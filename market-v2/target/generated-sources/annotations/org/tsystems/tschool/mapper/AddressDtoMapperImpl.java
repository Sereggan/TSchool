package org.tsystems.tschool.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import org.tsystems.tschool.dto.AddressDto;
import org.tsystems.tschool.entity.Address;
import org.tsystems.tschool.entity.Address.AddressBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-04T19:04:57+0300",
    comments = "version: 1.4.0.CR1, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
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

        AddressBuilder address = Address.builder();

        address.country( addressDto.getCountry() );
        address.city( addressDto.getCity() );
        address.postalCode( addressDto.getPostalCode() );
        address.street( addressDto.getStreet() );
        address.house( addressDto.getHouse() );
        address.flat( addressDto.getFlat() );

        return address.build();
    }
}
