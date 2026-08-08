package br.com.reireal.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.reireal.domain.entity.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID> {

    List<ItemPedido> findByPedidoId(UUID pedidoId);

}