package com.apache.microservice.camelcrud.controller;

import com.apache.microservice.camelcrud.controller.response.DetalharProdutoResponse;
import com.apache.microservice.camelcrud.controller.response.ListaProdutoResponse;
import com.apache.microservice.camelcrud.controller.response.ProdutoSimplesResponse;
import com.apache.microservice.camelcrud.domain.Produto;
import com.apache.microservice.camelcrud.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoSimplesResponse> salvarProduto(@RequestBody Produto request){
        return ResponseEntity.ok(service.salvarProduto(request));
    }

    @GetMapping
    public ResponseEntity<ListaProdutoResponse> listarProduto(){
        return ResponseEntity.ok(service.listarProduto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharProdutoResponse> detalharProduto(@PathVariable Long id){
        return ResponseEntity.ok(service.detalharProduto(id));
    }

    @PostMapping("{id}")
    public ResponseEntity<ProdutoSimplesResponse> editarProduto(@PathVariable Long id, @RequestBody Produto request){
        return ResponseEntity.ok(service.editarProduto(id, request));
    }
}
