package io.swagger.commons.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ApiModel(description = "Contiene información de curso")
@Table(name = "curso", indexes = { @Index(name = "curso_idx", columnList = "curso_id") })
public class Curso {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "curso_id")
	@JsonIgnore
	private Long id;

	@Column(name = "nombre", nullable = false, length = 25)
	@ApiModelProperty(value = "Nombre de la curso")
	private String nombre;

	@Column(name = "clave", nullable = true, length = 7)
	@ApiModelProperty(value = "Clave identificadora de curso")
	private String clave;

	@Column(name = "activo", nullable = false)
	@ApiModelProperty(value = "Estado activo del curso")
	private Boolean activo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "curso")
	Set<Inscripcion> inscripcion;
	
}