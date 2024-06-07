package com.marcket.api.service;

import com.marcket.api.model.Market;

import java.util.List;

public interface MarketService {

    List<Market> getAllMarkets();

    Market getMarketById(Long id);

    Market saveMarket(Market market);

    void deleteMarket(Long id);
}

