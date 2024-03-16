package br.com.allan.encurtador.controller;

import br.com.allan.encurtador.domain.urlEncurtador.EncurtadorResponse;
import br.com.allan.encurtador.domain.urlEncurtador.UrlEncurtadorDto;
import br.com.allan.encurtador.exception.AliasException;
import br.com.allan.encurtador.service.UrlEncurtadorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UrlEncurtadorControllerTest {

    @Mock
    private UrlEncurtadorService urlEncurtadorService;

    @InjectMocks
    private UrlEncurtadorController urlEncurtadorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarUrlEncurtador_Success() {
        UrlEncurtadorDto dto = new UrlEncurtadorDto("http://teste.com/alias", null, null);
        EncurtadorResponse response = new EncurtadorResponse("alias", "http://teste.com", "http://teste.com/alias", null);
        when(urlEncurtadorService.criar(dto)).thenReturn(response);

        EncurtadorResponse expectedResponse = EncurtadorResponse.builder()
                .alias("alias")
                .url("http://teste.com")
                .urlGerada("http://teste.com/alias")
                .statistics(null)
                .build();
    }

    @Test
    public void testCriarUrlEncurtador_AliasException() {
        UrlEncurtadorDto dto = new UrlEncurtadorDto(null, null,"http://teste.com/alias");
        when(urlEncurtadorService.criar(dto)).thenThrow(new AliasException("alias", "001", "CUSTOM ALIAS ALREADY EXISTS"));

        ResponseEntity result = urlEncurtadorController.criarUrlEncurtador(dto);

        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
    }

}