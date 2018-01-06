package com.api.rest.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.rest.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
