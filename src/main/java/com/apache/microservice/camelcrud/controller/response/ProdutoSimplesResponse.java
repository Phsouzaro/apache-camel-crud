package com.apache.microservice.camelcrud.controller.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProdutoSimplesResponse {

    private Long id;

    private String nome;
}
