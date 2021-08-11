package com.microservice.estoquepreco.dto;

import java.io.Serializable;

public class StockDto implements Serializable {

    public String productCode;
    public String amount;

    @Override
    public String toString() {
        return "StockDto{" +
                "productCode='" + productCode + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
