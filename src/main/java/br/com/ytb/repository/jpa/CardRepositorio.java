package br.com.ytb.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ytb.model.Card;
import br.com.ytb.model.Usuario;

@Repository
public interface CardRepositorio extends JpaRepository<Card, Long> {	
	
	/*@Query(value = "select * from card where user_id = ?1", nativeQuery = true)
	List<Card> findCardByUserId(Long id); */
	
	public List<Card> findByUser(Usuario user);
	
	
	
}
