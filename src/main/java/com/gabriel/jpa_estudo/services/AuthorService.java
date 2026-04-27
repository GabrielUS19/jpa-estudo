package com.gabriel.jpa_estudo.services;

import com.gabriel.jpa_estudo.dto.AuthorResponse;
import com.gabriel.jpa_estudo.models.AuthorModel;
import com.gabriel.jpa_estudo.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorResponse save(String name) {
        var author = new AuthorModel();

        author.setName(name);

        var savedAuthor = authorRepository.save(author);

        return new AuthorResponse(savedAuthor.getId(), savedAuthor.getName());
    }

    public List<AuthorResponse> getAll() {
        return authorRepository
                .findAll()
                .stream()
                .map(authorModel -> new AuthorResponse(
                        authorModel.getId(),
                        authorModel.getName()
                ))
                .toList();
    }

    public AuthorResponse getById(UUID authorId) {
        return authorRepository
                .findById(authorId)
                .map(authorModel -> new AuthorResponse(
                        authorModel.getId(),
                        authorModel.getName()
                ))
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
    }
}
