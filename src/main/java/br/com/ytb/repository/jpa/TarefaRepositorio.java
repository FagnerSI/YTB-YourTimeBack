package br.com.ytb.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ytb.model.Card;
import br.com.ytb.model.Tarefa;

@Repository
public interface TarefaRepositorio extends JpaRepository<Tarefa, Long> {
	
	public List<Tarefa> findByCard(Card card);

}
