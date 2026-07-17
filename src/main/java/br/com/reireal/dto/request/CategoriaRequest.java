package br.com.reireal.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaRequest {
    
    @NotBlank (message = "Nome da categoria é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    private String nome;

    public CategoriaRequest(){

    }

    public CategoriaRequest(String nome) {
    this.nome = nome;

    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}


