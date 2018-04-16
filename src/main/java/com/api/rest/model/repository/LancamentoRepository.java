package com.api.rest.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.rest.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
