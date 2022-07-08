package io.swagger.app.services;

import javax.validation.Valid;

import io.swagger.commons.model.CursoModel;
import io.swagger.commons.model.EstudianteModel;
import io.swagger.commons.model.EstudiantesCursosModel;
import io.swagger.commons.model.ResponseColegioModel;

public interface IEstudiantes {

	ResponseColegioModel getEstudiantesCursos();

	EstudianteModel addEstudiante(@Valid EstudianteModel body);

	CursoModel addCursos(@Valid CursoModel body);

	EstudiantesCursosModel addEstudiantesCursos(@Valid EstudiantesCursosModel body);

}
