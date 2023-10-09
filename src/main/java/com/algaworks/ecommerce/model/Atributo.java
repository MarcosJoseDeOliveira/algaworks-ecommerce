package com.algaworks.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Atributo {

    @NotBlank
    @Column(length = 100, nullable = false)
    private String nome;

    @NotBlank
    private String valor;

}
