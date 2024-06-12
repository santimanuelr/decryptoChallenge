package com.marcket.api.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CountryStats {

    private String country;
    private String market;
    private BigDecimal percentage;

}
