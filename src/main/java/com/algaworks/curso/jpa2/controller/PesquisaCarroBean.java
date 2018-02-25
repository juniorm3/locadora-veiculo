package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.AcessorioDAO;
import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesMessages;
import com.algaworks.curso.modelolazy.LazyCarroDataModel;

@Named
@ViewScoped
public class PesquisaCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesMessages facesMessages;

	@Inject
	private CarroDAO carroDAO;

	private LazyCarroDataModel lazyCarros;

	private Carro carroSelecionado;

	public void inicializar() {
		// carros = carroDAO.buscarTodos();
		lazyCarros = new LazyCarroDataModel(carroDAO);
	}

	@SuppressWarnings("unchecked")
	public void excluir() {
		try {
			carroDAO.excluir(carroSelecionado);
			List<Carro> listaCarros = (List<Carro>) this.getLazyCarros().getWrappedData();
			listaCarros.remove(carroSelecionado);
			facesMessages.info("Carro placa " + carroSelecionado.getPlaca() + " excluído com sucesso.");
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());
		}
	}

	public Carro getCarroSelecionado() {
		return carroSelecionado;
	}

	public void setCarroSelecionado(Carro carroSelecionado) {
		this.carroSelecionado = carroSelecionado;
	}

	public void buscarAcessoriosParaCarro() {
		carroSelecionado = carroDAO.buscarAcessorios(carroSelecionado.getCodigo());
	}

	public void buscarCarroComAcessorios() {
		carroSelecionado = carroDAO.buscarCarroComAcessorios(carroSelecionado.getCodigo());
		Carro carroAtualizado = carroSelecionado;

		if (carroAtualizado != null) {
			carroSelecionado = carroAtualizado;
		}
	}

	public LazyCarroDataModel getLazyCarros() {
		return lazyCarros;
	}

}
