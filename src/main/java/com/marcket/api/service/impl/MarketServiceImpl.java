package com.marcket.api.service.impl;

import com.marcket.api.model.Country;
import com.marcket.api.model.Market;
import com.marcket.api.model.dto.CountryStats;
import com.marcket.api.repository.CountryRepository;
import com.marcket.api.repository.MarketRepository;
import com.marcket.api.service.MarketService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarketServiceImpl implements MarketService {

    private final MarketRepository marketRepository;

    private final CountryRepository countryRepository;

    public MarketServiceImpl(MarketRepository marketRepository, CountryRepository countryRepository) {
        this.marketRepository = marketRepository;
        this.countryRepository = countryRepository;
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
        Country country = countryRepository.findById(market.getCountry().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
        market.setCountry(country);
        return marketRepository.save(market);
    }

    @Override
    public void deleteMarket(Long id) {
        marketRepository.deleteById(id);
    }

    @Override
    public List<CountryStats> getStats() {
        List<Object[]> distributionArray = marketRepository.sumSharesPerMarketAndCountry();
        List<CountryStats> countryStatsList = new ArrayList<>();
        distributionArray.forEach(o -> {
            CountryStats countryStats = CountryStats.builder()
                    .country(o[0].toString())
                    .market(o[1].toString())
                    .percentage(new BigDecimal(o[2].toString())).build();
            countryStatsList.add(countryStats);
        });
        return countryStatsList;
    }
}
