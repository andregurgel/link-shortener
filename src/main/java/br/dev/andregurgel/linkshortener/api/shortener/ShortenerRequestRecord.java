package br.dev.andregurgel.linkshortener.api.shortener;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ShortenerRequestRecord(@NotNull @Size(max = 1024) String urlToShort) {
}
