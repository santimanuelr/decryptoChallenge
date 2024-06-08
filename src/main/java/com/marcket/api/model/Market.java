package com.marcket.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String description;
    @ManyToOne
    private Country country;
    @OneToMany
    private Set<Share> shares;

}
