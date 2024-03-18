package br.com.allan.encurtador.domain.urlEncurtador.dto;

import lombok.Builder;

@Builder
public record EncurtadorResponse(String alias, String url, String urlGerada) {
}
