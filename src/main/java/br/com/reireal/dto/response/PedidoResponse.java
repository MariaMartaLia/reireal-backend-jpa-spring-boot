package br.com.reireal.dto.response;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.reireal.domain.enums.StatusPedido;

public class PedidoResponse{
private UUID id;
private LocalDateTime dataPedido;
private BigDecimal total;
private StatusPedido status;
private UUID clienteId;
private List<ItemPedidoResponse> itens;



public PedidoResponse(UUID id, LocalDateTime dataPedido, BigDecimal total, StatusPedido status, UUID clienteId, List<ItemPedidoResponse> itens) {
this.id = id;
this.dataPedido = dataPedido;
this.total = total;
this.status = status;
this.clienteId = clienteId;
this.itens = itens;
}
public UUID getId() {
    return id;
}
public LocalDateTime getDataPedido() {
    return dataPedido;
}
public BigDecimal getTotal() {
    return total;
}
public StatusPedido getStatus() {
    return status;
}
public UUID getClienteId() {
    return clienteId;
}
public List<ItemPedidoResponse> getItens() {
    return itens;
}
}
