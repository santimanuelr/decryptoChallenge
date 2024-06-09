package com.marcket.api.service.impl;

import com.marcket.api.model.Share;
import com.marcket.api.repository.ShareRepository;
import com.marcket.api.service.ShareService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;

    public ShareServiceImpl(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
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
        // Consider validations and checks before saving
        return shareRepository.save(share);
    }

    @Override
    public void deleteShare(Long id) {
        shareRepository.deleteById(id);
    }
}

