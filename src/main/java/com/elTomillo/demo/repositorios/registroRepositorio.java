package com.elTomillo.demo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elTomillo.demo.entidades.registro;

@Repository
public interface registroRepositorio extends JpaRepository<registro, String> {

	@Query(value = "SELECT a FROM Usuario a")
	List<registro> findAllusuarios();
	
	@Query(value = "SELECT c FROM Usuario c WHEREc.id = :id")
	List<registro> findfindById(@Param("id") String id);

	@Query("SELECT c FROM Usuario c WHERE c.email = :email")
	public registro buscarPorEmail(@Param("email") String email);
}