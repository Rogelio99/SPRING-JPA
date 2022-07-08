package io.swagger.commons.model;

import java.util.HashMap;
import java.util.List;

import io.swagger.commons.entity.Mascota;
import io.swagger.commons.entity.Persona;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseModel {
	private HashMap<String, List<Persona>> personas;
	private HashMap<String, List<Mascota>> mascotas;
}
