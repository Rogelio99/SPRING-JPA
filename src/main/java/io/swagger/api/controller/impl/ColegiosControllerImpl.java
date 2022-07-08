package io.swagger.api.controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.api.controller.IColegiosController;
import io.swagger.app.services.IEstudiantes;
import io.swagger.commons.model.CursoModel;
import io.swagger.commons.model.EstudianteModel;
import io.swagger.commons.model.EstudiantesCursosModel;
import io.swagger.commons.model.ResponseColegioModel;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = "colegios")
public class ColegiosControllerImpl implements IColegiosController {

	private final HttpServletRequest request;
	private IEstudiantes estudiantes;

	@Autowired
	public ColegiosControllerImpl(HttpServletRequest request, IEstudiantes estudiantes) {
		this.request = request;
		this.estudiantes = estudiantes;
	}

	@Override
	public ResponseEntity<EstudianteModel> addEstudiante(@Valid EstudianteModel body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				EstudianteModel estudiante = this.estudiantes.addEstudiante(body);
				return new ResponseEntity<EstudianteModel>(estudiante, HttpStatus.OK);
			} catch (Exception e) {
				log.error(e);
				return new ResponseEntity<EstudianteModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<EstudianteModel>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<CursoModel> addCurso(@Valid CursoModel body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				CursoModel curso = this.estudiantes.addCursos(body);
				return new ResponseEntity<CursoModel>(curso, HttpStatus.OK);
			} catch (Exception e) {
				log.error(e);
				return new ResponseEntity<CursoModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<CursoModel>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<EstudiantesCursosModel> addInscripciones(@Valid EstudiantesCursosModel body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				EstudiantesCursosModel estudiantesCursos = this.estudiantes.addEstudiantesCursos(body);
				return new ResponseEntity<EstudiantesCursosModel>(estudiantesCursos, HttpStatus.OK);
			} catch (Exception e) {
				log.error(e);
				return new ResponseEntity<EstudiantesCursosModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<EstudiantesCursosModel>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<ResponseColegioModel> getEstudiantesCursos() {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				ResponseColegioModel response = new ResponseColegioModel();
				response = this.estudiantes.getEstudiantesCursos();
				return new ResponseEntity<ResponseColegioModel>(response, HttpStatus.OK);
			} catch (Exception e) {
				log.error(e);
				return new ResponseEntity<ResponseColegioModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<ResponseColegioModel>(HttpStatus.NOT_IMPLEMENTED);
	}

}
