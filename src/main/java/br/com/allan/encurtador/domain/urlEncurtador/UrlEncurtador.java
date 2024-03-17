package br.com.allan.encurtador.domain.urlEncurtador;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity(name = "UrlEncurtador")
@Table(name = "urlencurtador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UrlEncurtador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alias;

    @NotBlank
    private String url;

    @NotBlank
    private String urlGerada;

    private int acessos;

}
