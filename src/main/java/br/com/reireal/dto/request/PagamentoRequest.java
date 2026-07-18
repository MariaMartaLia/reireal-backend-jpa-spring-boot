package br.com.reireal.dto.request;


import java.util.UUID;


import br.com.reireal.domain.enums.TipoPagamento;
import jakarta.validation.constraints.NotNull;

public class PagamentoRequest {
@NotNull
private TipoPagamento tipo;

@NotNull
private UUID pedidoId;

public PagamentoRequest() {

}
public PagamentoRequest(TipoPagamento tipo, UUID pedidoId) {
    this.tipo = tipo;
    this.pedidoId = pedidoId;
}
public TipoPagamento getTipo() {
    return tipo;
}
public UUID getPedidoId() {
    return pedidoId;
}
public void setTipo(TipoPagamento tipo) {
    this.tipo = tipo;
}
public void setPedidoId(UUID pedidoId) {
    this.pedidoId = pedidoId;
}
}
