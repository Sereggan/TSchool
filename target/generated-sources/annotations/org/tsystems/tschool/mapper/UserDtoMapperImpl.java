package org.tsystems.tschool.mapper;

import java.sql.Date;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import org.tsystems.tschool.dto.AddressDto;
import org.tsystems.tschool.dto.UserDto;
import org.tsystems.tschool.entity.Address;
import org.tsystems.tschool.entity.User;
import org.tsystems.tschool.entity.User.UserBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-30T17:28:33+0300",
    comments = "version: 1.4.0.CR1, compiler: javac, environment: Java 11.0.7 (JetBrains s.r.o.)"
)
@Component
public class UserDtoMapperImpl implements UserDtoMapper {

    @Override
    public UserDto userToDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setAddressDto( addressToAddressDto( user.getAddress() ) );
        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setLastName( user.getLastName() );
        userDto.setBirthday( user.getBirthday() );
        userDto.setEmail( user.getEmail() );

        return userDto;
    }

    @Override
    public User DtoToUser(UserDto userDto) {
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
