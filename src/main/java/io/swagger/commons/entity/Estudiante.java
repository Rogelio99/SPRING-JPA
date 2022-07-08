package io.swagger.commons.entity;

import java.math.BigDecimal;
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
@ApiModel(description = "Contiene información de estudiante")
@Table(name = "estudiante", indexes = { @Index(name = "estudiante_idx", columnList = "estudiante_id") })
public class Estudiante {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "estudiante_id")
	@JsonIgnore
	private Long id;

	@Column(name = "nombre", nullable = false, length = 50)
	@ApiModelProperty(value = "Nombre de la estudiante")
	private String nombre;
	
	@Column(name = "promedio", nullable = false)
	@ApiModelProperty(value = "Promedio general del estudiante")
	private BigDecimal promedio;
	
	@Column(name = "genero", nullable = false)
	@ApiModelProperty(value = "Genero del estudiante - (1=Masculino/2=Femenino)")
	private Integer genero;

	@JsonIgnore
	@OneToMany(mappedBy = "estudiante")
	Set<Inscripcion> inscripcion;
}