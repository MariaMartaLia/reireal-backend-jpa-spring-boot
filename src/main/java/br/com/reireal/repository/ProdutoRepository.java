package br.com.reireal.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.reireal.domain.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

}
