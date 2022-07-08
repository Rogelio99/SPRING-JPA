package io.swagger.commons.model;

import java.util.HashMap;
import java.util.List;

import io.swagger.commons.entity.Curso;
import io.swagger.commons.entity.Estudiante;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseColegioModel {
	private HashMap<String, List<Estudiante>> estudiantes;
	private HashMap<String, List<Curso>> cursos;
}
