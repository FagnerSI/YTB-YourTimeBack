package br.com.ytb.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table(name ="user")
public class Usuario {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;	

	@NotEmpty(message = "")
	private String nome;
	
	@NotNull 
	@NotEmpty(message = "Já existente")
	@Column(unique=true)
	private String userName;	
	
	@NotNull
	@NotEmpty
	@Size(min = 6, max = 32 , message = "O tamanho deve ser entre {min} e {max}")
	private String senha;	
	
	@OneToMany(mappedBy = "user", cascade={CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.REMOVE})	
	private List<Card> cards;	
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, String userName, String senha) {
		this.nome = nome;
		this.userName = userName;
		this.senha = senha;		
	} 
	
	public Usuario(String nome, String userName, String senha, List<Card> cards) {
		this.nome = nome;
		this.userName = userName;
		this.senha = senha;
		this.cards = cards;
	}   
	
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
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
		
	


	
}
