package com.marcket.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Country {
    @Id
    private Long id;
    private String code;
    private String name;
}
