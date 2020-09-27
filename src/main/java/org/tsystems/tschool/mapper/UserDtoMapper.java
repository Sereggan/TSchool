package org.tsystems.tschool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.tsystems.tschool.dto.CategoryDto;
import org.tsystems.tschool.dto.UserDto;
import org.tsystems.tschool.entity.Category;
import org.tsystems.tschool.entity.User;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDtoMapper INSTANCE = Mappers.getMapper( UserDtoMapper.class );

    UserDto userToDto(User user);
    User DtoToUser(UserDto userDto);
}
