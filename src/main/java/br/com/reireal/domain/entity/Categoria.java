package br.com.reireal.domain.entity;


import java.util.UUID;

public class Categoria {
private UUID id;
private String nome;
private boolean ativo;

public Categoria(String nome){
    validarNome(nome);
    this.id = UUID.randomUUID();
    this.nome = nome;
    this.ativo = true;
}
public UUID getId(){
    return id;
}
public String getNome(){
    return nome;
}
public boolean isAtivo(){
    return ativo;
}
public void alterarNome(String nome){
    validarNome(nome);
    this.nome = nome;
}
public void desativarCategoria(){
    if(!ativo){
        throw new IllegalStateException("Categoria já esta desativada.");
    }
    ativo = false;
}
public void ativarCategoria(){
    if(ativo){
        throw new IllegalStateException("Categoria já está ativa.");
    }
    ativo = true;
}
private void validarNome(String nome){
    if(nome == null || nome.isBlank()){
        throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
    }}
}
