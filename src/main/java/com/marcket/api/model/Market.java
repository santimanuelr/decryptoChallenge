package com.marcket.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Market {

    @Id
    private Long id;
    private String code;
    private String description;
    @ManyToOne
    private Country country;
    @ManyToMany
    private Set<Commiter> commiters;

}
