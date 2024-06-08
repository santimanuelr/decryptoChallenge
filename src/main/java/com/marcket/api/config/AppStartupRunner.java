package com.marcket.api.config;

import com.marcket.api.model.Country;
import com.marcket.api.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppStartupRunner implements ApplicationRunner {

    private final CountryRepository countryRepository;

    public AppStartupRunner(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void run(ApplicationArguments args) {

        Country countryArg = Country.builder().code("ARG").name("Argentina").build();
        countryRepository.save(countryArg);

    }

}