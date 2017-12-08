 package br.com.ytb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "task")
public class Tarefa {
    
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "task_id")
	private Long id;
	
	@NotNull
	private String nome;
	private String prioridade;
	
	
	@ManyToOne
	@JoinColumn(name = "card_id", referencedColumnName = "card_id")
	private Card card;	
	
	
	public Tarefa () {
		
	}
	
	public Tarefa (String nome, Card card, String prioridade) {
		this.card = card;
		this.nome = nome;
		this.prioridade = prioridade;
	}
	
	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		
		Tarefa other = (Tarefa) obj;		
		if(id == null) {
			if (other.getId() != null)
				return false;
		}
		else if (!id.equals(other.getId()))
		return false;
		
		return true;
	}
	
	
	
}
