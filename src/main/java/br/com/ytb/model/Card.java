package br.com.ytb.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


@Entity
@Table(name = "card")
public class Card {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "card_id")
	private Long id;

	@NotNull 	    
	@NotEmpty
	@Size(max = 32 , message = "No maximo {max} caracteres.")
	private String titulo;

	@Size(max = 100, message = "No maximo {max} caracteres.")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private Usuario user;

	@OneToMany(mappedBy="card", cascade={CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.REMOVE})	
	private List<Tarefa> tarefas;  

	public Card(){

	}

	public Card(String titulo, String descricao, List<Tarefa> tarefas){	    	
		this.titulo = titulo;
		this.descricao = descricao;
		this.tarefas = tarefas;		
	}

	public Card(String titulo, String descricao, List<Tarefa> tarefas, Usuario user){	    	
		this.titulo = titulo;
		this.descricao = descricao;
		this.tarefas = tarefas;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	
	@Override
    public String toString() {
    	ObjectMapper mapper = new ObjectMapper();
    	
    	String jsonString = "";
		try {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			jsonString = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
    	return jsonString;
    }

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;

		Card other = (Card) obj;		
		if(id == null) {
			if (other.getId() != null)
				return false;
		}
		else if (!id.equals(other.getId()))
			return false;

		return true;
	}


}
