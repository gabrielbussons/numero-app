package com.numero.app.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.numero.app.Model.RegistroModel;


@Repository
public interface RegistroRepository extends JpaRepository<RegistroModel, Long>{

	@Query("SELECT r FROM RegistroModel r WHERE (:dataRegistro is null or r.dataRegistro = :dataRegistro) and (:email is null or r.email = :email) and "
			+ "(:numero is null or r.numeroChamado = :numero) and (:resultado is null or r.resultado = :resultado)")
	List<RegistroModel> findRegistros(
			@Param("dataRegistro") String dataRegistro, @Param("email") String email, 
			@Param("numero") Integer numero, @Param("resultado") String resultado);

}