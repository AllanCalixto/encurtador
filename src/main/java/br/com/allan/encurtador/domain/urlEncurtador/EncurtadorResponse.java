package br.com.allan.encurtador.domain.urlEncurtador;

import lombok.Builder;
import lombok.Data;

@Builder
public record EncurtadorResponse(String alias, String url, String urlGerada, Statistics statistics) {

}
