package org.tsystems.tschool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.tsystems.tschool.dto.UserDto;
import org.tsystems.tschool.entity.User;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDtoMapper INSTANCE = Mappers.getMapper( UserDtoMapper.class );

    @Mapping(source = "user.address", target = "addressDto")
    UserDto userToDto(User user);

    @Mapping(source = "userDto.addressDto", target = "address")
    User DtoToUser(UserDto userDto);
}
