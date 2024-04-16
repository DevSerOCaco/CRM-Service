package com.postech.crmservice.mappers;

import com.postech.crmservice.entities.Cliente;
import com.postech.crmservice.entities.DTOs.ClienteDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = EnderecoMapper.class, componentModel = "spring")
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDto toDto(Cliente cliente);

    Cliente toEntity(ClienteDto clienteDto);
}