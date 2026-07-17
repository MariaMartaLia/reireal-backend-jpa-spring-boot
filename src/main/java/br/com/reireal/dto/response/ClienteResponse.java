package br.com.reireal.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ClienteResponse {
    private UUID id;
    private String nome;
    private String telefone;
    private String email ;
    private LocalDate dataNascimento;
    private BigDecimal credito;
    private boolean ativo;
    private LocalDate dataCadastro;

 
public ClienteResponse(UUID id, String nome, String telefone, String email, LocalDate dataNascimento, BigDecimal credito, boolean ativo, LocalDate dataCadastro) {
    this.id = id;
    this.nome = nome;
    this.telefone = telefone;
    this.email = email;
    this.dataNascimento = dataNascimento;
    this.credito = credito;
    this.ativo = ativo;
    this.dataCadastro = dataCadastro;
    }
    
public UUID getId () {
    return id;
}
public String getNome() {
    return nome;
}
public String getTelefone() {
    return telefone;
}
public String getEmail() {
    return email;
}
public LocalDate getDataNascimento() {
    return dataNascimento;
}
public BigDecimal getCredito() {
    return credito;
}
public boolean isAtivo() {
    return ativo;
}
public LocalDate getDataCadastro() {
    return dataCadastro;
}

}
