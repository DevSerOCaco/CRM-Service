package com.postech.gestaodeenvio.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Destinatario {

    private long id;

    private String nome;

    private String telefone;

}
