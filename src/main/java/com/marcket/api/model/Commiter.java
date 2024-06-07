package com.marcket.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Commiter {

    @Id
    private Long id;
}
