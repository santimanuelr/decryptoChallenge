package com.marcket.api.service;

import com.marcket.api.model.Country;
import com.marcket.api.model.Market;
import com.marcket.api.model.dto.CountryStats;
import com.marcket.api.repository.CountryRepository;
import com.marcket.api.repository.MarketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static com.marcket.api.rest.MarketControllerTest.MARKET_CODE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MarketServiceTest {

    @Autowired
    private MarketService marketService;

    @MockBean
    private MarketRepository marketRepository;

    @MockBean
    private CountryRepository countryRepository;

    @Test
    public void saveMarketTest() {
        //Given
        Market market = getMockMarket();
        Country countryArg = Country.builder().code("ARG").name("Argentina").build();

        //When
        when(marketRepository.save(market)).thenReturn(market);
        when(countryRepository.findById(eq(1L))).thenReturn(Optional.of(countryArg));
        Market marketSaved = marketService.saveMarket(market);

        //Then
        assertNotNull(marketSaved);
        assertEquals(MARKET_CODE, marketSaved.getCode());

        verify(marketRepository).save(eq(marketSaved)); // Verify service interaction
    }

    @Test
    public void failSaveMarketCountryNotFoundException() {
        //Given
        Market market = getMockMarket();
        market.setCountry(Country.builder().id(99L).build());
        Country countryArg = Country.builder().code("ARG").name("Argentina").build();

        //When
        when(marketRepository.save(market)).thenReturn(market);
        when(countryRepository.findById(eq(1L))).thenReturn(Optional.of(countryArg));

        //Then
        assertThrows(ResponseStatusException.class, () -> marketService.saveMarket(market));
    }

    private static Market getMockMarket() {
        Market market = new Market();
        market.setCode(MARKET_CODE);
        market.setDescription("Market Description");
        market.setCountry(Country.builder().id(1L).build());
        return market;
    }

    @Test
    public void testGetStats() {
        //Given
        String[] resultStats = {"Argentina", "MAE", "40.30"};
        String[] resultStats2 = {"Argentina", "ROFEX", "20.0"};

        //When
        when(marketRepository.sumSharesPerMarketAndCountry()).thenReturn(List.of(resultStats, resultStats2));
        List<CountryStats> countryStats = marketService.getStats();

        //Then
        assertEquals(2, countryStats.size());
    }

}
