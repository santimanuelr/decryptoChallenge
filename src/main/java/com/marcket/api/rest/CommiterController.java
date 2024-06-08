package com.marcket.api.rest;

import com.marcket.api.model.Commiter;
import com.marcket.api.service.CommiterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commiters")
public class CommiterController {

    private final CommiterService commiterService;

    public CommiterController(CommiterService commiterService) {
        this.commiterService = commiterService;
    }

    @GetMapping
    public ResponseEntity<List<Commiter>> getAllCommiters() {
        return ResponseEntity.ok(commiterService.getAllCommiters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commiter> getCommiterById(@PathVariable Long id) {
        Commiter commiter = commiterService.getCommiterById(id);
        if (commiter == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(commiter);
    }

    @PostMapping
    public ResponseEntity<Commiter> createCommiter(@RequestBody Commiter commiter) {
        Commiter savedCommiter = commiterService.saveCommiter(commiter);
        return ResponseEntity.ok(savedCommiter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commiter> updateCommiter(@PathVariable Long id, @RequestBody Commiter commiter) {
        Commiter existingCommiter = commiterService.getCommiterById(id);
        if (existingCommiter == null) {
            return ResponseEntity.notFound().build();
        }
        commiter.setId(id); // Set the ID to avoid creating a new entity
        Commiter updatedCommiter = commiterService.saveCommiter(commiter);
        return ResponseEntity.ok(updatedCommiter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommiter(@PathVariable Long id) {
        commiterService.deleteCommiter(id);
        return ResponseEntity.noContent().build();
    }
}

