package br.com.reireal.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProdutoRequest {
    @NotBlank
    @Size(max = 100, min = 3)
    private String nome;
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal valorUnitario;
    @NotNull
    private Integer estoque;
    @NotNull
    private UUID categoriaId;

 public ProdutoRequest() {
 }

public ProdutoRequest(String nome, BigDecimal valorUnitario, Integer estoque,UUID categoriaId) {
    this.nome = nome;
    this.valorUnitario = valorUnitario;
    this.estoque = estoque;
    this.categoriaId = categoriaId;

 }
public String getNome() {
    return nome;
}
public BigDecimal getValorUnitario() {
    return valorUnitario;
}
public Integer getEstoque() {
    return estoque;
}
public UUID getCategoriaId() {
    return categoriaId;
}
public void setNome(String nome) {
    this.nome = nome;
}
public void setValorUnitario(BigDecimal valorUnitario) {
    this.valorUnitario = valorUnitario;
}
 public void setEstoque(Integer estoque) {
    this.estoque = estoque;
 }
 public void setCategoriaId (UUID categoriaId) {
    this.categoriaId = categoriaId;
 }
}
