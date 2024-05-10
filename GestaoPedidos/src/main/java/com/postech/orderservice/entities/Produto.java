package com.postech.orderservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    private long id;
    private String descricao;
    private double precoUnitario;
    private int quantidade;

}