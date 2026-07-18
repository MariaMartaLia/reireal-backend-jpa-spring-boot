package br.com.reireal.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import br.com.reireal.domain.enums.StatusPedido;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
@Id
@GeneratedValue
@UuidGenerator
private UUID id;

@Column(nullable = false)
private LocalDateTime dataPedido;

@Column(nullable = false, precision = 12,scale = 2)
private BigDecimal total;

@Enumerated(EnumType.STRING)
@Column(nullable = false)
private StatusPedido status;

@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL,orphanRemoval = true)
private List<ItemPedido> itens = new ArrayList<>();

@ManyToOne
@JoinColumn(name = "cliente_id", nullable = false)
private Cliente cliente;

protected Pedido(){

}
public Pedido (Cliente cliente ) {
    validarCliente(cliente);
      
    this.dataPedido = LocalDateTime.now();
    this.total= BigDecimal.ZERO;
    this.status =  StatusPedido.PENDENTE;
    this.cliente = cliente;         
    
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
   public List<ItemPedido> getItens() {
    return itens;
   }
   public Cliente getCliente() {
    return cliente;
   }

   private void validarCliente(Cliente cliente) {
        if(cliente == null){
        throw new IllegalArgumentException(
            "Pedido não pode ser feito sem um cliente!"
        );
    } 
}
private void validarItemPedido(ItemPedido itemPedido) {
    if(itemPedido ==null){
        throw new IllegalArgumentException( 
            "ItemPedido não pode ser nulo"
        );
    }
}
public void adicionarItem(ItemPedido itemPedido) { 

    validarItemPedido(itemPedido);

    for (ItemPedido itemExistente : itens) {         

        if (itemExistente.getProduto()
                .equals(itemPedido.getProduto())) {  

            itemExistente.aumentarQuantidade(
                    itemPedido.getQuantidade()
            );
            calcularTotal();
            return;
    } } 
    itens.add(itemPedido);
    calcularTotal();
} 
public void diminuirItem(ItemPedido itemPedido) {
    validarItemPedido(itemPedido);
    
    for (ItemPedido itemExistente : itens) {
     if(itemExistente.getProduto().equals(itemPedido.getProduto()) && itemExistente.getQuantidade() > 1) {
        itemExistente.diminuirQuantidade(1);
        calcularTotal();
}
    if(itemExistente.getProduto().equals(itemPedido.getProduto()) && itemExistente.getQuantidade() == 1) {
        itens.remove(itemExistente);
}
        calcularTotal();
        return;
    }}
private void calcularTotal() {
    total=BigDecimal.ZERO;
    for (ItemPedido itemPedido : itens) {
        total = total.add(itemPedido.getSubTotal());

    }
} 
public void confirmarPagamento(){
    if(status != StatusPedido.PENDENTE) {
           throw new IllegalStateException(
            "O pagamento só pode ser confirmado para pedidos pendentes." 
        );
        }
        status = StatusPedido.PAGAMENTO_REALIZADO;
}
public void comecarSeparacao(){
    if(status != StatusPedido.PAGAMENTO_REALIZADO) {
       throw new IllegalStateException(
        "O pedido so pode ser separado se pagamento for confirmado!!"
    );
     
}
status = StatusPedido.EM_SEPARACAO;
    
}
public void finalizarSeparacao(){
    if( status != StatusPedido.EM_SEPARACAO) {
        throw new IllegalStateException(
            "O pedido só pode ficar pronto para entrega se estiver em separação."
        );
    }
    status = StatusPedido.PRONTO_PARA_ENTREGA;
}
public void entregarPedido(){
    if(status != StatusPedido.PRONTO_PARA_ENTREGA) {
        throw new IllegalStateException(
            "O pedido só pode ser entregue se estiver pronto para entrega."
        );
    }
    status = StatusPedido.ENTREGUE;
}
public void cancelarPedido(){
    if (status != StatusPedido.PENDENTE) {
        throw new IllegalStateException(
            "O pedido só pode ser cancelado se estiver pendente."
        );
    }
    status = StatusPedido.CANCELADO;
}
}  
