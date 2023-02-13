package com.apache.microservice.camelcrud.camel.processor;

import com.apache.microservice.camelcrud.camel.route.ProdutoRouter;
import com.apache.microservice.camelcrud.controller.response.DetalharProdutoResponse;
import com.apache.microservice.camelcrud.domain.Produto;
import com.apache.microservice.camelcrud.mapper.ProdutoMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Optional;


public class ProdutoDetalhadoProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Optional<Produto> domain = exchange.getProperty(ProdutoRouter.RESPONSE_PROPERTY, Optional.class);

        if(domain.isPresent()){
            DetalharProdutoResponse response = ProdutoMapper.toDetalharProdutoResponse(domain.get());

            exchange.getMessage().setBody(response);
        }else {
         exchange.setException(new RuntimeException("Produto não encontrado"));
        }
    }
}
