package com.gabriel.jpa_estudo.controllers;

import com.gabriel.jpa_estudo.dto.AuthorRequest;
import com.gabriel.jpa_estudo.dto.AuthorResponse;
import com.gabriel.jpa_estudo.services.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> save(@RequestBody AuthorRequest authorRequest) {
        var newAuthor = authorService.save(authorRequest.name());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAuthor.id())
                .toUri();

        return ResponseEntity.created(location).body(newAuthor);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAll() {
        return ResponseEntity.ok().body(authorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(authorService.getById(id));
    }
}
