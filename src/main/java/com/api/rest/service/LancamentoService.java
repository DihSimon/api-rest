package com.api.rest.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.api.rest.model.Lancamento;
import com.api.rest.model.repository.LancamentoRepository;

/**
 * Servico de Lancamentos
 * @author Diego
 *
 */
@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	/**
	 * Serviço para atualizar pessoas
	 * @param codigo
	 * @param pessoa
	 * @return
	 */
	public Lancamento atualizar(Long codigo, Lancamento pessoa) {
		Lancamento lancamentoSalvo = buscarLancamentoPeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, lancamentoSalvo, "codigo");
		return lancamentoRepository.save(lancamentoSalvo);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Lancamento lancamentoSalvo = buscarLancamentoPeloCodigo(codigo);
		lancamentoRepository.save(lancamentoSalvo);
	}

	private Lancamento buscarLancamentoPeloCodigo(Long codigo) {
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(codigo);
		if (lancamentoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return lancamentoSalvo;
	}

	
	
}
