package com.marcket.api.rest;

import com.marcket.api.model.Market;
import com.marcket.api.service.MarketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/markets")
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping
    public ResponseEntity<List<Market>> getAllMarkets() {
        return ResponseEntity.ok(marketService.getAllMarkets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Market> getMarketById(@PathVariable Long id) {
        Market market = marketService.getMarketById(id);
        if (market == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(market);
    }

    @PostMapping
    public ResponseEntity<Market> createMarket(@RequestBody Market market) {
        Market savedMarket = marketService.saveMarket(market);
        return ResponseEntity.ok(savedMarket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Market> updateMarket(@PathVariable Long id, @RequestBody Market market) {
        Market existingMarket = marketService.getMarketById(id);
        if (existingMarket == null) {
            return ResponseEntity.notFound().build();
        }
        market.setId(id); // Set the ID to avoid creating a new entity
        Market updatedMarket = marketService.saveMarket(market);
        return ResponseEntity.ok(updatedMarket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarket(@PathVariable Long id) {
        marketService.deleteMarket(id);
        return ResponseEntity.noContent().build();
    }
}

