package io.swagger.app.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.app.facade.CursoFacade;
import io.swagger.app.facade.EstudianteFacade;
import io.swagger.app.facade.InscripcionFacade;
import io.swagger.app.services.IEstudiantes;
import io.swagger.commons.entity.Curso;
import io.swagger.commons.entity.Estudiante;
import io.swagger.commons.entity.Inscripcion;
import io.swagger.commons.entity.Persona;
import io.swagger.commons.model.CursoModel;
import io.swagger.commons.model.EstudianteModel;
import io.swagger.commons.model.EstudiantesCursosModel;
import io.swagger.commons.model.ResponseColegioModel;
import io.swagger.commons.utils.Constantes;

@Service
public class EstudiantesServices implements IEstudiantes {

	private EstudianteFacade estudianteFacade;
	private CursoFacade cursoFacade;
	private InscripcionFacade inscripcionFacade;

	@Autowired
	public EstudiantesServices(EstudianteFacade estudianteFacade, CursoFacade cursoFacade, InscripcionFacade inscripcionFacade) {
		super();
		this.estudianteFacade = estudianteFacade;
		this.cursoFacade = cursoFacade;
		this.inscripcionFacade = inscripcionFacade;
	}

	@Override
	public ResponseColegioModel getEstudiantesCursos() {
		ResponseColegioModel response = new ResponseColegioModel();
		
		HashMap<String, List<Estudiante>> estudiantes = new HashMap<String, List<Estudiante>>();
		estudiantes.put("consultaTodos", this.estudianteFacade.consultaTodos());
		List<EstudianteModel> estudiantesModelResumen = this.estudianteFacade.consultaTodosResumen();
		List<Estudiante> estudiantesResumen = new ArrayList<>();
		
		estudiantesModelResumen.stream().forEach(estudianteR -> {
			Estudiante e = new Estudiante();
			e.setNombre(estudianteR.getNombre());
			e.setPromedio(estudianteR.getPromedio());
			estudiantesResumen.add(e);
		});
		
		estudiantes.put("consultaTodosResumen", estudiantesResumen);
		estudiantes.put("joinCurso", this.estudianteFacade.joinCurso());
		response.setEstudiantes(estudiantes);
		HashMap<String, List<Curso>> cursos = new HashMap<String, List<Curso>>();
		response.setCursos(cursos);
		
		return response;
	}

	@Override
	public EstudianteModel addEstudiante(@Valid EstudianteModel body) {
		Estudiante e = new Estudiante();
		e.setNombre(body.getNombre());
		e.setGenero(body.getGenero());
		e.setPromedio(body.getPromedio());
		e = this.estudianteFacade.addEstudiante(e);
		return e.getId() != null ? body : null;
	}

	@Override
	public CursoModel addCursos(@Valid CursoModel body) {
		Curso c = new Curso();
		c.setNombre(body.getNombre());
		c.setClave(RandomStringUtils.randomAlphanumeric(7));
		c.setActivo(body.getActivo());
		c = this.cursoFacade.addCurso(c);
		return c.getId() != null ? body : null;
	}

	@Override
	public EstudiantesCursosModel addEstudiantesCursos(@Valid EstudiantesCursosModel body) {
		EstudiantesCursosModel responseEstudiantesCursos = new EstudiantesCursosModel();
		List<Long> estudiantes = new ArrayList<>();
		List<Long> cursos = new ArrayList<>();
		body.getEstudiantes().stream().forEach(estudiante -> {
			Optional<Estudiante> e = this.estudianteFacade.findById(estudiante);
			if(e.isPresent()) {
				body.getCursos().stream().forEach(curso -> {
					Optional<Curso> c = this.cursoFacade.findById(curso);
					if(c.isPresent()) {
						Inscripcion i = new Inscripcion();
						i.setEstudiante(e.get());
						i.setCurso(c.get());
						this.inscripcionFacade.addInscripcion(i);
						if(!cursos.contains(c.get().getId())) {
							cursos.add(c.get().getId());
						}
					}
				});
				estudiantes.add(e.get().getId());
			}
		});
		responseEstudiantesCursos.setCursos(cursos);
		responseEstudiantesCursos.setEstudiantes(estudiantes);
		return responseEstudiantesCursos;
	}

}
