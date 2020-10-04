package org.tsystems.tschool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.tsystems.tschool.dto.CatalogValueDto;
import org.tsystems.tschool.dto.OrderDto;
import org.tsystems.tschool.dto.OrderItemDto;
import org.tsystems.tschool.entity.Order;
import org.tsystems.tschool.entity.OrderItem;
import org.tsystems.tschool.entity.Value;

@Mapper(componentModel = "spring")

public interface OrderDtoMapper {
    OrderDtoMapper INSTANCE = Mappers.getMapper( OrderDtoMapper.class );

    @Mapping(source = "order.user.id", target = "userId")
    @Mapping(source = "order.address", target = "addressDto")
    OrderDto orderToDto(Order order);

    @Mapping(source = "item.article.title", target = "article")
    OrderItemDto orderItemToDto(OrderItem item);
}
