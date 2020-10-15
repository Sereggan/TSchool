package org.tsystems.tschool.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import org.tsystems.tschool.dto.AddressDto;
import org.tsystems.tschool.entity.Address;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-15T15:06:07+0300",
    comments = "version: 1.4.0.CR1, compiler: javac, environment: Java 1.8.0_252 (Amazon.com Inc.)"
)
@Component
public class AddressDtoMapperImpl implements AddressDtoMapper {

    @Override
    public AddressDto addressToDto(Address address) {
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

    @Override
    public Address dtoToAddress(AddressDto addressDto) {
        if ( addressDto == null ) {
            return null;
        }

        Address address = new Address();

        address.setCountry( addressDto.getCountry() );
        address.setCity( addressDto.getCity() );
        address.setPostalCode( addressDto.getPostalCode() );
        address.setStreet( addressDto.getStreet() );
        address.setHouse( addressDto.getHouse() );
        address.setFlat( addressDto.getFlat() );

        return address;
    }
}
