package io.swagger.commons.model;

import java.math.BigDecimal;

import javax.persistence.Column;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EstudianteModel {
	
	public EstudianteModel(String nombre, BigDecimal promedio) {
		super();
		this.nombre = nombre;
		this.promedio = promedio;
	}

	@ApiModelProperty(value = "Nombre de la estudiante")
	private String nombre;

	@ApiModelProperty(value = "Promedio general del estudiante")
	private BigDecimal promedio;
	
	@ApiModelProperty(value = "Genero del estudiante - (1=Masculino/2=Femenino)")
	private Integer genero;
}
