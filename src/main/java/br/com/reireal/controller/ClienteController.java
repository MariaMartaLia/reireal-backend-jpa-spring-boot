package br.com.reireal.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import br.com.reireal.dto.request.ClienteRequest;
import br.com.reireal.dto.response.ClienteResponse;
import br.com.reireal.service.ClienteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;





@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service){
        this.service = service;
    }
    
    @PostMapping
    public ClienteResponse criarCliente(@RequestBody ClienteRequest request) {

        return service.cadastrar(request);
    }
    
   @GetMapping("/{id}")
    public ClienteResponse buscarCliente(@PathVariable UUID id) {
    return service.buscar(id);
}
    
    @GetMapping
    public List<ClienteResponse> listarTodos() {
    return service.listarTodos();
}

@PutMapping("/{id}")
public ClienteResponse atualizarCliente(@PathVariable UUID id,
                                        @RequestBody ClienteRequest request) {
    return service.atualizar(id, request);
}

    @DeleteMapping("/{id}")
    public void excluirCliente(@PathVariable UUID id) {
        service.excluir(id);
    }
}
