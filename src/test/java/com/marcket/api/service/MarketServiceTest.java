package com.marcket.api.service;

import com.marcket.api.model.Market;
import com.marcket.api.repository.MarketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.marcket.api.rest.MarketControllerTest.MARKET_CODE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
public class MarketServiceTest {

    @Autowired
    private MarketService marketService;

    @MockBean
    private MarketRepository marketRepository;

    @Test
    public void saveMarket() {
        //Given
        Market market = new Market();
        market.setCode(MARKET_CODE);
        market.setDescription("Market Description");

        //When
        when(marketRepository.save(market)).thenReturn(market);
        Market marketSaved = marketService.saveMarket(market);

        //Then
        assertNotNull(marketSaved);
        assertEquals(MARKET_CODE, marketSaved.getCode());

        // Assert the response status and body
        verify(marketRepository).save(any()); // Verify service interaction
    }


}
