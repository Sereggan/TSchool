package org.tsystems.tschool.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.tsystems.tschool.dto.AddressDto;
import org.tsystems.tschool.dto.OrderDto;
import org.tsystems.tschool.dto.OrderItemDto;
import org.tsystems.tschool.entity.Address;
import org.tsystems.tschool.entity.Order;
import org.tsystems.tschool.entity.OrderItem;
import org.tsystems.tschool.entity.User;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-16T00:48:23+0300",
    comments = "version: 1.4.0.CR1, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
*/
@Component
public class OrderDtoMapperImpl implements OrderDtoMapper {

    @Override
    public OrderDto orderToDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setUserId( orderUserId( order ) );
        orderDto.setAddressDto( addressToAddressDto( order.getAddress() ) );
        orderDto.setId( order.getId() );
        orderDto.setPaymentMethod( order.getPaymentMethod() );
        orderDto.setDeliveryMethod( order.getDeliveryMethod() );
        orderDto.setOrderItems( orderItemSetToOrderItemDtoList( order.getOrderItems() ) );
        orderDto.setIsPaid( order.getIsPaid() );
        orderDto.setOrderStatus( order.getOrderStatus() );
        orderDto.setPrice( order.getPrice() );

        return orderDto;
    }

    @Override
    public OrderItemDto orderItemToDto(OrderItem item) {
        if ( item == null ) {
            return null;
        }

        OrderItemDto orderItemDto = new OrderItemDto();

        orderItemDto.setArticle( item.getArticleTitle() );
        orderItemDto.setId( item.getId() );
        orderItemDto.setQuantity( item.getQuantity() );
        orderItemDto.setPrice( item.getPrice() );

        return orderItemDto;
    }

    private Long orderUserId(Order order) {
        if ( order == null ) {
            return null;
        }
        User user = order.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected AddressDto addressToAddressDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDto addressDto = new AddressDto();

        addressDto.setCountry( address.getCountry() );
        addressDto.setCity( address.getCity() );
        addressDto.setPostalCode( address.getPostalCode() );
        addressDto.setStreet( address.getStreet() );
        addressDto.setHouse( address.getHouse() );
        addressDto.setFlat( address.getFlat() );

        return addressDto;
    }

    protected List<OrderItemDto> orderItemSetToOrderItemDtoList(Set<OrderItem> set) {
        if ( set == null ) {
            return null;
        }

        List<OrderItemDto> list = new ArrayList<OrderItemDto>( set.size() );
        for ( OrderItem orderItem : set ) {
            list.add( orderItemToDto( orderItem ) );
        }

        return list;
    }
}
