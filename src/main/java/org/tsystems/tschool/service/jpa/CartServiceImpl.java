package org.tsystems.tschool.service.jpa;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.tsystems.tschool.dao.ArticleDAO;
import org.tsystems.tschool.dao.CartDAO;
import org.tsystems.tschool.dao.OrderDAO;
import org.tsystems.tschool.dao.UserDAO;
import org.tsystems.tschool.dto.*;
import org.tsystems.tschool.entity.*;
import org.tsystems.tschool.enums.OrderStatus;
import org.tsystems.tschool.mapper.AddressDtoMapper;
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

    final OrderDAO orderDAO;

    private final CartDtoMapper mapper
            = Mappers.getMapper(CartDtoMapper.class);

    private final AddressDtoMapper addressDtoMapper
            = Mappers.getMapper(AddressDtoMapper.class);

    public CartServiceImpl(CartDAO cartDao, UserDAO userDAO, ArticleDAO articleDAO, OrderDAO orderDAO) {
        this.cartDao = cartDao;
        this.userDAO = userDAO;
        this.articleDAO = articleDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public CartDto addArticle(Long cartId, Long articleId) {
        Cart cart = cartDao.findById(cartId);
        Article article = articleDAO.findByIdWithLock(articleId);
        Float articlePrice = article.getPrice();
        article.setQuantity(article.getQuantity()-1);
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
        cartItem.setPrice(articlePrice);
        cart.getCartItems().add(cartItem);
        cart.setTotalCost(cart.getTotalCost() + articlePrice);
        return mapper.cartToDto(cart);
    }

    @Override
    public CartDto removeArticle(Long cartId, Long articleId) {
        Cart cart = cartDao.findById(cartId);
        List<CartItem> items = new ArrayList<>(cart.getCartItems());
        Article article = articleDAO.findByIdWithLock(articleId);
        article.setQuantity(article.getQuantity()+1);
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getArticle().getId().equals(articleId)){
                CartItem item = items.get(i);
                cart.setTotalCost(cart.getTotalCost()-article.getPrice());
                if(item.getQuantity()==1){
                    cart.removeItem(item);

                    return mapper.cartToDto(cart);
                }
                Float price = item.getPrice()/item.getQuantity();
                item.setQuantity(item.getQuantity()-1);
                item.setPrice(price*item.getQuantity());
            }
        }

        return mapper.cartToDto(cart);
    }

    // Moves cart from session to database after authorization
    @Override
    public CartDto addItemsToDatabase(CartDto cartDto, String username) {

        Cart cart = cartDao.findByUsername(username);

        cart.setTotalCost(cartDto.getTotalCost());
        List<CartItemDto> cartItemDtos = new ArrayList<>(cartDto.getCartItems());
        Set<CartItem> cartItems = new HashSet<>();
        for(int i=0; i< cartItemDtos.size(); i++){
            CartItemDto cartItemDto = cartItemDtos.get(i);
            CartItem item = mapper.dtoToCartItem(cartItemDto);
            Article article = articleDAO.findById(cartItemDto.getArticleId());
            item.setArticle(article);
            item.setCart(cart);
            cartItems.add(item);
            cart.getCartItems().add(item);
        }

        return mapper.cartToDto(cart);
    }

    @Override
    public CartDto addArticleInSession(CartDto cartDto, ArticleDto articleDto) {

        Article article = articleDAO.findByIdWithLock(articleDto.getId());
        article.setQuantity(article.getQuantity()-1);
        
        for(CartItemDto item: cartDto.getCartItems()){
            if(item.getArticleId().equals(articleDto.getId())) {
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
        Article article = articleDAO.findByIdWithLock(id);
        article.setQuantity(article.getQuantity()+1);
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
        cartDto.setTotalCost(cartDto.getTotalCost() - article.getPrice());
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

    // Converts Cart to Order when user makes order
    @Override
    public void createOrder(CartDto cartDto, OrderDetailsDto orderDetailsDto) {
        Order order = new Order();
        User user = userDAO.getUserById(cartDto.getUserId());
        order.setUser(user);
        order.setAddress(addressDtoMapper.dtoToAddress(orderDetailsDto.getAddressDto()));
        order.setDeliveryMethod(orderDetailsDto.getDeliveryMethod());
        order.setPaymentMethod(orderDetailsDto.getPaymentMethod());
        order.setIsPaid(false);
        order.setPrice(cartDto.getTotalCost());
        order.setOrderStatus(OrderStatus.STATUS_AWAITING_SHIPMENT);

        for(CartItemDto item: cartDto.getCartItems()){
            Article article = articleDAO.findById(item.getArticleId());
            OrderItem orderItem = new OrderItem();
            orderItem.setArticleTitle(article.getTitle());
            orderItem.setArticleTitle(item.getArticle());
            orderItem.setOrder(order);
            orderItem.setPrice(item.getPrice());
            orderItem.setQuantity(item.getQuantity());
            order.addOrderItem(orderItem);
        }

        user.getOrders().add(order);
        orderDAO.save(order);
        Cart cart = cartDao.findById(cartDto.getId());
        cartDao.update(cart);
        cartDao.delete(cart);
    }
}
