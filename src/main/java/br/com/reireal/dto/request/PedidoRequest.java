package br.com.reireal.dto.request;


import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;


public class PedidoRequest {

@Valid
@NotEmpty    
private List<ItemPedidoRequest> itens;

public PedidoRequest(){
}
public PedidoRequest(List<ItemPedidoRequest> itens) {
    this.itens = itens;
}
public List<ItemPedidoRequest> getItens(){
    return itens;
}
public void setItens(List<ItemPedidoRequest> itens) {
    this.itens = itens;
}
}
