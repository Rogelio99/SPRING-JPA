package io.swagger.app.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.swagger.app.repository.IEstudianteRepository;
import io.swagger.commons.entity.Estudiante;
import io.swagger.commons.model.EstudianteModel;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@Transactional(readOnly = true)
public class EstudianteFacade {

	private IEstudianteRepository estudianteRepo;

	public EstudianteFacade(IEstudianteRepository estudianteRepo) {
		super();
		this.estudianteRepo = estudianteRepo;
	}

	@Transactional(readOnly = false)
	public Estudiante addEstudiante(Estudiante body) {
		return this.estudianteRepo.save(body);
	}

	public Optional<Estudiante> findById(Long idEstudiante) {
		return this.estudianteRepo.findById(idEstudiante);
	}

	public List<Estudiante> findAll() {
		return this.estudianteRepo.findAll();
	}

	public List<Estudiante> consultaTodos() {
		return this.estudianteRepo.consultaTodos();
	}

	public List<EstudianteModel> consultaTodosResumen() {
		return this.estudianteRepo.consultaTodosResumen();
	}

	public List<Estudiante> joinCurso() {
		return this.estudianteRepo.joinCurso();
	}

}
