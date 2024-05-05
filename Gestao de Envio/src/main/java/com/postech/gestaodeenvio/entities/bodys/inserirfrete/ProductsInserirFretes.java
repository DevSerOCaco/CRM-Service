package com.postech.gestaodeenvio.entities.bodys.inserirfrete;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductsInserirFretes {
    private String name;
    private String quantity;
    private String unitary_value;

    public ProductsInserirFretes() {
        this.name = "teste product";
        this.quantity = "1";
        this.unitary_value = "150";
    }
}
