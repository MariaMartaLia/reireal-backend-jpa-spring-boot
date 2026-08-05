package br.com.reireal.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.reireal.dto.request.CategoriaRequest;
import br.com.reireal.dto.response.CategoriaResponse;
import br.com.reireal.service.CategoriaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    

    private final CategoriaService service;

    public CategoriaController(CategoriaService service){
        this.service = service ;
    }

    @PostMapping
    public CategoriaResponse criarCategoria( @RequestBody CategoriaRequest request){ 
        return service.cadastrar(request);
        
    }

   @GetMapping("/{id}")
    public CategoriaResponse buscarCategoria(@PathVariable UUID id) {
    return service.buscarPorId(id);
}
    
   @GetMapping
    public List<CategoriaResponse> listarCategorias() { 
       return service.listarTodos();
    
    }

    @PutMapping("/{id}")
public CategoriaResponse atualizarCategoria(
        @PathVariable UUID id,
        @RequestBody CategoriaRequest request) {

    return service.atualizar(id, request);
}

    @DeleteMapping("/{id}")
    public void excluirCategoria(@PathVariable UUID id) {
        service.excluir(id);
    }
}