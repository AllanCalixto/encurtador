package br.com.allan.encurtador.service;

import br.com.allan.encurtador.Exception.ValidacaoException;
import br.com.allan.encurtador.domain.urlEncurtador.UrlEncurtador;
import br.com.allan.encurtador.domain.urlEncurtador.UrlEncurtadorDto;
import br.com.allan.encurtador.repository.UrlEncurtadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UrlEncurtadorService {

    @Autowired
    private UrlEncurtadorRepository repository;

    public UrlEncurtador criar(UrlEncurtadorDto dto)  {
        UrlEncurtador encurtador = new UrlEncurtador();
        if (dto.url() == null) {
            throw new ValidacaoException("A URL não pode ser nula!");
        }
        if (dto.alias() == null) {
            var alias = aliasAleatorio();
            encurtador.setAlias(alias);
            encurtador.setUrl(dto.url());
            encurtador.setUrlGerada(dto.url() + "/" + alias);
            repository.save(encurtador);
        }
             return encurtador;
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
