package br.com.reireal.reireal_backend.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.reireal.domain.entity.Pagamento;
import br.com.reireal.domain.entity.Pedido;

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

}
