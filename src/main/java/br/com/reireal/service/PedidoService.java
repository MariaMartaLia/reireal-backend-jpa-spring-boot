package br.com.reireal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.reireal.domain.entity.Cliente;
import br.com.reireal.domain.entity.ItemPedido;
import br.com.reireal.domain.entity.Pedido;
import br.com.reireal.domain.entity.Produto;
import br.com.reireal.dto.request.ItemPedidoRequest;
import br.com.reireal.dto.request.PedidoRequest;
import br.com.reireal.dto.response.ItemPedidoResponse;
import br.com.reireal.dto.response.PedidoResponse;
import br.com.reireal.repository.ClienteRepository;
import br.com.reireal.repository.ItemPedidoRepository;
import br.com.reireal.repository.PedidoRepository;
import br.com.reireal.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoService(
            PedidoRepository pedidoRepository,
            ProdutoRepository produtoRepository,
            ItemPedidoRepository itemPedidoRepository,
            ClienteRepository clienteRepository) {

        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    public PedidoResponse cadastrar(PedidoRequest pedidoRequest) {

        Pedido pedido = toEntity(pedidoRequest);

        pedido = pedidoRepository.save(pedido);

        return toResponse(pedido);
    }

    public PedidoResponse buscar(UUID pedidoId) {

        Pedido pedido = buscarPedidoPorId(pedidoId);

        return toResponse(pedido);
    }

    public List<PedidoResponse> listar() {

        return pedidoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public PedidoResponse atualizar(UUID pedidoId, PedidoRequest pedidoRequest) {

        Pedido pedido = buscarPedidoPorId(pedidoId);

        pedido = atualizarEntity(pedido, pedidoRequest);

        pedido = pedidoRepository.save(pedido);

        return toResponse(pedido);
    }

    public void excluir(UUID id) {

        Pedido pedido = buscarPedidoPorId(id);

        pedidoRepository.delete(pedido);
    }

    private Pedido atualizarEntity(
            Pedido pedido,
            PedidoRequest pedidoRequest) {

        List<ItemPedidoRequest> itensRequest = pedidoRequest.getItens();

        List<ItemPedido> novosItens = new ArrayList<>();

        itensRequest.forEach(itemRequest -> {

            Produto produto = buscarProdutoPorId(
                    itemRequest.getProdutoId());

            ItemPedido itemPedido = new ItemPedido(produto);

            itemPedido.alterarQuantidade(
                    itemRequest.getQuantidade());

            novosItens.add(itemPedido);
        });

        pedido.substituirItens(novosItens);

        return pedido;
    }

    private Pedido buscarPedidoPorId(UUID pedidoId) {

        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Pedido não foi encontrado."
                ));
    }

    private Cliente buscarClientePorId(UUID clienteId) {

        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cliente não encontrado."
                ));
    }

    private Produto buscarProdutoPorId(UUID produtoId) {

        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Produto não encontrado."
                ));
    }

    private Pedido toEntity(PedidoRequest pedidoRequest) {

        UUID clienteId = pedidoRequest.getClienteId();

        Cliente cliente = buscarClientePorId(clienteId);

        Pedido pedido = new Pedido(cliente);

        List<ItemPedidoRequest> itens = pedidoRequest.getItens();

        itens.forEach(itemRequest -> {

            Produto produto = buscarProdutoPorId(
                    itemRequest.getProdutoId());

            ItemPedido itemPedido = new ItemPedido(produto);

            itemPedido.alterarQuantidade(
                    itemRequest.getQuantidade());

            pedido.adicionarItem(itemPedido);
        });

        return pedido;
    }

    private PedidoResponse toResponse(Pedido pedido) {

        return new PedidoResponse(
                pedido.getId(),
                pedido.getDataPedido(),
                pedido.getTotal(),
                pedido.getStatus(),
                pedido.getCliente().getId(),
                itemPedidoRepository.findByPedidoId(pedido.getId())
                        .stream()
                        .map(item -> new ItemPedidoResponse(
                                item.getId(),
                                item.getQuantidade(),
                                item.getValorUnitario(),
                                item.getSubTotal(),
                                item.getProduto().getId(),
                                item.getProduto().getNome()
                        ))
                        .toList()
        );
    }
}
