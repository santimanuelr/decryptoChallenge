package com.marcket.api.rest;

import com.marcket.api.model.dto.CountryStats;
import com.marcket.api.service.MarketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final MarketService marketService;

    public StatsController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CountryStats>> getShareById(@PathVariable Long id) {
        //todo
        return ResponseEntity.ok(null);
    }
}
