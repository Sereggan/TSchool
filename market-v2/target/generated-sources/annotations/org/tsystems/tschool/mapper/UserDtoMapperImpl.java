package org.tsystems.tschool.mapper;

import java.sql.Date;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import org.tsystems.tschool.dto.AddressDto;
import org.tsystems.tschool.dto.UserDto;
import org.tsystems.tschool.dto.UserDto.UserDtoBuilder;
import org.tsystems.tschool.entity.Address;
import org.tsystems.tschool.entity.Address.AddressBuilder;
import org.tsystems.tschool.entity.User;
import org.tsystems.tschool.entity.User.UserBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-23T21:27:20+0300",
    comments = "version: 1.4.0.CR1, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
@Component
public class UserDtoMapperImpl implements UserDtoMapper {

    @Override
    public UserDto userToDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.addressDto( addressToAddressDto( user.getAddress() ) );
        userDto.id( user.getId() );
        userDto.username( user.getUsername() );
        userDto.lastName( user.getLastName() );
        userDto.birthday( user.getBirthday() );
        userDto.email( user.getEmail() );

        return userDto.build();
    }

    @Override
    public User dtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.address( addressDtoToAddress( userDto.getAddressDto() ) );
        user.id( userDto.getId() );
        user.username( userDto.getUsername() );
        user.lastName( userDto.getLastName() );
        if ( userDto.getBirthday() != null ) {
            user.birthday( new Date( userDto.getBirthday().getTime() ) );
        }
        user.email( userDto.getEmail() );

        return user.build();
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

    protected Address addressDtoToAddress(AddressDto addressDto) {
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
