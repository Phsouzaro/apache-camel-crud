package com.apache.microservice.camelcrud.controller.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DetalharProdutoResponse {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal valor;

    private int quantidade;
}
