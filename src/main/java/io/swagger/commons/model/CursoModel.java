package io.swagger.commons.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CursoModel {

	@ApiModelProperty(value = "Nombre de la curso")
	private String nombre;

	@ApiModelProperty(value = "Clave identificadora de curso")
	private String clave;

	@ApiModelProperty(value = "Estado activo del curso")
	private Boolean activo;
	
}
