package br.com.reireal.repository;

import java.util.UUID;

import br.com.reireal.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemPedidoRepository  extends JpaRepository<ItemPedido, UUID> {

}
