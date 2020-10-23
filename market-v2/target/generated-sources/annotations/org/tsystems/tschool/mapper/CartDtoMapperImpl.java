package org.tsystems.tschool.mapper;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import org.tsystems.tschool.dto.CartDto;
import org.tsystems.tschool.dto.CartDto.CartDtoBuilder;
import org.tsystems.tschool.dto.CartItemDto;
import org.tsystems.tschool.dto.CartItemDto.CartItemDtoBuilder;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Article.ArticleBuilder;
import org.tsystems.tschool.entity.Cart;
import org.tsystems.tschool.entity.Cart.CartBuilder;
import org.tsystems.tschool.entity.CartItem;
import org.tsystems.tschool.entity.CartItem.CartItemBuilder;
import org.tsystems.tschool.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-23T03:38:54+0300",
    comments = "version: 1.4.0.CR1, compiler: javac, environment: Java 14.0.2 (Oracle Corporation)"
)
@Component
public class CartDtoMapperImpl implements CartDtoMapper {

    @Override
    public CartDto cartToDto(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartDtoBuilder cartDto = CartDto.builder();

        cartDto.userId( cartUserId( cart ) );
        cartDto.id( cart.getId() );
        cartDto.cartItems( cartItemSetToCartItemDtoSet( cart.getCartItems() ) );
        cartDto.totalCost( cart.getTotalCost() );

        return cartDto.build();
    }

    @Override
    public CartItemDto cartItemToDto(CartItem item) {
        if ( item == null ) {
            return null;
        }

        CartItemDtoBuilder cartItemDto = CartItemDto.builder();

        cartItemDto.article( itemArticleTitle( item ) );
        cartItemDto.articleId( itemArticleId( item ) );
        cartItemDto.price( itemArticlePrice( item ) );
        cartItemDto.id( item.getId() );
        cartItemDto.quantity( item.getQuantity() );

        return cartItemDto.build();
    }

    @Override
    public Cart dtoToCart(CartDto cartDto) {
        if ( cartDto == null ) {
            return null;
        }

        CartBuilder cart = Cart.builder();

        cart.id( cartDto.getId() );
        cart.cartItems( cartItemDtoSetToCartItemSet( cartDto.getCartItems() ) );
        cart.totalCost( cartDto.getTotalCost() );

        return cart.build();
    }

    @Override
    public CartItem dtoToCartItem(CartItemDto item) {
        if ( item == null ) {
            return null;
        }

        CartItemBuilder cartItem = CartItem.builder();

        cartItem.article( cartItemDtoToArticle( item ) );
        cartItem.id( item.getId() );
        cartItem.quantity( item.getQuantity() );
        cartItem.price( item.getPrice() );

        return cartItem.build();
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

        ArticleBuilder article = Article.builder();

        article.title( cartItemDto.getArticle() );
        article.price( cartItemDto.getPrice() );

        return article.build();
    }
}
