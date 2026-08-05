package br.com.reireal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.reireal.dto.request.ItemPedidoRequest;
import br.com.reireal.dto.response.ItemPedidoResponse;
import br.com.reireal.service.ItemPedidoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController {

    private final ItemPedidoService service;

    public ItemPedidoController(ItemPedidoService service) {
        this.service = service;
    }
    
@PostMapping
public ItemPedidoResponse criarItemPedido(@RequestBody ItemPedidoRequest request) {
    return service.cadastrar(request);
}

@GetMapping("/{id}")
public ItemPedidoResponse buscarItemPedido(@PathVariable UUID id) {
    return service.buscar(id);
}

@GetMapping
public List<ItemPedidoResponse> listarItemPedido() {
    return service.listarTodos();
}

@PutMapping("/{id}")
public ItemPedidoResponse atualizarItemPedido(@PathVariable UUID id, @RequestBody ItemPedidoRequest request) {
    return service.atualizar(id, request);
}

@DeleteMapping("/{id}")
public void excluirItemPedido(@PathVariable UUID id) {
    service.excluir(id);
    }   
}