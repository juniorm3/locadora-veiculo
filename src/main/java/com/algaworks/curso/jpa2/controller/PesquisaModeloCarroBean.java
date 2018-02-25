package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.ModeloCarroDAO;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesMessages;
import com.algaworks.curso.modelolazy.LazyModeloCarroDataModel;

@Named
@ViewScoped
public class PesquisaModeloCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private LazyModeloCarroDataModel lazyModeloCarro;
	
	private ModeloCarro modeloCarroSelecionado;
	
	@Inject
	ModeloCarroDAO modeloCarroDAO;
	
	@Inject
	private FacesMessages facesMessages;
		
	public void inicializar() {
		//this.modelosCarro = this.modeloCarroDAO.buscarTodos();
		lazyModeloCarro = new LazyModeloCarroDataModel(modeloCarroDAO);
	}
	
	@SuppressWarnings("unchecked")
	public void excluir() {
		try {
			modeloCarroDAO.excluir(modeloCarroSelecionado);
			List<ModeloCarro> modelosCarro = (List<ModeloCarro>) this.getLazyModeloCarro().getWrappedData();
			modelosCarro.remove(modeloCarroSelecionado);
			facesMessages.info("Modelo " + modeloCarroSelecionado.getDescricao() + " excluído com sucesso.");
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());			
		}
	}
	
	public ModeloCarro getModeloCarroSelecionado() {
		return modeloCarroSelecionado;
	}
	
	public void setModeloCarroSelecionado(ModeloCarro modeloCarroSelecionado) {
		this.modeloCarroSelecionado = modeloCarroSelecionado;
	}
	
	public LazyModeloCarroDataModel getLazyModeloCarro() {
		return lazyModeloCarro;
	}
	
}
