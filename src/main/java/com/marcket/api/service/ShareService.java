package com.marcket.api.service;

import com.marcket.api.model.Share;

import java.util.List;

public interface ShareService {

    List<Share> getAllShares();

    Share getShareById(Long id);

    Share saveShare(Share share);

    void deleteShare(Long id);
}

