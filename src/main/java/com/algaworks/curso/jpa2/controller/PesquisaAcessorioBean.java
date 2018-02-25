package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.AcessorioDAO;
import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesMessages;
import com.algaworks.curso.modelolazy.LazyAcessorioDataModel;

@Named
@ViewScoped
public class PesquisaAcessorioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	AcessorioDAO acessorioDAO;

	private LazyAcessorioDataModel lazyAcessorios;

	private Acessorio acessorioSelecionado;

	@Inject
	private FacesMessages facesMessages;

	public void inicializar() {
		// acessorios = acessorioDAO.buscarTodos();
		lazyAcessorios = new LazyAcessorioDataModel(acessorioDAO);
	}

	@SuppressWarnings("unchecked")
	public void excluir() {
		try {
			acessorioDAO.excluir(acessorioSelecionado);
			List<Acessorio> acessorios = (List<Acessorio>) this.getLazyAcessorios().getWrappedData();
			acessorios.remove(acessorioSelecionado);
			facesMessages.info("Acessório " + acessorioSelecionado.getDescricao() + " excluído com sucesso.");			
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());
		}
	}

	public Acessorio getAcessorioSelecionado() {
		return acessorioSelecionado;
	}

	public void setAcessorioSelecionado(Acessorio acessorioSelecionado) {
		this.acessorioSelecionado = acessorioSelecionado;
	}

	public LazyAcessorioDataModel getLazyAcessorios() {
		return lazyAcessorios;
	}
}
