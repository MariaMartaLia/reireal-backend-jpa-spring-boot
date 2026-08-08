package br.com.reireal.dto.request;


import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;


public class PedidoRequest {

private UUID clienteId;
@Valid
@NotEmpty    
private List<ItemPedidoRequest> itens;


public PedidoRequest(){
}
public PedidoRequest(UUID clienteId, List<ItemPedidoRequest> itens) {
    this.clienteId = clienteId;
    this.itens = itens; 
}

public UUID getClienteId() {
    return clienteId;
}
public List<ItemPedidoRequest> getItens(){
    return itens;
}
public void setItens(List<ItemPedidoRequest> itens) {
    this.itens = itens;
}
}
