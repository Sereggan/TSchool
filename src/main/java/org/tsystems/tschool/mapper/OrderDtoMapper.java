package org.tsystems.tschool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tsystems.tschool.dto.OrderDto;
import org.tsystems.tschool.dto.OrderItemDto;
import org.tsystems.tschool.entity.Order;
import org.tsystems.tschool.entity.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderDtoMapper {

    @Mapping(source = "order.user.id", target = "userId")
    @Mapping(source = "order.address", target = "addressDto")
    OrderDto orderToDto(Order order);

    @Mapping(source = "item.articleTitle", target = "article")
    OrderItemDto orderItemToDto(OrderItem item);
}
