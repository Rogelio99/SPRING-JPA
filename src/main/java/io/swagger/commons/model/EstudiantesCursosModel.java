package io.swagger.commons.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EstudiantesCursosModel {
	private List<Long> estudiantes;
	private List<Long> cursos;
}
