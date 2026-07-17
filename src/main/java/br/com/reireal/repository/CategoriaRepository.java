package br.com.reireal.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.reireal.domain.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,  UUID> {

}


