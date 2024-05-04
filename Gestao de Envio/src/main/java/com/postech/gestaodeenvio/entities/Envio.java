package com.postech.gestaodeenvio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Envio {

    @Id
    private long id;

    private long pedidoId;

    private String transportadora;

    private String servico;

    private String codigoRastreamento;

    private String status;

    private String etiquetaUrl;
}