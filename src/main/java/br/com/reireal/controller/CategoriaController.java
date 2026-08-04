package br.com.reireal.controller;

import java.util.UUID;

import br.com.reireal.dto.request.CategoriaRequest;
import br.com.reireal.dto.response.CategoriaResponse;
import br.com.reireal.service.CategoriaService;

public class CategoriaController {
    

    private final CategoriaService service;

    public CategoriaController(CategoriaService service){
        this.service = service ;
    }
    public CategoriaResponse criarCategoria(CategoriaRequest request){ 
       CategoriaResponse response = service.cadastrar(request);
        return response;
    }
    public CategoriaResponse buscarPorId(UUID id) { 
        CategoriaResponse response = service.buscarPorId(id);
        return response;
    }
    public CategoriaResponse atualizarCategoria(UUID id,  CategoriaRequest request) {
        CategoriaResponse response = service.atualizar(id, request);
        return response;
    }
    public void exclirCategoria(UUID id) {
        service.excluir(id);
    }
}