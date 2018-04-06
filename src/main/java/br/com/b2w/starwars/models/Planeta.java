package br.com.b2w.starwars.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planetas")
public class Planeta {

	@Id
	private String id;
	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	@NotEmpty
	private String clima;
	@NotNull
	@NotEmpty
	private String terreno;
	
	private Integer quantidadeAparicoesFilmes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public Integer getQuantidadeAparicoesFilmes() {
		return quantidadeAparicoesFilmes;
	}

	public void setQuantidadeAparicoesFilmes(Integer quantidadeAparicoesFilmes) {
		this.quantidadeAparicoesFilmes = quantidadeAparicoesFilmes;
	}

}
