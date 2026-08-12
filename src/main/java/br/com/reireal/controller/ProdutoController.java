package br.com.reireal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.reireal.dto.request.ProdutoRequest;
import br.com.reireal.dto.response.ProdutoResponse;
import br.com.reireal.service.ProdutoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
  
@PostMapping
public ProdutoResponse cadastrarProduto(@RequestBody ProdutoRequest produtoRequest) {
    return produtoService.cadastrar(produtoRequest);
}

@GetMapping("/{id}")
public ProdutoResponse buscarPedido(@PathVariable UUID id) {
    return produtoService.buscar(id);
}

@GetMapping
public List<ProdutoResponse> listarProduto() {
    return produtoService.listar();
}

@PutMapping("/{id}")
public ProdutoResponse atualizarProduto(@PathVariable UUID id, @RequestBody ProdutoRequest produtoRequest) {
    return produtoService.atualizar(id, produtoRequest);
}

@DeleteMapping("/{id}")
public void excluirProduto(@PathVariable UUID id) {
    produtoService.excluir(id);
}
}




