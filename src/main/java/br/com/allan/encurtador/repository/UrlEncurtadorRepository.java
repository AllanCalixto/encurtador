package br.com.allan.encurtador.repository;

import br.com.allan.encurtador.domain.urlEncurtador.UrlEncurtador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UrlEncurtadorRepository extends JpaRepository<UrlEncurtador, Long> {
    Optional<Object> findByAlias(String alias);

    @Query("SELECT u FROM UrlEncurtador u ORDER BY u.acessos DESC")
    List<UrlEncurtador> findByTop10();
}
