package br.com.allan.encurtador.service;

import br.com.allan.encurtador.domain.urlEncurtador.EncurtadorResponse;
import br.com.allan.encurtador.domain.urlEncurtador.Statistics;
import br.com.allan.encurtador.domain.urlEncurtador.UrlEncurtador;
import br.com.allan.encurtador.domain.urlEncurtador.UrlEncurtadorDto;
import br.com.allan.encurtador.exception.AliasException;
import br.com.allan.encurtador.exception.ValidacaoException;
import br.com.allan.encurtador.repository.UrlEncurtadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UrlEncurtadorService {

    @Autowired
    private UrlEncurtadorRepository repository;

    public EncurtadorResponse criar(UrlEncurtadorDto dto) {
        long startTime = System.currentTimeMillis();
        if (dto.url() == null || dto.url().isEmpty()) {
            throw new ValidacaoException("A URL NÃO PODE SER NULA!");
        }

        String alias;
        if (dto.alias() == null) {
            alias = aliasAleatorio();
        } else {
            alias = dto.alias();
        }

        if (repository.findByAlias(alias).isPresent()) {
            throw new AliasException(alias, "001", "CUSTOM ALIAS ALREADY EXISTS");
        }

        String urlGerada = dto.url() + "/" + alias;
        UrlEncurtador encurtador = new UrlEncurtador(null, alias, dto.url(), urlGerada, 0);
        repository.save(encurtador);

        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        String formattedTimeTaken = timeTaken + "ms";

        Statistics statistics = new Statistics(formattedTimeTaken);

        return EncurtadorResponse.builder().alias(alias).url(dto.url()).urlGerada(urlGerada).statistics(statistics).build();
    }

    public EncurtadorResponse buscarUrlEncurtador(String alias) {
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        String formattedTimeTaken = timeTaken + "ms";

        Optional<Object> urlEncurtadorOptional = repository.findByAlias(alias);
        if (urlEncurtadorOptional.isPresent()) {
            UrlEncurtador urlEncurtador = (UrlEncurtador) urlEncurtadorOptional.get();
            urlEncurtador.setAcessos(urlEncurtador.getAcessos() + 1);
            repository.save(urlEncurtador);
            return EncurtadorResponse.builder()
                    .alias(urlEncurtador.getAlias())
                    .url(urlEncurtador.getUrl())
                    .urlGerada(urlEncurtador.getUrlGerada())
                    .statistics(new Statistics(formattedTimeTaken))
                    .build();
        } else {
            throw new ValidacaoException("SHORTENED URL NOT FOUND: " + alias);
        }
    }

    public List<EncurtadorResponse> findByTop10() {
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        String formattedTimeTaken = timeTaken + "ms";
        List<UrlEncurtador> top10 = repository.findByTop10();
        return top10.stream()
                .sorted(Comparator.comparingInt(UrlEncurtador::getAcessos).reversed())
                .map(urlEncurtador -> EncurtadorResponse.builder()
                        .alias(urlEncurtador.getAlias())
                        .url(urlEncurtador.getUrl())
                        .urlGerada(urlEncurtador.getUrlGerada())
                        .statistics(new Statistics(formattedTimeTaken))
                        .build())
                .collect(Collectors.toList());
    }

    private String aliasAleatorio() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[4];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder();
        String aliasAleatorio = encoder.encodeToString(bytes);
        return aliasAleatorio.substring(0, 6);
    }


}
