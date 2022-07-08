package io.swagger.commons.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
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
@ApiModel(description = "Contiene información del cliente")
@Table(name = "persona", indexes = { @Index(name = "persona_idx", columnList = "id"),@Index(name = "persona_activo_idx", columnList = "activo") })
public class Persona {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonIgnore
	private Long id;

	@Column(name = "nombre", nullable = false, length = 245)
	@ApiModelProperty(value = "Nombre de la persona")
	private String nombre;

	@Column(name = "activo", nullable = false)
	@ApiModelProperty(value = "Estado activo de persona")
	private Boolean activo;

	@Column(name = "edad", nullable = false)
	@ApiModelProperty(value = "Edad de persona")
	private Integer edad;
	
	@Column(name = "fecha_registro", nullable = false)
	@ApiModelProperty(value = "Fecha de nacimiento de persona")
	private Date fechaRegistro;
	
	@Column(name = "salario", nullable = true)
	@ApiModelProperty(value = "Salario de persona")
	private BigDecimal salario;
}