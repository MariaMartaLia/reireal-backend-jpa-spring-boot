package br.com.reireal.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.reireal.domain.entity.ItemPedido;
import br.com.reireal.domain.entity.Produto;
import br.com.reireal.dto.request.ItemPedidoRequest;
import br.com.reireal.dto.response.ItemPedidoResponse;
import br.com.reireal.repository.ItemPedidoRepository;
import br.com.reireal.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemPedidoService {

    private final ItemPedidoRepository repository;
    private final ProdutoRepository produtoRepository;

    public ItemPedidoService(ItemPedidoRepository repository, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
    }

    public ItemPedidoResponse cadastrar(ItemPedidoRequest request) {

        validar(request);
        
        ItemPedido itemPedido = toEntity(request);

        itemPedido = repository.save(itemPedido);

        return toResponse(itemPedido);
    }

    public ItemPedidoResponse buscar(UUID id) {

        ItemPedido itemPedido = buscarPorId(id);

        return toResponse(itemPedido);
    }

    public List<ItemPedidoResponse> listarTodos() {

        return repository.findAll()

                .stream()

                .map(this::toResponse)

                .toList();
    }
  public ItemPedidoResponse atualizar(UUID id, ItemPedidoRequest request) {

    validar(request);

    ItemPedido itemPedido = buscarPorId(id);

    itemPedido.alterarQuantidade(request.getQuantidade());

    itemPedido = repository.save(itemPedido);

    return toResponse(itemPedido);
}
    public void excluir(UUID id) {

        ItemPedido itemPedido = buscarPorId(id);

        repository.delete(itemPedido);
    }

    private ItemPedido  buscarPorId(UUID id) {

        ItemPedido itemPedido = repository.findById(id)

    .orElseThrow(() -> new IllegalArgumentException("ItemPedido não encontrado."));

        return itemPedido;

    }
    
    private void validar(ItemPedidoRequest request) {

        if (request.getQuantidade() == null || request.getQuantidade() <= 0) {

            throw new IllegalArgumentException("Quantidade inválida.");
        }
    }

    private ItemPedido toEntity(ItemPedidoRequest request) {

    Produto produto = produtoRepository.findById(request.getProdutoId())

            .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

    ItemPedido itemPedido = new ItemPedido(produto);

    itemPedido.alterarQuantidade(request.getQuantidade());

    return itemPedido;
}

private ItemPedidoResponse toResponse(ItemPedido itemPedido) {
    return new ItemPedidoResponse(
        itemPedido.getId(),
        itemPedido.getQuantidade(),
        itemPedido.getValorUnitario(),
        itemPedido.getSubTotal(),
        itemPedido.getProduto().getId(),
        itemPedido.getProduto().getNome()
    );
}
}