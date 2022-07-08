package io.swagger.app.repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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


	//JPQL

	// DISTINCT
	@Query("SELECT DISTINCT p FROM Persona p WHERE p.nombre = ?1")
	List<Persona> findDistinctByNombre(String nombre);

	// AND
	@Query("SELECT p FROM Persona p WHERE p.nombre = ?1 AND p.activo = ?2")
	List<Persona> findByNombreAndActivo(String nombre, Boolean activo);

	// OR
	@Query("SELECT p FROM Persona p WHERE p.nombre = ?1 OR p.activo = ?2")
	List<Persona> findByNombreOrActivo(String nombre, Boolean activo);

	// EQUALS
	@Query("SELECT p FROM Persona p WHERE p.nombre = ?1")
	List<Persona> findByNombreEquals(String nombre);

	// BETWEEN
	@Query("SELECT p FROM Persona p WHERE p.fechaRegistro BETWEEN ?1 AND ?2")
	List<Persona> findByFechaRegistroBetweenJPQL(Date fromDate, Date toDate);

	// Less than
	@Query("SELECT p FROM Persona p WHERE p.edad < ?1")
	List<Persona> findByEdadLessThanJPQL(Integer edad);

	// Less than or equal to
	@Query("SELECT p FROM Persona p WHERE p.edad <= ?1")
	List<Persona> findByEdadLessThanOrEqualToJPQL(Integer edad);

	// Greater than
	@Query("SELECT p FROM Persona p WHERE p.edad > ?1")
	List<Persona> findByEdadGreaterThanJPQL(Integer edad);

	// Greater than or equal to
	@Query("SELECT p FROM Persona p WHERE p.edad >= ?1")
	List<Persona> findByEdadGreaterThanOrEqualToJPQL(Integer edad);

	// Is null
	@Query("SELECT p FROM Persona p WHERE p.salario IS NULL")
	List<Persona> findBySalarioIsNullJPQL();

	// Is not null
	@Query("SELECT p FROM Persona p WHERE p.salario IS NOT NULL")
	List<Persona> findBySalarioIsNotNullJPQL();

	// Like
	@Query("SELECT p FROM Persona p WHERE p.nombre LIKE ?1")
	List<Persona> findByNombreLikeJPQL(String nombre);

	// Not like
	@Query("SELECT p FROM Persona p WHERE p.nombre NOT LIKE ?1")
	List<Persona> findByNombreNotLikeJPQL(String nombre);

	// Starts with
	@Query("SELECT p FROM Persona p WHERE p.nombre LIKE ?1")
	List<Persona> findByNombreStartsWithJPQL(String nombre);

	// Ends with
	@Query("SELECT p FROM Persona p WHERE p.nombre LIKE ?1")
	List<Persona> findByNombreEndsWithJPQL(String nombre);

	// Contains
	@Query("SELECT p FROM Persona p WHERE p.nombre LIKE ?1")
	List<Persona> findByNombreContainsJPQL(String nombre);

	// In
	@Query("SELECT p FROM Persona p WHERE p.nombre IN ?1")
	List<Persona> findByNombreInJPQL(List<String> nombres);

	// Not in
	@Query("SELECT p FROM Persona p WHERE p.nombre NOT IN ?1")
	List<Persona> findByNombreNotInJPQL(List<String> nombres);

	// Order by asc
	@Query("SELECT p FROM Persona p ORDER BY ?1 ASC")
	List<Persona> findByNombreOrderByCustomAscJPQL(String column);

	// Order by desc
	@Query("SELECT p FROM Persona p ORDER BY ?1 DESC")
	List<Persona> findByNombreOrderByCustomDescJPQL(String column);

	// Ignore case
	@Query("SELECT p FROM Persona p WHERE UPPER(p.nombre) = UPPER(?1)")
	List<Persona> findByNombreIgnoreCaseJPQL(String nombre);


}
