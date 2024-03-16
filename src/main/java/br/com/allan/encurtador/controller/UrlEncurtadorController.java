package br.com.allan.encurtador.controller;

import br.com.allan.encurtador.Exception.ValidacaoException;
import br.com.allan.encurtador.domain.urlEncurtador.EncurtadorResponse;
import br.com.allan.encurtador.domain.urlEncurtador.Statistics;
import br.com.allan.encurtador.domain.urlEncurtador.UrlEncurtador;
import br.com.allan.encurtador.domain.urlEncurtador.UrlEncurtadorDto;
import br.com.allan.encurtador.exception.AliasException;
import br.com.allan.encurtador.repository.UrlEncurtadorRepository;
import br.com.allan.encurtador.service.UrlEncurtadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encurtador")
@Validated
public class UrlEncurtadorController {

    @Autowired
    private UrlEncurtadorService service;

    @Autowired
    private UrlEncurtadorRepository repository;

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity criarUrlEncurtador(@RequestBody UrlEncurtadorDto dto) {
        long startTime = System.currentTimeMillis();
        try {
            EncurtadorResponse response = service.criar(dto);

            long endTime = System.currentTimeMillis();
            long timeTaken = endTime - startTime;
            String formattedTimeTaken = timeTaken + "ms";

            Statistics statistics = new Statistics(formattedTimeTaken);

            EncurtadorResponse responseComStatistics = EncurtadorResponse.builder()
                    .alias(response.alias())
                    .url(response.url())
                    .urlGerada(response.urlGerada())
                    .statistics(statistics)
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(responseComStatistics);
        } catch (AliasException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/{alias}")
    public ResponseEntity<?> buscarUrlEncurtador(@PathVariable String alias) {
        try {
            EncurtadorResponse response = service.buscarUrlEncurtador(alias);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (ValidacaoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
