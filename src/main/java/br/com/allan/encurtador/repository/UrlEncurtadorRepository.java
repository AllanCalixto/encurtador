package br.com.allan.encurtador.repository;

import br.com.allan.encurtador.domain.urlEncurtador.UrlEncurtador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlEncurtadorRepository extends JpaRepository<UrlEncurtador, Long> {
    boolean existsByAlias(String alias);
}
