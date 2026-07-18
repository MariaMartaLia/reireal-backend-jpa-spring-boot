package br.com.reireal.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class ItemPedidoRequest {
    @NotNull
    @Positive
    private Integer quantidade;
    @NotNull
    private UUID produtoId;
   
public ItemPedidoRequest() {
}
public ItemPedidoRequest(Integer quantidade, UUID produtoId) {
    this.quantidade = quantidade;
    this.produtoId = produtoId;
}

public Integer getQuantidade() {
    return quantidade;
}
public UUID getProdutoId() {
    return produtoId;
}
public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
}
public void setProdutoId(UUID produtoId) {
    this.produtoId = produtoId;
}
}
