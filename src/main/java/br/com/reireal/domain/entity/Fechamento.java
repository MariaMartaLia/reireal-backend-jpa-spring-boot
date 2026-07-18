package br.com.reireal.domain.entity;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import br.com.reireal.domain.enums.StatusPagamento;
import br.com.reireal.domain.enums.StatusPedido;
import br.com.reireal.domain.enums.TipoPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fechamento")
public class Fechamento {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    @Column(nullable = false)
    private LocalDateTime dataFechamento;

    @Column(nullable = false)
    private BigDecimal totalDinheiro;

    @Column(nullable = false)
    private BigDecimal totalCartaoCredito;

    @Column(nullable = false)
    private BigDecimal totalCartaoDebito;

    @Column(nullable = false)
    private BigDecimal totalPix;

    @Column(nullable = false)
    private BigDecimal creditoGerado;

    @Column(nullable = false)
    private BigDecimal creditoUtilizado;

    @Column(nullable = false)
    private BigDecimal totalGeral;
    
 
    

public Fechamento() {
    this.dataFechamento = LocalDateTime.now();
    this.totalDinheiro =  BigDecimal.ZERO;
    this.totalCartaoCredito =  BigDecimal.ZERO;
    this.totalCartaoDebito =  BigDecimal.ZERO;
    this.totalPix =  BigDecimal.ZERO;
    this.creditoGerado = BigDecimal.ZERO;
    this.creditoUtilizado = BigDecimal.ZERO;
    this.totalGeral =  BigDecimal.ZERO;
   
}
public UUID getId() {
    return id;
}
public LocalDateTime getDataFechamento() {
    return dataFechamento;
}
public BigDecimal getTotalDinheiro() {
    return totalDinheiro;
}
public BigDecimal getTotalCartaoCredito() {
    return totalCartaoCredito;
}
public BigDecimal getTotalCartaoDebito() {
    return totalCartaoDebito;
}
public BigDecimal getTotalPix() {
    return totalPix;
}
public BigDecimal getCreditoGerado() {
    return creditoGerado;
}
public BigDecimal getCreditoUtilizado() {
    return creditoUtilizado;
}
public BigDecimal getTotalGeral() {
    return totalGeral;
}


public void adicionarPagamento(Pagamento pagamento){
    validarPagamento(pagamento);
    Pedido pedido = pagamento.getPedido();
    if( pedido.getStatus() != StatusPedido.ENTREGUE)  {
        throw new IllegalStateException(
            "Somente pagamentos de pedidos entregues podem ser incluídos no fechamento."
        );
    }
   TipoPagamento tipo  = pagamento.getTipo();
   switch (tipo) {
    case DINHEIRO:
        totalDinheiro =totalDinheiro.add(pagamento.getValor());
        break;
    case CARTAO_CREDITO:
        totalCartaoCredito = totalCartaoCredito.add(pagamento.getValor());
        break;
    case CARTAO_DEBITO:
        totalCartaoDebito = totalCartaoDebito.add(pagamento.getValor());
        break;
    case PIX:
        totalPix =totalPix.add(pagamento.getValor());
        break;  
    default:
        throw new IllegalArgumentException(
        "Tipo de pagamento não reconhecido."
    );
        }
      totalGeral = totalGeral.add(pagamento.getValor());
     
}
  public void adicionarCredito(Pagamento pagamento) {
    validarPagamento(pagamento);
    Pedido pedido = pagamento.getPedido();
   
    if(pedido.getStatus() == StatusPedido.ENTREGUE || pagamento.getStatus() != StatusPagamento.PAGAMENTO_APROVADO ){
        throw new IllegalStateException(
            "Pedido não pode ser creditado pois foi entregue ou pagamento não foi aprovado."
        );
    }
     Cliente cliente= pedido.getCliente();
     cliente.adicionarCreditoCliente( pagamento.getValor());
     creditoGerado = creditoGerado.add(pagamento.getValor());
    
     
   }
   public void utilizarCredito(Pedido pedido) {
    validarUtilizarCredito(pedido);
    Cliente cliente = pedido.getCliente();
    cliente.utilizarCredito(pedido.getTotal());
    creditoUtilizado = creditoUtilizado.add(pedido.getTotal());
   
   }
private void validarPagamento(Pagamento pagamento) {
if (pagamento == null) {
    throw new IllegalArgumentException(
        "Pagamento não pode ser nulo."
    );
}

if (pagamento.getValor() == null) {
    throw new IllegalArgumentException(
        "Valor do pagamento não pode ser nulo."
    );
}

if (pagamento.getTipo() == null) {
    throw new IllegalArgumentException(
        "Tipo de pagamento não pode ser nulo."
    );
}

if (pagamento.getPedido() == null) {
    throw new IllegalArgumentException(
        "Pagamento deve possuir um pedido."
    );
}
}
private void validarUtilizarCredito(Pedido pedido) {
      if (pedido == null) {
        throw new IllegalArgumentException(
            "Pedido não pode ser nulo."
        );
    }
   
    Cliente cliente = pedido.getCliente();

    if (cliente == null) {
    throw new IllegalArgumentException(
        "Pedido deve possuir um cliente."
    );
}

    if (pedido.getTotal() == null) {
        throw new IllegalArgumentException(
            "O valor total do pedido não pode ser nulo."
        );
    }

    if (pedido.getTotal().compareTo(cliente.getCredito()) > 0) {
        throw new IllegalStateException(
            "O cliente não possui crédito suficiente para pagar o pedido."
        );
    }}}


