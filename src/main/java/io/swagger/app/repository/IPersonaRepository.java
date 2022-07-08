package io.swagger.app.repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.swagger.commons.entity.Persona;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Long> {

	// USO DE BUSQUEDA INDIVIDUAL
	List<Persona> findByActivo(Boolean activo);

	// USO DE BUSQUEDA COMBINADA USANDO OPERADOR LOGICO
	List<Persona> findByActivoAndNombre(Boolean activo, String nombre);

	// USO DE BUSQUEDA COMBINADA USANDO OPERADOR LOGICO
	List<Persona> findByActivoOrNombre(Boolean activo, String nombre);

	// USO DE 'distinct' EN QUERY
	List<Persona> findPersonaDistinctByNombre(String nombre);

	// USO DE 'ignoring case' EN QUERY, PARA UNA SOLA PROPIEDAD
	List<Persona> findByNombreIgnoreCase(String nombre);

	// USO ORDER BY EN QUERY
	List<Persona> findByEdadOrderByFechaRegistroAsc(Integer edad);

	List<Persona> findByEdadOrderByFechaRegistroDesc(Integer edad);

	// USO DE REMOVE
	List<Persona> removeByNombre(String nombre);

	// USO DE FIRST
	Persona findFirstByOrderByNombreAsc();

	// USO DE FIRST CON UN TOP
	List<Persona> findFirst10ByEdad(Integer edad, Sort sort);

	// USO DE STREAM Y NO NULOS
	Stream<Persona> readAllBySalarioNotNull();

	// USO DE BETWEEN
	List<Persona> findByFechaRegistroBetween(Date fromDate, Date toDate);

}
