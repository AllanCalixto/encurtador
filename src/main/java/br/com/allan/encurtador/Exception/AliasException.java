package br.com.allan.encurtador.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AliasException extends RuntimeException{

    private String alias;
    private String errCode;
    private String description;
}
