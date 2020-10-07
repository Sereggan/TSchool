package org.tsystems.tschool.service.jpa;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.tsystems.tschool.dao.ArticleDAO;
import org.tsystems.tschool.dao.CartDAO;
import org.tsystems.tschool.dao.UserDAO;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.dto.CartDto;
import org.tsystems.tschool.dto.CartItemDto;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Cart;
import org.tsystems.tschool.entity.CartItem;
import org.tsystems.tschool.entity.User;
import org.tsystems.tschool.mapper.CartDtoMapper;
import org.tsystems.tschool.service.CartService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    final CartDAO cartDao;

    final UserDAO userDAO;

    final ArticleDAO articleDAO;

    private final CartDtoMapper mapper
            = Mappers.getMapper(CartDtoMapper.class);

    public CartServiceImpl(CartDAO cartDao, UserDAO userDAO, ArticleDAO articleDAO) {
        this.cartDao = cartDao;
        this.userDAO = userDAO;
        this.articleDAO = articleDAO;
    }

    @Override
    public CartDto addArticle(Long cartId, Long articleId) {
        Cart cart = cartDao.findById(cartId);
        Article article = articleDAO.findById(articleId);
        List<CartItem> items = new ArrayList<>(cart.getCartItems());
        for(int i = 0;i<items.size(); i++){
            if(items.get(i).getArticle().getId().equals(articleId)){
                CartItem item = items.get(i);
                item.setQuantity(item.getQuantity()+1);
                item.setPrice(article.getPrice()*item.getQuantity());
                cart.setTotalCost(cart.getTotalCost()+article.getPrice());
                return mapper.cartToDto(cart);
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setArticle(article);
        cartItem.setCart(cart);
        cartItem.setQuantity(1);
        cartItem.setPrice(article.getPrice());
        cart.getCartItems().add(cartItem);
        cart.setTotalCost(cart.getTotalCost()+ article.getPrice());
        return mapper.cartToDto(cart);
    }

    @Override
    public void removeArticle(Long cartId, Long articleId) {
        Cart cart = cartDao.findById(cartId);
        List<CartItem> items = new ArrayList<>(cart.getCartItems());
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getArticle().getId().equals(articleId)){
                CartItem item = items.get(i);
                if(item.getQuantity()==1){
                    cart.removeItem(item);
                    cart.setTotalCost(cart.getTotalCost()-item.getPrice());
                    return;
                }
                Float price = item.getPrice()/item.getQuantity();
                item.setQuantity(item.getQuantity()-1);
                item.setPrice(price*item.getQuantity());
                cart.setTotalCost(cart.getTotalCost()-price);
            }
        }
    }

    @Override
    public CartDto addArticleInSession(CartDto cartDto, ArticleDto articleDto) {

        for(CartItemDto item: cartDto.getCartItems()){
            if(item.getArticleId()==articleDto.getId()) {
                item.setQuantity(item.getQuantity()+1);
                item.setPrice(item.getQuantity()*articleDto.getPrice());
                cartDto.setTotalCost(cartDto.getTotalCost()+articleDto.getPrice());
                return cartDto;
            }
        }
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setArticle(articleDto.getTitle());
        cartItemDto.setArticleId(articleDto.getId());
        cartItemDto.setQuantity(1);
        cartItemDto.setPrice(articleDto.getPrice()*cartItemDto.getQuantity());
        cartDto.getCartItems().add(cartItemDto);
        cartDto.setTotalCost(cartDto.getTotalCost()+articleDto.getPrice());

        return cartDto;
    }

    @Override
    public CartDto removeArticleInSession(CartDto cartDto, Long id) {
        List<CartItemDto> list = new ArrayList<>(cartDto.getCartItems());
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getArticleId().equals(id)){
                CartItemDto item = list.get(i);
                if(item.getQuantity()==1){
                    list.remove(item);
                }
                Float price = item.getPrice()/item.getQuantity();
                item.setQuantity(item.getQuantity()-1);
                item.setPrice(price*item.getQuantity());
            }
        }
        cartDto.setCartItems(new HashSet<>(list));
        return cartDto;
    }

    @Override
    public CartDto findByUsername(String name) {
        Cart cart = cartDao.findByUsername(name);
        if(cart == null) {
            User user = userDAO.getUserByUsername(name);
            cart = new Cart();
            cart.setUser(user);
            cart.setTotalCost(0F);
            cart = cartDao.save(cart);
        }
        return mapper.cartToDto(cart);
    }
}
