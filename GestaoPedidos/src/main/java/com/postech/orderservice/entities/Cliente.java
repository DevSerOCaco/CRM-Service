package com.postech.orderservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    private long id;
    private String nome;
    private String telefone;
    private String email;
    private Endereco endereco;
}
