package br.com.allan.encurtador.service;

import br.com.allan.encurtador.Exception.ValidacaoException;
import br.com.allan.encurtador.domain.urlEncurtador.EncurtadorResponse;
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

    public EncurtadorResponse criar(UrlEncurtadorDto dto)  {
        if (dto.url() == null) {
            throw new ValidacaoException("A URL não pode ser nula!");
        }
        String alias;
        if (dto.alias() == null) {
            alias = aliasAleatorio();;
            String url = dto.url();
            String urlGerada = url + "/" + alias;
            EncurtadorResponse response = new EncurtadorResponse(alias, url, urlGerada);
            System.out.println("RESPONSE: " + response);
        } else {
            alias = dto.alias();
        }
        String urlGerada = dto.url() + "/" + alias;
        return new EncurtadorResponse(alias, dto.url(), urlGerada);
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
