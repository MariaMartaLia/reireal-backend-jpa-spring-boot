package br.com.reireal.dto.response;

import java.util.UUID;

public class CategoriaResponse {

    private UUID id;
    private String nome;
    private boolean ativo;

    public CategoriaResponse(UUID id, String nome, boolean ativo) {

        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
    }
    public UUID getId() {
        return id;
    }
    public String getNome(){
        return nome;
    }
    public boolean isAtivo(){
        return ativo;
    }
}
