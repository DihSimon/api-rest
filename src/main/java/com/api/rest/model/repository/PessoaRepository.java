package com.api.rest.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.rest.model.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
