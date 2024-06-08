package com.marcket.api.config;

import com.marcket.api.model.Country;
import com.marcket.api.model.Market;
import com.marcket.api.repository.CountryRepository;
import com.marcket.api.repository.MarketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppStartupRunner implements ApplicationRunner {

    private final CountryRepository countryRepository;

    private final MarketRepository marketRepository;

    public AppStartupRunner(CountryRepository countryRepository, MarketRepository marketRepository) {
        this.countryRepository = countryRepository;
        this.marketRepository = marketRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        Country countryArg = Country.builder().code("ARG").name("Argentina").build();
        Country countryUrg = Country.builder().code("URG").name("Uruguay").build();
        countryRepository.save(countryArg);
        countryRepository.save(countryUrg);
        log.info("Country entities saved ------");

        Market marketToSave = new Market();
        marketToSave.setCountry(countryArg);
        marketToSave.setCode("ROFEX");
        marketToSave.setDescription("El Índice de Acciones ROFEX 20 es un índice de retorno total, " +
                "diseñado para medir el desempeño de una cartera integrada por las veinte acciones más " +
                "líquidas operadas en los mercados bajo jurisdicción de la República Argentina");
        marketRepository.save(marketToSave);
        log.info("Market entities saved ------");

    }

}