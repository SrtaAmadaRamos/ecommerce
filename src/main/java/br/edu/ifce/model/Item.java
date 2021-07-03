package br.edu.ifce.model;

import br.edu.ifce.utils.StringUtils;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Transactional
public class Item {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(length = 50)
	private String nome;

	@Column(length = 150)
	private String descricao;

	private String imagem;

	@Column(precision = 2)
	private Double preco;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getPrecoFormatado()
	{
		return StringUtils.FormatarParaDinheiro(preco);
	}
}
