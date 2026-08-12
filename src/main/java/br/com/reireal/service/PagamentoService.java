package br.com.reireal.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.reireal.domain.entity.Pagamento;
import br.com.reireal.domain.entity.Pedido;
import br.com.reireal.domain.enums.TipoPagamento;
import br.com.reireal.dto.request.PagamentoRequest;
import br.com.reireal.dto.response.PagamentoResponse;
import br.com.reireal.repository.PagamentoRepository;
import br.com.reireal.repository.PedidoRepository;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;

    public PagamentoService(
            PagamentoRepository pagamentoRepository,
            PedidoRepository pedidoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public PagamentoResponse cadastrar(PagamentoRequest pagamentoRequest) {

        Pagamento pagamento = toEntity(pagamentoRequest);

        pagamento = pagamentoRepository.save(pagamento);

        return toResponse(pagamento);
    }

    public PagamentoResponse buscar(UUID id) {

        Pagamento pagamento = buscarPagamentoId(id);

        return toResponse(pagamento);
    }

    public List<PagamentoResponse> listar() {

        List<Pagamento> pagamentos = pagamentoRepository.findAll();

        return pagamentos.stream()
                .map(this::toResponse)
                .toList();
    }

    public PagamentoResponse atualizar(
            UUID id,
            PagamentoRequest pagamentoRequest) {

        Pagamento pagamento = buscarPagamentoId(id);

        Pedido pedido = pedidoRepository.findById(pagamentoRequest.getPedidoId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Pedido não encontrado."
                ));

        pagamento.atualizarDados(
                pagamentoRequest.getTipo(),
                pedido.getTotal(),
                pedido
        );

        pagamento = pagamentoRepository.save(pagamento);

        return toResponse(pagamento);
    }

    public void excluir(UUID id) {

        Pagamento pagamento = buscarPagamentoId(id);

        pagamentoRepository.delete(pagamento);
    }

    private Pagamento buscarPagamentoId(UUID id) {

        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Pagamento não foi encontrado."
                ));
    }

        private Pagamento toEntity(PagamentoRequest pagamentoRequest) {

        UUID pedidoId = pagamentoRequest.getPedidoId();

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Pedido não encontrado."
                ));

        TipoPagamento tipo = pagamentoRequest.getTipo();
        BigDecimal valor = pedido.getTotal();

        Pagamento pagamento = new Pagamento(
                tipo,
                valor,
                pedido
        );

        return pagamento;
    }

    private PagamentoResponse toResponse(Pagamento pagamento) {

        return new PagamentoResponse(
                pagamento.getId(),
                pagamento.getTipo(),
                pagamento.getValor(),
                pagamento.getDataPagamento(),
                pagamento.getStatus(),
                pagamento.getPedido().getId()
        );
    }
}