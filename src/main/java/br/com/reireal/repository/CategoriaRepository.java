package br.com.reireal.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.reireal.domain.entity.Categoria;
import br.com.reireal.dto.request.CategoriaRequest;

public interface CategoriaRepository extends JpaRepository<Categoria,  UUID> {
    boolean existsByNome(String nome);
    boolean existsByNomeAndIdNot(String nome, UUID id);

}


