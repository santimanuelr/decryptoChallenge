package com.marcket.api.rest;

import com.marcket.api.model.Share;
import com.marcket.api.service.ShareService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shares")
public class ShareController {

    private final ShareService shareService;

    public ShareController(ShareService shareService) {
        this.shareService = shareService;
    }

    @GetMapping
    public ResponseEntity<List<Share>> getAllShares() {
        return ResponseEntity.ok(shareService.getAllShares());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Share> getShareById(@PathVariable Long id) {
        Share share = shareService.getShareById(id);
        if (share == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(share);
    }

    @PostMapping
    public ResponseEntity<Share> createShare(@RequestBody Share share) {
        Share savedShare = shareService.saveShare(share);
        return ResponseEntity.ok(savedShare);
    }

    // Update method with some considerations

    @PutMapping("/{id}")
    public ResponseEntity<Share> updateShare(@PathVariable Long id, @RequestBody Share share) {
        // Considerations for updating shares:
        // - Updating a share might involve complex logic related to market and commiter relationships.
        // - You might need to check if the provided market and commiter IDs exist before updating.
        // - Consider implementing a partial update approach where only specific fields are updated.

        // This example demonstrates a basic update with potential issues:
        Share existingShare = shareService.getShareById(id);
        if (existingShare == null) {
            return ResponseEntity.notFound().build();
        }
        existingShare.setMarket(share.getMarket()); // Update market (might require checks)
        existingShare.setCommiter(share.getCommiter()); // Update commiter (might require checks)
        existingShare.setShare(share.getShare()); // Update share value
        Share updatedShare = shareService.saveShare(existingShare);
        return ResponseEntity.ok(updatedShare);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShare(@PathVariable Long id) {
        shareService.deleteShare(id);
        return ResponseEntity.noContent().build();
    }
}

