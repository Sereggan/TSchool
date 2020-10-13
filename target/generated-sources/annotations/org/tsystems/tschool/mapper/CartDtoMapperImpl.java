package org.tsystems.tschool.mapper;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import org.tsystems.tschool.dto.CartDto;
import org.tsystems.tschool.dto.CartItemDto;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Cart;
import org.tsystems.tschool.entity.CartItem;
import org.tsystems.tschool.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-13T16:54:54+0300",
    comments = "version: 1.4.0.CR1, compiler: javac, environment: Java 1.8.0_252 (Amazon.com Inc.)"
)
@Component
public class CartDtoMapperImpl implements CartDtoMapper {

    @Override
    public CartDto cartToDto(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartDto cartDto = new CartDto();

        cartDto.setUserId( cartUserId( cart ) );
        cartDto.setId( cart.getId() );
        cartDto.setCartItems( cartItemSetToCartItemDtoSet( cart.getCartItems() ) );
        cartDto.setTotalCost( cart.getTotalCost() );

        return cartDto;
    }

    @Override
    public CartItemDto cartItemToDto(CartItem item) {
        if ( item == null ) {
            return null;
        }

        CartItemDto cartItemDto = new CartItemDto();

        cartItemDto.setArticle( itemArticleTitle( item ) );
        cartItemDto.setArticleId( itemArticleId( item ) );
        cartItemDto.setPrice( itemArticlePrice( item ) );
        cartItemDto.setId( item.getId() );
        cartItemDto.setQuantity( item.getQuantity() );

        return cartItemDto;
    }

    @Override
    public Cart dtoToCart(CartDto cartDto) {
        if ( cartDto == null ) {
            return null;
        }

        Cart cart = new Cart();

        cart.setId( cartDto.getId() );
        cart.setCartItems( cartItemDtoSetToCartItemSet( cartDto.getCartItems() ) );
        cart.setTotalCost( cartDto.getTotalCost() );

        return cart;
    }

    @Override
    public CartItem dtoToCartItem(CartItemDto item) {
        if ( item == null ) {
            return null;
        }

        CartItem cartItem = new CartItem();

        cartItem.setArticle( cartItemDtoToArticle( item ) );
        cartItem.setId( item.getId() );
        cartItem.setQuantity( item.getQuantity() );
        cartItem.setPrice( item.getPrice() );

        return cartItem;
    }

    private Long cartUserId(Cart cart) {
        if ( cart == null ) {
            return null;
        }
        User user = cart.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<CartItemDto> cartItemSetToCartItemDtoSet(Set<CartItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<CartItemDto> set1 = new HashSet<CartItemDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CartItem cartItem : set ) {
            set1.add( cartItemToDto( cartItem ) );
        }

        return set1;
    }

    private String itemArticleTitle(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }
        Article article = cartItem.getArticle();
        if ( article == null ) {
            return null;
        }
        String title = article.getTitle();
        if ( title == null ) {
            return null;
        }
        return title;
    }

    private Long itemArticleId(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }
        Article article = cartItem.getArticle();
        if ( article == null ) {
            return null;
        }
        Long id = article.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Float itemArticlePrice(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }
        Article article = cartItem.getArticle();
        if ( article == null ) {
            return null;
        }
        Float price = article.getPrice();
        if ( price == null ) {
            return null;
        }
        return price;
    }

    protected Set<CartItem> cartItemDtoSetToCartItemSet(Set<CartItemDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<CartItem> set1 = new HashSet<CartItem>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CartItemDto cartItemDto : set ) {
            set1.add( dtoToCartItem( cartItemDto ) );
        }

        return set1;
    }

    protected Article cartItemDtoToArticle(CartItemDto cartItemDto) {
        if ( cartItemDto == null ) {
            return null;
        }

        Article article = new Article();

        article.setTitle( cartItemDto.getArticle() );
        article.setPrice( cartItemDto.getPrice() );

        return article;
    }
}
