package com.marcket.api.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcket.api.model.Market;
import com.marcket.api.service.MarketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MarketController.class)
public class MarketControllerTest {

    public static final String MARKET_CODE = "MARKET_CODE";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MarketService marketService;


    @Test
    public void testCreateMarket() throws Exception {

        Market market = new Market();
        market.setCode(MARKET_CODE);
        market.setDescription("Market Description");
        // Mock the MarketService behavior
        Market savedMarket = new Market();
        savedMarket.setId(1L);
        savedMarket.setCode(MARKET_CODE);
        savedMarket.setDescription("Market Description");
        when(marketService.saveMarket(market)).thenReturn(savedMarket);

        // Perform the POST request
        mockMvc.perform(post("/markets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(market)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.code").value(MARKET_CODE));

        // Assert the response status and body
        verify(marketService).saveMarket(any()); // Verify service interaction
    }

    public static String asJsonString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
