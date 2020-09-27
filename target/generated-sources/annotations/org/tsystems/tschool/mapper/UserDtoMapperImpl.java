package org.tsystems.tschool.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import org.tsystems.tschool.dto.UserDto;
import org.tsystems.tschool.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-28T01:32:56+0300",
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

        User user = new User();

        user.setId( userDto.getId() );
        user.setUsername( userDto.getUsername() );
        user.setLastName( userDto.getLastName() );
        user.setBirthday( userDto.getBirthday() );
        user.setEmail( userDto.getEmail() );

        return user;
    }
}
