package br.com.reireal.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.reireal.domain.entity.Categoria;
import br.com.reireal.domain.entity.Produto;
import br.com.reireal.dto.request.ProdutoRequest;
import br.com.reireal.dto.response.ProdutoResponse;
import br.com.reireal.repository.CategoriaRepository;
import br.com.reireal.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public ProdutoResponse cadastrar(ProdutoRequest request) {
        validar(request);

        Produto produto = toEntity(request);
        produtoRepository.save(produto);

        return toResponse(produto);
    }

    public ProdutoResponse buscar(UUID id) {
        Produto produto = buscarProdutoPorId(id);
        return toResponse(produto);
    }

    public ProdutoResponse atualizar(UUID id, ProdutoRequest request) {
        validar(request);

        Produto produto = buscarProdutoPorId(id);
        atualizarEntity(produto, request);

        produtoRepository.save(produto);

        return toResponse(produto);
    }

    public void excluir(UUID id) {
        Produto produto = buscarProdutoPorId(id);
        produtoRepository.delete(produto);
    }

    private Produto atualizarEntity(Produto produto, ProdutoRequest request) {

        produto.alterarNome(request.getNome());
        produto.alterarValorUnitario(request.getValorUnitario());
        produto.alterarEstoque(request.getEstoque());
        produto.alterarCategoria(buscarCategoriaPorId(request.getCategoriaId()));

        return produto;
    }

    private Produto toEntity(ProdutoRequest request) {
        return new Produto(
                request.getNome(),
                request.getValorUnitario(),
                request.getEstoque(),
                buscarCategoriaPorId(request.getCategoriaId()));
    }

    private ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getValorUnitario(),
                produto.getEstoque(),
                produto.isAtivo(),
                produto.getCategoria().getId(),
                produto.getCategoria().getNome());
    }

    private Produto buscarProdutoPorId(UUID id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));
    }

    private Categoria buscarCategoriaPorId(UUID categoriaId) {
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));
    }

    private void validar(ProdutoRequest request) {

        if (request.getNome() == null || request.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }

        if (request.getValorUnitario() == null
                || request.getValorUnitario().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor unitário deve ser maior que zero.");
        }

        if (request.getEstoque() == null || request.getEstoque() < 0) {
            throw new IllegalArgumentException("O estoque não pode ser negativo.");
        }

        if (request.getCategoriaId() == null) {
            throw new IllegalArgumentException("A categoria é obrigatória.");
        }
    }
}