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
	private EntityManager manager;
	

	public void salvar(Fabricante fabricante) {
		manager.merge(fabricante);
	}

	public List<Fabricante> buscarTodos() {
		return manager.createQuery("from Fabricante", Fabricante.class).getResultList();
	}

	@Transactional
	public void excluir(Fabricante fabricante) throws NegocioException {
		try {
			Fabricante fabricanteTemp = manager.find(Fabricante.class, fabricante.getCodigo());
			
			manager.remove(fabricanteTemp);
			manager.flush();
		} catch(PersistenceException e) {
			throw new NegocioException("Fabricante não pode ser excluído.");
		}
	}

	public Fabricante buscarPeloCodigo(Long codigo) {
		return manager.find(Fabricante.class, codigo);
	}

	public List<Fabricante> buscaComPaginacao(int first, int pageSize) {		
		return manager.createQuery("select f from Fabricante f", Fabricante.class)
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
	}

	public Long encontratQuantidadeDeFabricantes() {
		return manager.createQuery("select count(f) from Fabricante f", Long.class).getSingleResult();
	}

}
