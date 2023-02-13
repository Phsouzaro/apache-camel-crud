package com.apache.microservice.camelcrud.mapper;

import com.apache.microservice.camelcrud.controller.response.DetalharProdutoResponse;
import com.apache.microservice.camelcrud.controller.response.ProdutoSimplesResponse;
import com.apache.microservice.camelcrud.domain.Produto;

public class ProdutoMapper {

    public static ProdutoSimplesResponse toProdutoSimplesResponse(Produto domain){

        return ProdutoSimplesResponse.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .build();

    }

    public static DetalharProdutoResponse toDetalharProdutoResponse(Produto produto) {
        return DetalharProdutoResponse.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .valor(produto.getValor())
                .quantidade(produto.getQuantidade())
                .build();
    }
}
