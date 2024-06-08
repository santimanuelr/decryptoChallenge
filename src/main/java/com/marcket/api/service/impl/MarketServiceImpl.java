package com.marcket.api.service.impl;

import com.marcket.api.model.Market;
import com.marcket.api.repository.MarketRepository;
import com.marcket.api.service.MarketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketServiceImpl implements MarketService {

    private final MarketRepository marketRepository;

    public MarketServiceImpl(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    @Override
    public List<Market> getAllMarkets() {
        return marketRepository.findAll();
    }

    @Override
    public Market getMarketById(Long id) {
        return marketRepository.findById(id).orElse(null);
    }

    @Override
    public Market saveMarket(Market market) {
        return marketRepository.save(market);
    }

    @Override
    public void deleteMarket(Long id) {
        marketRepository.deleteById(id);
    }
}
