package br.com.ytb.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ytb.model.Usuario;


@Repository
public interface UserRepositorio extends JpaRepository<Usuario, Integer> {
	
	@Query(value = "select * from user where user_name = ?1  and senha = ?2", nativeQuery = true)
	public Usuario autenticar(String nome, String senha); 
	
    public Usuario findById(Long id); 	
 

    
}