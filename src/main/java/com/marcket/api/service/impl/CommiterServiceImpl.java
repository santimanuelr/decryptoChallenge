package com.marcket.api.service.impl;

import com.marcket.api.model.Commiter;
import com.marcket.api.repository.CommiterRepository;
import com.marcket.api.service.CommiterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommiterServiceImpl implements CommiterService {

    private final CommiterRepository commiterRepository;

    public CommiterServiceImpl(CommiterRepository commiterRepository) {
        this.commiterRepository = commiterRepository;
    }

    @Override
    public List<Commiter> getAllCommiters() {
        return commiterRepository.findAll();
    }

    @Override
    public Commiter getCommiterById(Long id) {
        return commiterRepository.findById(id).orElse(null);
    }

    @Override
    public Commiter saveCommiter(Commiter commiter) {
        return commiterRepository.save(commiter);
    }

    @Override
    public void deleteCommiter(Long id) {
        commiterRepository.deleteById(id);
    }
}

