package org.tsystems.tschool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tsystems.tschool.dto.CartDto;
import org.tsystems.tschool.dto.CartItemDto;
import org.tsystems.tschool.entity.Cart;
import org.tsystems.tschool.entity.CartItem;

@Mapper(componentModel = "spring")
public interface CartDtoMapper {

    @Mapping(source = "cart.user.id", target = "userId")
    CartDto cartToDto(Cart cart);

    @Mapping(source = "item.article.title", target = "article")
    @Mapping(source = "item.article.id", target = "articleId")
    @Mapping(source = "item.article.price", target = "price")
    CartItemDto cartItemToDto(CartItem item);

    Cart dtoToCart(CartDto cartDto);

    @Mapping(source = "item.article", target = "article.title")
    @Mapping(source = "item.price", target = "article.price")
    CartItem dtoToCartItem(CartItemDto item);
}