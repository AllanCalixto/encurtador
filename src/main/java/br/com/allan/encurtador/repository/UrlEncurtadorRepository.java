package br.com.allan.encurtador.repository;

import br.com.allan.encurtador.domain.urlEncurtador.UrlEncurtador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlEncurtadorRepository extends JpaRepository<UrlEncurtador, Long> {
    Optional<Object> findByAlias(String alias);
}
