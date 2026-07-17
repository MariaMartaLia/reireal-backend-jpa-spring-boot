package br.com.reireal.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.reireal.domain.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

}
