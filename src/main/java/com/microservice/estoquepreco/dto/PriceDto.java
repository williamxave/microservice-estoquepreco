package com.microservice.estoquepreco.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PriceDto implements Serializable {

    public String productCode;
    public BigDecimal price;

    @Override
    public String toString() {
        return "PriceDto{" +
                "productCode='" + productCode + '\'' +
                ", price=" + price +
                '}';
    }
}
