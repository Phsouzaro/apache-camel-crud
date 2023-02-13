package com.apache.microservice.camelcrud.controller.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListaProdutoResponse {

    private List<ProdutoSimplesResponse> produtos;
}
