package br.com.reireal.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;


import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valorUnitario;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal subTotal;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    public ItemPedido() {

    }
   

public ItemPedido(Produto produto) {
    validarProduto(produto);
   
    this.produto = produto;
    this.quantidade = 1;
    this.valorUnitario = produto.getValorUnitario();
    this.subTotal = calcularSubTotal();
    }
    public UUID getId() {
        return id;
    }
    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }
   public BigDecimal getSubTotal() {
    return subTotal;
}
    public Produto getProduto() {
        return produto;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void alterarQuantidade(Integer quantidade) {
        validarQuantidade(quantidade);
        this.quantidade = quantidade;
        this.subTotal = calcularSubTotal();
    }
    public void aumentarQuantidade(Integer quantidade) {
        validarQuantidade(quantidade);
        this.quantidade += quantidade;
        this.subTotal = calcularSubTotal();
    }
    public void diminuirQuantidade(Integer quantidade) {
        validarQuantidade(quantidade);
        if (this.quantidade - quantidade <=0) {
            throw new IllegalArgumentException(
                "A quantidade não pode ser menor ou igual a zero"
            );
        }   
        this.quantidade -= quantidade;
        this.subTotal = calcularSubTotal();
    }
    private BigDecimal calcularSubTotal() {
        return this.valorUnitario.multiply(BigDecimal.valueOf(this.quantidade));
    }
    private void validarProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException(
                "O produto não pode ser nulo"
            );
        }
    }
    private void validarQuantidade(Integer quantidade) {
        if(quantidade == null ||quantidade <= 0) {
            throw new IllegalArgumentException(
                "A quantidade deve ser maior que zero"
            );
        }
    }

}