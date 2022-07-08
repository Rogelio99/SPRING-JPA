package io.swagger.commons.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ApiModel(description = "Contiene información de inscripcion del alumnos a cursos")
@Table(name = "inscripcion", indexes = { @Index(name = "inscripcion_idx", columnList = "id") })
public class Inscripcion {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "estudiante_id")
	Estudiante estudiante;

	@ManyToOne
	@JoinColumn(name = "curso_id")
	Curso curso;

}