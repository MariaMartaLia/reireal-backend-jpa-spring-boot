package br.com.reireal.dto.response;

import java.math.BigDecimal;
import java.util.UUID;


public class ItemPedidoResponse {
    private UUID id;
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal subTotal;
    private UUID produtoId;
    private String produtoNome;
   
public ItemPedidoResponse(UUID id, Integer quantidade, BigDecimal valorUnitario, BigDecimal subTotal, UUID produtoId, String produtoNome) {
    this.id = id;
    this.quantidade = quantidade;
    this.valorUnitario = valorUnitario;
    this.subTotal = subTotal;
    this.produtoId = produtoId;
    this.produtoNome = produtoNome;
}
public UUID getId() {
    return id;
}
public Integer getQuantidade() {
    return quantidade;
}
public BigDecimal getValorUnitario() {
    return valorUnitario;
}
public BigDecimal getSubTotal() {
    return subTotal;
}
public UUID getProdutoId() {
    return produtoId;
}
public String getProdutoNome() {
    return produtoNome;
}
}
