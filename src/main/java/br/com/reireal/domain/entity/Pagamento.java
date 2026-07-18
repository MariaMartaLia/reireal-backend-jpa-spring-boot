package br.com.reireal.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import br.com.reireal.domain.enums.StatusPagamento;
import br.com.reireal.domain.enums.TipoPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "pagamento")

public class Pagamento {
@Id
@GeneratedValue
@UuidGenerator
private UUID id;

@Enumerated(EnumType.STRING)
@Column(nullable = false)
private TipoPagamento tipo;

@Column(nullable = false, precision = 12, scale = 2)
private BigDecimal valor;

@Column 
private LocalDateTime dataPagamento;

@Enumerated(EnumType.STRING)
@Column(nullable = false)
private StatusPagamento status;

@OneToOne
@JoinColumn(name = "pedido_id", nullable = false)
private Pedido pedido;

public Pagamento() {

}

public Pagamento(TipoPagamento tipo, BigDecimal valor, Pedido pedido) {
    validarTipo(tipo);
    validarValor(valor);
    validarPedido(pedido);
    this.tipo = tipo;
    this.valor = valor;
    this.status = StatusPagamento.AGUARDANDO_PAGAMENTO;
    this.pedido = pedido;
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
public Pedido getPedido() {
    return pedido;
}
public void aprovarPagamento() {
    if(status != StatusPagamento.AGUARDANDO_PAGAMENTO) {
        throw new IllegalStateException(
            "Pagamento só pode ser aprovado se estiver AGUARDANDO_PAGAMENTO."
        );
    }
    status = StatusPagamento.PAGAMENTO_APROVADO;
    dataPagamento =  LocalDateTime.now();
}
public void recusarPagamento() {
    if(status != StatusPagamento.AGUARDANDO_PAGAMENTO) {
        throw new IllegalStateException(
            "O pagamento só pode ser recusado se estiver AGUARDANDO_PAGAMENTO."
        );
    }
    status = StatusPagamento.PAGAMENTO_RECUSADO;
}
public void creditarPagamento() {
    if(status != StatusPagamento.PAGAMENTO_APROVADO) {
        throw new IllegalStateException(
            "O pagamento só pode ser creditado se estiver PAGAMENTO_APROVADO."
        );
    }
    status = StatusPagamento.PAGAMENTO_CREDITADO;
}
public void cancelarPagamento() {
    if(status != StatusPagamento.AGUARDANDO_PAGAMENTO) {
        throw new IllegalStateException(
            "O pagamento só pode ser cancelado se estiver AGUARDANDO_PAGAMENTO."
        );
    }
    status = StatusPagamento.PAGAMENTO_CANCELADO;
}
private void validarTipo(TipoPagamento tipo) {
    if( tipo == null) {
        throw new IllegalArgumentException(
            "Tipo não pode ser nulo."
        );
    }
}
private void validarValor(BigDecimal valor) {
    if(valor == null ||valor.compareTo(BigDecimal.ZERO) <= 0) {
        throw new IllegalArgumentException(
            "O valor do pagamento não pode ser nulo e deve ser maior que zero."
        );
    }
}
private void validarPedido(Pedido pedido) {
    if(pedido == null) {
    throw new IllegalArgumentException(
        "Pedido não pode ser nulo."
    );
    }
}

}
