package com.algaworks.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.algaworks.curso.jpa2.dao.FuncionarioDAO;
import com.algaworks.curso.jpa2.modelo.Funcionario;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class CadastroFuncionarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioDAO funcionarioDAO;

	@Transactional
	public void salvar(Funcionario funcionario) throws NegocioException {

		if (StringUtils.isBlank(funcionario.getNome())) {
			throw new NegocioException("O nome do funcionario é obrigatprio");
		}

		this.funcionarioDAO.salvar(funcionario);
	}

}
