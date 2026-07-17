package br.com.reireal.dto.response;

import java.math.BigDecimal;
import java.util.UUID;



public class ProdutoResponse {

    private UUID id;
    private String nome;
    private BigDecimal valorUnitario;
    private Integer estoque;
    private boolean ativo;
    private UUID categoriaId;
    private String categoriaNome;

    public ProdutoResponse (UUID id, String nome, BigDecimal valorUnitario, Integer estoque, boolean ativo, UUID categoriaId, String categoriaNome) {
        this.id = id;
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        this.estoque = estoque;
        this.ativo = ativo;
        this.categoriaId = categoriaId;
        this.categoriaNome = categoriaNome;
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
    public UUID getCategoriaId() {
        return categoriaId;
    }
    public String getCategoriaNome() {
        return categoriaNome;
    }
}


