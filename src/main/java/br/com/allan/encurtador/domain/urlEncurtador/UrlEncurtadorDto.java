package br.com.allan.encurtador.domain.urlEncurtador;

import jakarta.validation.constraints.NotBlank;

public record UrlEncurtadorDto(
        String alias,
        @NotBlank
        String url,
        String urlGerada
) {
}
