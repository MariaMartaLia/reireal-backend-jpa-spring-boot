package br.com.reireal.reireal_backend.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.reireal.domain.entity.Pagamento;
import br.com.reireal.domain.entity.Pedido;
import br.com.reireal.domain.enums.TipoPagamento;
import br.com.reireal.dto.request.PagamentoRequest;
import static org.mockito.ArgumentMatchers.any;
import br.com.reireal.dto.response.PagamentoResponse;
import br.com.reireal.repository.PagamentoRepository;
import br.com.reireal.repository.PedidoRepository;
import br.com.reireal.service.PagamentoService;


public class PagamentoServiceTest {
@Test
void devebuscarPorid() {
    //ARRANGE
    UUID id = UUID.randomUUID();
    UUID pedidoId = UUID.randomUUID();
    PagamentoRepository pagamentoRepository = mock(PagamentoRepository.class);
    PedidoRepository pedidoRepository = mock(PedidoRepository.class);
    Pedido pedido = mock(Pedido.class);

PagamentoService service = new PagamentoService(
        pagamentoRepository,
        pedidoRepository
);

    Pagamento pagamento = mock(Pagamento.class);
    when(pagamento.getId()).thenReturn(id);
    when(pagamento.getValor()).thenReturn(BigDecimal.valueOf(100));
    when(pedido.getId()).thenReturn(pedidoId);
    when(pagamento.getPedido()).thenReturn(pedido);

    when(pagamentoRepository.findById(id))
        .thenReturn(Optional.of(pagamento));


//ACT
PagamentoResponse resultado = service.buscar(id);

//ASSERT
assertNotNull(resultado);
assertEquals(id, resultado.getId());
assertEquals(BigDecimal.valueOf(100), resultado.getValor());

//VERIFY
verify(pagamentoRepository)
            .findById(id);
}

@Test
void devecadastrar() {

    // ARRANGE

    UUID pedidoId = UUID.randomUUID();
    UUID pagamentoId = UUID.randomUUID();

    TipoPagamento tipo = TipoPagamento.PIX;

    PagamentoRequest pagamentoRequest = new PagamentoRequest(
            tipo,
            pedidoId
    );

    PagamentoRepository pagamentoRepository =
            mock(PagamentoRepository.class);

    PedidoRepository pedidoRepository =
            mock(PedidoRepository.class);

    PagamentoService pagamentoService =
            new PagamentoService(
                    pagamentoRepository,
                    pedidoRepository
            );

    Pedido pedido = mock(Pedido.class);

    when(pedidoRepository.findById(pedidoId))
            .thenReturn(Optional.of(pedido));

    when(pedido.getTotal())
            .thenReturn(BigDecimal.valueOf(100));

    when(pedido.getId())
            .thenReturn(pedidoId);

    Pagamento pagamento = mock(Pagamento.class);

    when(pagamento.getId())
            .thenReturn(pagamentoId);

    when(pagamento.getTipo())
            .thenReturn(tipo);

    when(pagamento.getValor())
            .thenReturn(BigDecimal.valueOf(100));

    when(pagamento.getPedido())
            .thenReturn(pedido);

    when(pagamentoRepository.save(any(Pagamento.class)))
            .thenReturn(pagamento);


    // ACT

    PagamentoResponse resultado =
            pagamentoService.cadastrar(pagamentoRequest);


    // ASSERT

    assertNotNull(resultado);

    assertEquals(
            BigDecimal.valueOf(100),
            resultado.getValor()
    );

    assertEquals(
            tipo,
            resultado.getTipo()
    );

    assertEquals(
            pedidoId,
            resultado.getPedidoId()
    );


    // VERIFY

    verify(pedidoRepository)
            .findById(pedidoId);

    verify(pagamentoRepository)
            .save(any(Pagamento.class));
}

@Test
void deveListar() {

    // ARRANGE

    PagamentoRepository pagamentoRepository =
            mock(PagamentoRepository.class);

    PagamentoService pagamentoService =
            new PagamentoService(
                    pagamentoRepository,
                    null
            );

    Pagamento pagamento = mock(Pagamento.class);

    UUID pagamentoId = UUID.randomUUID();
    UUID pedidoId = UUID.randomUUID();

    Pedido pedido = mock(Pedido.class);

    when(pagamento.getId())
            .thenReturn(pagamentoId);

    when(pagamento.getTipo())
            .thenReturn(TipoPagamento.PIX);

    when(pagamento.getValor())
            .thenReturn(BigDecimal.valueOf(100));

    when(pagamento.getPedido())
            .thenReturn(pedido);

    when(pedido.getId())
            .thenReturn(pedidoId);

    List<Pagamento> pagamentos =
            List.of(pagamento);

    when(pagamentoRepository.findAll())
            .thenReturn(pagamentos);


    // ACT

    List<PagamentoResponse> resultado =
            pagamentoService.listar();


    // ASSERT

    assertNotNull(resultado);

    assertEquals(1, resultado.size());

    assertEquals(
            pagamentoId,
            resultado.get(0).getId()
    );

    assertEquals(
            TipoPagamento.PIX,
            resultado.get(0).getTipo()
    );

    assertEquals(
            BigDecimal.valueOf(100),
            resultado.get(0).getValor()
    );

    assertEquals(
            pedidoId,
            resultado.get(0).getPedidoId()
    );


    // VERIFY

    verify(pagamentoRepository)
            .findAll();
}
@Test
void deveAtualizar() {

    // ARRANGE

    UUID pagamentoId = UUID.randomUUID();
    UUID pedidoId = UUID.randomUUID();

    TipoPagamento tipo =
            TipoPagamento.PIX;

    PagamentoRequest pagamentoRequest =
            new PagamentoRequest(
                    tipo,
                    pedidoId
            );

    PagamentoRepository pagamentoRepository =
            mock(PagamentoRepository.class);

    PedidoRepository pedidoRepository =
            mock(PedidoRepository.class);

    PagamentoService pagamentoService =
            new PagamentoService(
                    pagamentoRepository,
                    pedidoRepository
            );

    Pagamento pagamento =
            mock(Pagamento.class);

    Pedido pedido =
            mock(Pedido.class);

    when(pagamentoRepository.findById(pagamentoId))
            .thenReturn(Optional.of(pagamento));

    when(pedidoRepository.findById(pedidoId))
            .thenReturn(Optional.of(pedido));

    when(pedido.getTotal())
            .thenReturn(BigDecimal.valueOf(200));

    when(pagamento.getId())
            .thenReturn(pagamentoId);

    when(pagamento.getTipo())
            .thenReturn(tipo);

    when(pagamento.getValor())
            .thenReturn(BigDecimal.valueOf(200));

    when(pagamento.getPedido())
            .thenReturn(pedido);

    when(pedido.getId())
            .thenReturn(pedidoId);

    when(pagamentoRepository.save(pagamento))
            .thenReturn(pagamento);


    // ACT

    PagamentoResponse resultado =
            pagamentoService.atualizar(
                    pagamentoId,
                    pagamentoRequest
            );


    // ASSERT

    assertNotNull(resultado);

    assertEquals(
            pagamentoId,
            resultado.getId()
    );

    assertEquals(
            tipo,
            resultado.getTipo()
    );

    assertEquals(
            BigDecimal.valueOf(200),
            resultado.getValor()
    );

    assertEquals(
            pedidoId,
            resultado.getPedidoId()
    );


    // VERIFY

    verify(pagamentoRepository)
            .findById(pagamentoId);

    verify(pedidoRepository)
            .findById(pedidoId);

    verify(pagamentoRepository)
            .save(pagamento);
}
@Test
void deveExcluir() {

    // ARRANGE

    UUID pagamentoId =
            UUID.randomUUID();

    PagamentoRepository pagamentoRepository =
            mock(PagamentoRepository.class);

    PagamentoService pagamentoService =
            new PagamentoService(
                    pagamentoRepository,
                    null
            );

    Pagamento pagamento =
            mock(Pagamento.class);

    when(pagamentoRepository.findById(pagamentoId))
            .thenReturn(Optional.of(pagamento));


    // ACT

    pagamentoService.excluir(pagamentoId);


    // ASSERT

    // Não existe Response para verificar.


    // VERIFY

    verify(pagamentoRepository)
            .findById(pagamentoId);

    verify(pagamentoRepository)
            .delete(pagamento);
}

}
