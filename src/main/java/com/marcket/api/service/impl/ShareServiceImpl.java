package com.marcket.api.service.impl;

import com.marcket.api.model.Commiter;
import com.marcket.api.model.Market;
import com.marcket.api.model.Share;
import com.marcket.api.repository.CommiterRepository;
import com.marcket.api.repository.MarketRepository;
import com.marcket.api.repository.ShareRepository;
import com.marcket.api.service.ShareService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;

    private final MarketRepository marketRepository;

    private final CommiterRepository commiterRepository;

    public ShareServiceImpl(ShareRepository shareRepository, MarketRepository marketRepository, CommiterRepository commiterRepository) {
        this.shareRepository = shareRepository;
        this.marketRepository = marketRepository;
        this.commiterRepository = commiterRepository;
    }

    @Override
    public List<Share> getAllShares() {
        return shareRepository.findAll();
    }

    @Override
    public Share getShareById(Long id) {
        return shareRepository.findById(id).orElse(null);
    }

    @Override
    public Share saveShare(Share share) {
        Market market = marketRepository.findById(share.getMarket().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Market not found"));
        share.setMarket(market);
        Commiter commiter = commiterRepository.findById(share.getCommiter().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Commiter not found"));
        share.setCommiter(commiter);
        return shareRepository.save(share);
    }

    @Override
    public void deleteShare(Long id) {
        shareRepository.deleteById(id);
    }
}

