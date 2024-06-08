package com.marcket.api.service;

import com.marcket.api.model.Commiter;

import java.util.List;

public interface CommiterService {

    List<Commiter> getAllCommiters();

    Commiter getCommiterById(Long id);

    Commiter saveCommiter(Commiter commiter);

    void deleteCommiter(Long id);
}
