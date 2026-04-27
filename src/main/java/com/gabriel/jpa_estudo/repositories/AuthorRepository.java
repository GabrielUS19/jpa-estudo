package com.gabriel.jpa_estudo.repositories;

import com.gabriel.jpa_estudo.models.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, UUID> {
    Optional<AuthorModel> findByNameIgnoreCase(String name);
}
