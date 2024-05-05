package com.postech.gestaodeenvio.entities.bodys.inserirfrete;

import com.postech.gestaodeenvio.entities.bodys.*;
import com.postech.gestaodeenvio.entities.bodys.calculafrete.ProductsCotacao;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
public class InserirFretesRequest {
    private int service;
    private Integer agency;
    private From from;
    private ToInserirFrete to;
    private List<ProductsInserirFretes> products;
    private List<Volume> volumes;
    private Options options;

    public InserirFretesRequest() {
        this.service = 2;
        this.agency = null;
        this.from = new FromInserirFrete();
        this.to = new ToInserirFrete("95020035");
        this.products = new ArrayList<>();
        this.products.add(new ProductsInserirFretes());
        this.volumes = new ArrayList<>();
        this.volumes.add(new Volume());
        this.options = new Options();
    }
}
