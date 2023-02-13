package com.apache.microservice.camelcrud.camel.route;

import com.apache.microservice.camelcrud.camel.bean.ProdutoBean;
import com.apache.microservice.camelcrud.camel.processor.EditarProdutoProcessor;
import com.apache.microservice.camelcrud.camel.processor.ListaProdutoProcessor;
import com.apache.microservice.camelcrud.camel.processor.ProdutoDetalhadoProcessor;
import com.apache.microservice.camelcrud.camel.processor.ProdutoProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;


@Component
@RequiredArgsConstructor
public class ProdutoRouter extends RouteBuilder {

    public static final String REQUEST_PROPERTY = "request";
    public static final String SALVAR_PRODUTO_ROUTE = "direct:salvarProduto";
    public static final String ROUTE_LISTAR_PRODUTO = "direct:listarProdutos";
    public static final String ROUTE_DETALHAR_PRODUTO = "direct:detalharProduto";
    public static final String EDITAR_PRODUTO_ROUTE = "direct:editarProduto";
    public static final String RESPONSE_PROPERTY = "response";
    private final ProdutoBean bean;

    @Override
    public void configure() throws Exception {
        setupSalvarProdutoRoute();
        setupListarProdutoRoute();
        setupDetalharProdutoRoute();
        setupEditarProdutoRoute();
    }

    private void setupSalvarProdutoRoute(){
        from(SALVAR_PRODUTO_ROUTE)
                .process(exchange -> exchange.setProperty(REQUEST_PROPERTY, exchange.getIn().getBody()))
                .bean(bean, "salvarProduto")
                .process(new ProdutoProcessor())
                .end();
    }

    private void setupListarProdutoRoute(){
        from(ROUTE_LISTAR_PRODUTO)
                .bean(bean, "listarProdutos")
                .choice()
                    .when(exchange -> exchange.getProperty(RESPONSE_PROPERTY, List.class).isEmpty())
                        .throwException(new RuntimeException("Lista sem produtos"))
                    .otherwise()
                .process(new ListaProdutoProcessor())
                .end();
    }

    private void setupDetalharProdutoRoute() {
        from(ROUTE_DETALHAR_PRODUTO)
                .process(exchange -> exchange.setProperty(REQUEST_PROPERTY, exchange.getIn().getBody()))
                .bean(bean, "detalharProduto")
                .process(new ProdutoDetalhadoProcessor())
                .end();
    }

    private void setupEditarProdutoRoute(){
        from(EDITAR_PRODUTO_ROUTE)
                .process(exchange -> exchange.setProperty(REQUEST_PROPERTY, exchange.getIn().getBody()))
                .bean(bean, "editarProduto")
                .choice()
                    .when(exchange -> Objects.isNull(exchange.getException()))
                        .process(new EditarProdutoProcessor())
                .end();
    }
}
