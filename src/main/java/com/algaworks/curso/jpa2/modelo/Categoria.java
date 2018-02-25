package com.algaworks.curso.jpa2.modelo;

public enum Categoria {

	HATCH_COMPACTO("Hatch compacto"),
	HATCH_MEDIO("Hatch médio"),
	SEDAN_COMPACTO("Sedan compacto"),
	SEDAN_MEDIO("Sedan médio"),
	SEDAN_GRANDE("Sedan grande"),
	MINIVAN("Minivan"),
	ESPORTIVO("Esportivo"),
	UTILITARIO_COMERCIAL("Utilitário comercial");
	
	private String descricao;
	
	private Categoria(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
		
}
