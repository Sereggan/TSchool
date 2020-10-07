package org.tsystems.tschool.service.jpa;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.tsystems.tschool.dao.ArticleDAO;
import org.tsystems.tschool.dao.CartDAO;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.dto.CartDto;
import org.tsystems.tschool.dto.CartItemDto;
import org.tsystems.tschool.entity.Cart;
import org.tsystems.tschool.mapper.CartDtoMapper;
import org.tsystems.tschool.service.CartService;

import javax.transaction.Transactional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    final CartDAO cartDao;

    final ArticleDAO articleDAO;

    private final CartDtoMapper mapper
            = Mappers.getMapper(CartDtoMapper.class);

    public CartServiceImpl(CartDAO cartDao, ArticleDAO articleDAO) {
        this.cartDao = cartDao;
        this.articleDAO = articleDAO;
    }

    @Override
    public CartDto addArticle(CartDto cartDto, ArticleDto articleDto) {
        Cart cart = cartDao.findById(mapper.dtoToCart(cartDto).getId());
        if(cart==null){

        }
        return null;
    }

    @Override
    public CartDto addArticleInSession(CartDto cartDto, ArticleDto articleDto) {

        for(CartItemDto item: cartDto.getCartItems()){
            if(item.getArticleId()==articleDto.getId()) {
                item.setQuantity(item.getQuantity()+1);
                cartDto.setTotalCost(cartDto.getTotalCost()+articleDto.getPrice());
                return cartDto;
            }
        }
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setArticle(articleDto.getTitle());
        cartItemDto.setArticleId(articleDto.getId());
        cartItemDto.setQuantity(1);
        cartDto.getCartItems().add(cartItemDto);
        cartDto.setTotalCost(cartDto.getTotalCost()+articleDto.getPrice());

        return cartDto;
    }

    @Override
    public CartDto findByUsername(String name) {
        return null;
    }
}
