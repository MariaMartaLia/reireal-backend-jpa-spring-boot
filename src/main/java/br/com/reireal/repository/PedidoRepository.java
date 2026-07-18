package br.com.reireal.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.reireal.domain.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

}
