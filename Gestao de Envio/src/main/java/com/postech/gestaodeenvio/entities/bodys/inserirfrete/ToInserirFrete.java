package com.postech.gestaodeenvio.entities.bodys.inserirfrete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ToInserirFrete extends com.postech.gestaodeenvio.entities.bodys.To {
    private String name;
    private String address;//logradouro
    private String city;
    private String document;//cpf


    public ToInserirFrete(String postal_code) {
        super(postal_code);
        this.name = "Teste";
        this.address = "Teste";
        this.city = "Caxias do Sul";
        this.document = "02076732098";
    }
}
