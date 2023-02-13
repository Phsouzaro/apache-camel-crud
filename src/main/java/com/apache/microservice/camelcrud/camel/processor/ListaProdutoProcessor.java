package com.apache.microservice.camelcrud.camel.processor;

import com.apache.microservice.camelcrud.camel.route.ProdutoRouter;
import com.apache.microservice.camelcrud.controller.response.ListaProdutoResponse;
import com.apache.microservice.camelcrud.controller.response.ProdutoSimplesResponse;
import com.apache.microservice.camelcrud.domain.Produto;
import com.apache.microservice.camelcrud.mapper.ProdutoMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;
import java.util.stream.Collectors;

public class ListaProdutoProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        List<Produto> prods = exchange.getProperty(ProdutoRouter.RESPONSE_PROPERTY, List.class);

        ListaProdutoResponse listaResponse = ListaProdutoResponse.builder()
                .produtos(this.converterProdutosParaResponse(prods))
                .build();

        exchange.getMessage().setBody(listaResponse);
    }

    private List<ProdutoSimplesResponse> converterProdutosParaResponse(List<Produto> produtos){
        return produtos.stream().map(ProdutoMapper::toProdutoSimplesResponse)
                .collect(Collectors.toList());
    }
}
