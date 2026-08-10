package br.com.reireal.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.reireal.dto.request.PagamentoRequest;
import br.com.reireal.dto.response.PagamentoResponse;
import br.com.reireal.service.PagamentoService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

@PostMapping
public PagamentoResponse criarPagamento(@RequestBody PagamentoRequest pagamentoRequest) {
 return service.cadastrar(pagamentoRequest);  
}

@GetMapping("/{id}")
public PagamentoResponse buscarIsPagamento(@PathVariable UUID id) {
    return service.buscar(id);
}

@GetMapping
public List<PagamentoResponse> listarPagamento() {
    return service.listar();
}

@PutMapping("/{id}")
public PagamentoResponse atualizarPagamento(@PathVariable UUID id, @RequestBody PagamentoRequest pagamentoRequest) {
    return service.atualizar(id, pagamentoRequest);
}

@DeleteMapping("/{id}") 
public void excluirPagamento(@PathVariable UUID id) {
    service.excluir(id);
}
}


