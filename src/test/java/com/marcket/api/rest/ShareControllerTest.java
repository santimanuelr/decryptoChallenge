package com.marcket.api.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcket.api.model.Commiter;
import com.marcket.api.model.Market;
import com.marcket.api.model.Share;
import com.marcket.api.service.ShareService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ShareController.class)
public class ShareControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ShareService shareService;

    @Test
    public void testCreateShare() throws Exception {
        // Create a mock Share object
        Market mockMarket = new Market();
        mockMarket.setId(1L);
        mockMarket.setCode("MARKET_CODE");
        Commiter mockCommiter = new Commiter();
        mockCommiter.setId(2L);
        mockCommiter.setDescription("Test Commiter");
        Share share = new Share();
        share.setMarket(mockMarket);
        share.setCommiter(mockCommiter);
        share.setShare(new BigDecimal(100));

        // Mock the ShareService behavior
        Share savedShare = new Share();
        savedShare.setId(3L);
        savedShare.setMarket(mockMarket);
        savedShare.setCommiter(mockCommiter);
        savedShare.setShare(new BigDecimal(100));
        when(shareService.saveShare(share)).thenReturn(savedShare);

        // Perform the POST request
        mvc.perform(post("/shares")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(share)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.share").value(new BigDecimal(100)));

        verify(shareService).saveShare(eq(share)); // Verify service interaction
    }

    public static String asJsonString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
