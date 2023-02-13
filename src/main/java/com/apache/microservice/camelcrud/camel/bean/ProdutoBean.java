package com.apache.microservice.camelcrud.camel.bean;

import com.apache.microservice.camelcrud.camel.route.ProdutoRouter;
import com.apache.microservice.camelcrud.domain.Produto;
import com.apache.microservice.camelcrud.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProdutoBean {

    private final ProdutoRepository repository;

    @Transactional
    public Produto salvarProduto(Exchange exchange){
        Produto produto = exchange.getProperty(ProdutoRouter.REQUEST_PROPERTY, Produto.class);
        Produto response =  repository.save(produto);
        exchange.setProperty(ProdutoRouter.RESPONSE_PROPERTY, response);
        return response;
    }

    public List<Produto> listarProdutos(Exchange exchange){
        List<Produto> produtos =  repository.findAll();
        exchange.setProperty(ProdutoRouter.RESPONSE_PROPERTY, produtos);
        return produtos;
    }

    public Optional<Produto> detalharProduto(Exchange exchange){
        Long id = exchange.getProperty(ProdutoRouter.REQUEST_PROPERTY, Long.class);
        Optional<Produto> produtoOpt = repository.findById(id);
        exchange.setProperty(ProdutoRouter.RESPONSE_PROPERTY, produtoOpt);
        return produtoOpt;
    }

    public void editarProduto(Exchange exchange){
        Long id = exchange.getProperty("id", Long.class);
        Produto edit = exchange.getProperty(ProdutoRouter.REQUEST_PROPERTY, Produto.class);
        var domainOpt = repository.findById(id);
        if(domainOpt.isPresent()){
            var domain = domainOpt.get();
            domain.setNome(edit.getNome());
            domain.setDescricao(edit.getDescricao());
            domain.setValor(edit.getValor());
            domain.setQuantidade(edit.getQuantidade());

            repository.save(domain);

            exchange.setProperty(ProdutoRouter.RESPONSE_PROPERTY, domain);
        }else{
            exchange.setException(new RuntimeException("Produto não existe"));
        }
    }
}
