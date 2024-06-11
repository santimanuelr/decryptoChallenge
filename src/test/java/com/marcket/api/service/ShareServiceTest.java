package com.marcket.api.service;

import com.marcket.api.model.Commiter;
import com.marcket.api.model.Market;
import com.marcket.api.model.Share;
import com.marcket.api.repository.CommiterRepository;
import com.marcket.api.repository.MarketRepository;
import com.marcket.api.repository.ShareRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static com.marcket.api.rest.MarketControllerTest.MARKET_CODE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ShareServiceTest {

    public static final BigDecimal SHARE = new BigDecimal(100);

    @Autowired
    private ShareService shareService;

    @MockBean
    private ShareRepository shareRepository;

    @MockBean
    private MarketRepository marketRepository;

    @MockBean
    private CommiterRepository commiterRepository;

    @Test
    public void saveShareTest() {
        //Given
        Market mockMarket = new Market();
        mockMarket.setId(1L);
        mockMarket.setCode(MARKET_CODE);
        Commiter mockCommiter = new Commiter();
        mockCommiter.setId(2L);
        mockCommiter.setDescription("Test Commiter");
        Share share = new Share();
        share.setMarket(mockMarket);
        share.setCommiter(mockCommiter);
        share.setShare(SHARE);

        //When
        when(shareRepository.save(share)).thenReturn(share);
        when(marketRepository.findById(eq(1L))).thenReturn(Optional.of(mockMarket));
        when(commiterRepository.findById(eq(2L))).thenReturn(Optional.of(mockCommiter));
        Share shareSaved = shareService.saveShare(share);

        //Then
        assertNotNull(shareSaved);
        assertEquals(SHARE, shareSaved.getShare());

        verify(shareRepository).save(eq(share)); // Verify service interaction
        verify(marketRepository).findById(eq(1L));
    }
}
