package br.dev.andregurgel.linkshortener.api.shortener;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortenerRepository extends JpaRepository<Shortener, Long> {
    Optional<Shortener> findByShortenedCode(String shortenedCode);
}
