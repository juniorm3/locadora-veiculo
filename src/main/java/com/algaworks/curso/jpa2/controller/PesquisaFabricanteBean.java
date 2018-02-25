package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesMessages;
import com.algaworks.curso.modelolazy.LazyFabricanteDataModel;

@Named
@ViewScoped
public class PesquisaFabricanteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FabricanteDAO fabricanteDAO;
	
	private LazyFabricanteDataModel lazyFabricantes;
	
	private Fabricante fabricanteSelecionado;
	
	@Inject
	private FacesMessages facesMessages;
	
	@SuppressWarnings("unchecked")
	public void excluir() {
		try {
			fabricanteDAO.excluir(fabricanteSelecionado);
			List<Fabricante> fabricantes = (List<Fabricante>) this.getLazyFabricantes().getWrappedData();
			fabricantes.remove(fabricanteSelecionado);
			facesMessages.info("Fabricante " + fabricanteSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());
		}
	}

	public Fabricante getFabricanteSelecionado() {
		return fabricanteSelecionado;
	}
	public void setFabricanteSelecionado(Fabricante fabricanteSelecionado) {
		this.fabricanteSelecionado = fabricanteSelecionado;
	}
		
	public void inicializar() {
		//fabricantes = fabricanteDAO.buscarTodos();
		lazyFabricantes = new LazyFabricanteDataModel(fabricanteDAO);
	}
	
	public LazyFabricanteDataModel getLazyFabricantes() {
		return lazyFabricantes;
	}
}
