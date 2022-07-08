package io.swagger.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.swagger.commons.entity.Estudiante;
import io.swagger.commons.model.EstudianteModel;

@Repository
public interface IEstudianteRepository extends JpaRepository<Estudiante, Long> {

	@Query("SELECT e FROM Estudiante e")
	List<Estudiante> consultaTodos();
	
	@Query("SELECT NEW io.swagger.commons.model.EstudianteModel(e.nombre, e.promedio) FROM Estudiante e")
	List<EstudianteModel> consultaTodosResumen();

	@Query("SELECT e FROM Estudiante e JOIN FETCH e.inscripcion i "
			+ " JOIN FETCH i.curso c WHERE c.id = 2")
	List<Estudiante> joinCurso();

}
