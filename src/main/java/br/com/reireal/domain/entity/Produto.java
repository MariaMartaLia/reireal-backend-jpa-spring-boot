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
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nome;

   @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valorUnitario;

    @Column(nullable = false)
    private Integer estoque;

    @Column(nullable = false)
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public Produto() {


    }

    public Produto(String nome, BigDecimal valorUnitario, Integer estoque, Categoria categoria) {
        
        validarNome(nome);
        validarValorUnitario(valorUnitario);
        validarEstoque(estoque);
        validarCategoria(categoria);
     
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        this.estoque = estoque;
        this.categoria = categoria;
        this.ativo = true;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void alterarNome(String nome) {
        validarNome(nome);
        this.nome = nome;
    }

    public void alterarValorUnitario(BigDecimal valorUnitario) {
        validarValorUnitario(valorUnitario);
        this.valorUnitario = valorUnitario;
    }

    public void aumentarEstoque(Integer quantidade) {
        validarQuantidade(quantidade);
        this.estoque += quantidade;
    }

    public void reduzirEstoque(Integer quantidade) {
        validarQuantidade(quantidade);

        if (this.estoque - quantidade < 0) {
            throw new IllegalArgumentException("Quantidade insuficiente para redução do estoque.");
        }

        this.estoque -= quantidade;
    }

    public void alterarCategoria(Categoria categoria) {
        validarCategoria(categoria);
        this.categoria = categoria;
    }

    public void ativar() {
        if (this.ativo) {
            throw new IllegalArgumentException("Produto já está ativo.");
        }

        this.ativo = true;
    }

    public void desativar() {
        if (!this.ativo) {
            throw new IllegalArgumentException("Produto já está desativado.");
        }

        this.ativo = false;
    }

    private void validarNome(String nome) {
        if (nome == null
                || nome.isBlank()
                || nome.length() > 100
                || !nome.matches("^[a-zA-Z0-9 ]+$")) {

            throw new IllegalArgumentException(
                    "Nome inválido. O nome deve conter apenas letras, números e espaços e ter no máximo 100 caracteres.");
        }
    }

    private void validarValorUnitario(BigDecimal valorUnitario) {
        if (valorUnitario == null || valorUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço inválido. O preço deve ser maior que zero.");
        }
    }

    private void validarEstoque(Integer estoque) {
        if (estoque == null || estoque < 0) {
            throw new IllegalArgumentException("Estoque inválido. O estoque não pode ser negativo.");
        }
    }

    private void validarQuantidade(Integer quantidade) {
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade inválida. A quantidade deve ser maior que zero.");
        }
    }

    private void validarCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria inválida. A categoria não pode ser nula.");
        }
        if (!categoria.isAtivo()) {
        throw new IllegalStateException(
            "Produto não pode ser associado a uma categoria desativada."
        );
    }
    }
}