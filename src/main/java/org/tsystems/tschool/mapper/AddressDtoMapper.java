package org.tsystems.tschool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.tsystems.tschool.dto.AddressDto;
import org.tsystems.tschool.entity.Address;

@Mapper(componentModel = "spring")
public interface AddressDtoMapper {
    AddressDto addressToDto(Address address);

    Address dtoToAddress(AddressDto addressDto);
}
