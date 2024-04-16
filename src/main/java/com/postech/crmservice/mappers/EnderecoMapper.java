package com.postech.crmservice.mappers;

import com.postech.crmservice.entities.Endereco;
import com.postech.crmservice.entities.DTOs.EnderecoDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    EnderecoDto toDto(Endereco endereco);

    Endereco toEntity(EnderecoDto enderecoDto);
}