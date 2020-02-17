package com.allissonjardel.departamentoBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.allissonjardel.departamentoBackend.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{

	@Query(value = "SELECT COUNT(*) FROM DEPARTAMENTO WHERE NOME = ?1", nativeQuery = true)
	Integer existByName(String nome);
	
}
