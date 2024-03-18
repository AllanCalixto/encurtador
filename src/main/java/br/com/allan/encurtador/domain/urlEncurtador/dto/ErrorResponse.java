package br.com.allan.encurtador.domain.urlEncurtador.dto;

import lombok.Builder;

@Builder
public record ErrorResponse(String alias, String errCode, String description) {
}
