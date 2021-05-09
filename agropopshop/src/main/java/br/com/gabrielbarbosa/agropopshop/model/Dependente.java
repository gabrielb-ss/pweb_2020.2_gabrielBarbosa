package br.com.gabrielbarbosa.agropopshop.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="dependentes")

public class Dependente implements Serializable {

	private static final long serialVersionUID = 235594939766088109L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int id_pai;
	
	private String nome;
	private String genero;
	private Date nascimento;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public int getId_pai() {
		return id_pai;
	}
	public void setId_pai(int id_pai) {
		this.id_pai = id_pai;
	}
	
}

