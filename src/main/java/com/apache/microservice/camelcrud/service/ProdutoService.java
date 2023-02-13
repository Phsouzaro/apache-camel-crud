package com.apache.microservice.camelcrud.service;

import com.apache.microservice.camelcrud.controller.response.DetalharProdutoResponse;
import com.apache.microservice.camelcrud.controller.response.ListaProdutoResponse;
import com.apache.microservice.camelcrud.controller.response.ProdutoSimplesResponse;
import com.apache.microservice.camelcrud.domain.Produto;
import com.apache.microservice.camelcrud.camel.route.ProdutoRouter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutoService {

    private final ProducerTemplate producerTemplate;

    public ProdutoSimplesResponse salvarProduto(Produto produto){
        var exchange = createExchange(produto);
        producerTemplate.send(ProdutoRouter.SALVAR_PRODUTO_ROUTE, exchange);
        this.handleException(exchange.getException());
        return exchange.getIn().getBody(ProdutoSimplesResponse.class);
    }

    public ListaProdutoResponse listarProduto() {
        var exchange = createExchange(null);
        producerTemplate.send(ProdutoRouter.ROUTE_LISTAR_PRODUTO, exchange);
        this.handleException(exchange.getException());
        return  exchange.getIn().getBody(ListaProdutoResponse.class);
    }

    public DetalharProdutoResponse detalharProduto(Long id){
        var exchange = createExchange(id);
        producerTemplate.send(ProdutoRouter.ROUTE_DETALHAR_PRODUTO, exchange);
        this.handleException(exchange.getException());
        return exchange.getIn().getBody(DetalharProdutoResponse.class);
    }

    public ProdutoSimplesResponse editarProduto(Long id, Produto request) {
        var exchange = createExchange(request);
        exchange.setProperty("id", id);
        producerTemplate.send(ProdutoRouter.EDITAR_PRODUTO_ROUTE, exchange);
        this.handleException(exchange.getException());
        return exchange.getIn().getBody(ProdutoSimplesResponse.class);
    }

    private Exchange createExchange(Object request){
        return ExchangeBuilder.anExchange(producerTemplate.getCamelContext())
                .withBody(request)
                .build();
    }

    private void handleException(Exception exception){
        if(Objects.nonNull(exception)){
            log.error("Erro ao manipular produto", exception);

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }


}

