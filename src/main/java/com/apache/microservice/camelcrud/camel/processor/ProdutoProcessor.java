package com.apache.microservice.camelcrud.camel.processor;

import com.apache.microservice.camelcrud.camel.route.ProdutoRouter;
import com.apache.microservice.camelcrud.controller.response.ProdutoSimplesResponse;
import com.apache.microservice.camelcrud.domain.Produto;
import com.apache.microservice.camelcrud.mapper.ProdutoMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ProdutoProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {
        Produto prod = exchange.getProperty(ProdutoRouter.REQUEST_PROPERTY, Produto.class);

        ProdutoSimplesResponse response = ProdutoMapper.toProdutoSimplesResponse(prod);

        exchange.getMessage().setBody(response);
    }
}
