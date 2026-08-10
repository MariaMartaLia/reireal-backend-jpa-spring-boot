package br.com.reireal.controller;

import br.com.reireal.service.PedidoService;
import org.springframework.web.bind.annotation.RestController;

import br.com.reireal.dto.request.PedidoRequest;
import br.com.reireal.dto.response.PedidoResponse;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
   

    public PedidoController ( PedidoService pedidoService) {
      
        this.pedidoService = pedidoService;
    }
@PostMapping
public PedidoResponse cadastrarPedido(@RequestBody PedidoRequest pedidoRequest) {
    return pedidoService.cadastrar(pedidoRequest);
}

@GetMapping("/{id}")
public PedidoResponse buscarPedido(@PathVariable UUID id) {
    return pedidoService.buscar(id);
}

@GetMapping
public List<PedidoResponse> listarPedido() {
    return pedidoService.listar();
}
@PutMapping("/{id}")
public PedidoResponse atualizarPedido(@PathVariable UUID id, @RequestBody PedidoRequest pedidoRequest) {
    return pedidoService.atualizar(id, pedidoRequest);
}

@DeleteMapping("/{id}")
public void excluirPedido(@PathVariable UUID id) {
    pedidoService.excluir(id);
}

}



    


