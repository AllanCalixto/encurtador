package br.com.allan.encurtador.controller;

import br.com.allan.encurtador.domain.urlEncurtador.EncurtadorResponse;
import br.com.allan.encurtador.domain.urlEncurtador.Statistics;
import br.com.allan.encurtador.domain.urlEncurtador.UrlEncurtadorDto;
import br.com.allan.encurtador.service.UrlEncurtadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/encurtador")
@Validated
public class UrlEncurtadorController {

    @Autowired
    private UrlEncurtadorService service;

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<EncurtadorResponse> criarUrlEncurtador(@RequestBody UrlEncurtadorDto dto) {
        long startTime = System.currentTimeMillis();

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
    }
}
