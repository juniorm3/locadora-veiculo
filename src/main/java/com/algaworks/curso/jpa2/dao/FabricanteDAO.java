package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class FabricanteDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public void salvar(Fabricante fabricante) {
		em.merge(fabricante);
	}

	public List<Fabricante> buscarTodos() {
		return em.createQuery("from Fabricante", Fabricante.class).getResultList();
	}

	@Transactional
	public void excluir(Fabricante fabricante) throws NegocioException {
		try {
			Fabricante fabricanteTemp = em.find(Fabricante.class, fabricante.getCodigo());

			em.remove(fabricanteTemp);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Fabriante não pode ser excluido");
		}
	}

	public Fabricante buscarPeloCodigo(Long codigo) {
		return em.find(Fabricante.class, codigo);
	}

}
