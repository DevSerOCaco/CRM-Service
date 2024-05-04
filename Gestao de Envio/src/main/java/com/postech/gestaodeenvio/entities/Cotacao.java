package com.postech.gestaodeenvio.entities;

import com.google.gson.annotations.SerializedName;

public class Cotacao {
    private From from;
    private To to;

    private Products[] products;
    private Options options;
    private String services;

    public Cotacao( To toCep) {
        this.from = new From();
        this.to = toCep;
        this.products = new Products[1];
        this.options = new Options();
        this.services = "2";
    }
}