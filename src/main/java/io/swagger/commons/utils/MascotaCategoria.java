package io.swagger.commons.utils;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum MascotaCategoria {
	
	GATO(0,"GATO"),
	PERRO(1,"PERRO"),
	AVE(2,"AVE");

	private Integer numero;
	private String nombre;
	
	MascotaCategoria(Integer numero, String nombre) {
		this.numero = numero;
		this.nombre = nombre;
	}

	public static MascotaCategoria get(Integer numero) {
		return Arrays.stream(MascotaCategoria.values())
				.filter(categoria -> categoria.getNumero().equals(numero))
				.findFirst()
				.orElse(null);
	}
}
