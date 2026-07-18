package br.com.reireal.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.reireal.domain.enums.StatusPagamento;
import br.com.reireal.domain.enums.TipoPagamento;

public class PagamentoResponse {
private UUID id;
private TipoPagamento tipo;
private BigDecimal valor;
private LocalDateTime dataPagamento;
private StatusPagamento status;
private UUID pedidoId;

public PagamentoResponse(UUID id, TipoPagamento tipo, BigDecimal valor, LocalDateTime dataPagamento, StatusPagamento status, UUID pedidoId) {
    this.id = id;
    this.tipo = tipo;
    this.valor = valor;
    this.dataPagamento = dataPagamento;
    this.status = status;
    this.pedidoId = pedidoId;
}
public UUID getId() {
    return id;
}
public TipoPagamento getTipo() {
    return tipo;
}
public BigDecimal getValor() {
    return valor;
}
public LocalDateTime getDataPagamento() {
    return dataPagamento;
}
public StatusPagamento getStatus() {
    return status;
}
public UUID getPedidoId() {
    return pedidoId;
}
}
