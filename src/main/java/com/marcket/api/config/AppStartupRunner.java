package com.marcket.api.config;

import com.marcket.api.model.Commiter;
import com.marcket.api.model.Country;
import com.marcket.api.model.Market;
import com.marcket.api.model.Share;
import com.marcket.api.repository.CommiterRepository;
import com.marcket.api.repository.CountryRepository;
import com.marcket.api.repository.MarketRepository;
import com.marcket.api.repository.ShareRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Slf4j
public class AppStartupRunner implements ApplicationRunner {

    private final CountryRepository countryRepository;

    private final MarketRepository marketRepository;

    private final CommiterRepository commiterRepository;

    private final ShareRepository shareRepository;

    public AppStartupRunner(CountryRepository countryRepository, MarketRepository marketRepository, CommiterRepository commiterRepository, ShareRepository shareRepository) {
        this.countryRepository = countryRepository;
        this.marketRepository = marketRepository;
        this.commiterRepository = commiterRepository;
        this.shareRepository = shareRepository;
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

//        Commiter commiter = new Commiter();
//        commiter.setDescription("Test");
//        commiterRepository.save(commiter);
//
//        Share share1 = new Share();
//        share1.setMarket(marketToSave);
//        share1.setCommiter(commiter);
//        share1.setShare(new BigDecimal(50));
//        shareRepository.save(share1);
//        log.info("Share entities saved ------");
//
//        List<Object[]> objects = marketRepository.sumSharesPerMarketAndCountry();
//        objects.stream().forEach(o -> {
//            for (int i = 0; i < o.length; i++) {
//                System.out.print(o[i] + " - ");
//            }
//            System.out.println();
//        });
    }

}