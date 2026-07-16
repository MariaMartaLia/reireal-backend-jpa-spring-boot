package br.com.reireal.domain.entity;


import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria") 
public class Categoria {
@Id
@GeneratedValue
@UuidGenerator
private UUID id;

@Column(nullable = false, unique = true, length = 100)
private String nome;

@Column(nullable = false)
private boolean ativo;

public Categoria() {
}

public Categoria(String nome) {
    validarNome(nome);
    this.nome = nome;
    this.ativo = true;
}
public UUID getId() {
    return id;
}
public String getNome() {
    return nome;
}
public boolean isAtivo() {
    return ativo;
}
public void alterarNome(String nome) {
    validarNome(nome);
    this.nome = nome;
}
public void desativarCategoria() {
    if (!ativo) {
        throw new IllegalStateException(
            "Categoria já está desativada."
        );
    }
    ativo = false;
}
public void ativarCategoria() {
    if (ativo) {
        throw new IllegalStateException(
            "Categoria já está ativa."
        );
    }
    ativo = true;
}
private void validarNome(String nome) {
    if (nome == null || nome.isBlank()) {
        throw new IllegalArgumentException(
            "O nome da categoria é obrigatório."
        );
    }
}
}
