package br.com.allan.encurtador.controller;

import br.com.allan.encurtador.domain.urlEncurtador.EncurtadorResponse;
import br.com.allan.encurtador.domain.urlEncurtador.UrlEncurtadorDto;
import br.com.allan.encurtador.service.UrlEncurtadorService;
import jakarta.validation.Valid;
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
        EncurtadorResponse response = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
