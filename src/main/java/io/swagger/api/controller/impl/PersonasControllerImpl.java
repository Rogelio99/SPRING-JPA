package io.swagger.api.controller.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.api.controller.IPersonasController;
import io.swagger.app.services.IPersonas;
import io.swagger.commons.entity.Mascota;
import io.swagger.commons.entity.Persona;
import io.swagger.commons.model.ResponseModel;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = "personas")
public class PersonasControllerImpl implements IPersonasController {

	private final HttpServletRequest request;
	private IPersonas cliente;

	@Autowired
	public PersonasControllerImpl(HttpServletRequest request, IPersonas cliente) {
		this.request = request;
		this.cliente = cliente;
	}

	@Override
	public ResponseEntity<Persona> addPersona(@Valid Persona body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				Persona persona = this.cliente.addPersona(body);
				return new ResponseEntity<Persona>(persona, HttpStatus.OK);
			} catch (Exception e) {
				log.error(e);
				return new ResponseEntity<Persona>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<Persona>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<Mascota> addMascotaPersona(Mascota body, Long idPersona) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				Mascota mascota = this.cliente.addMascotaPersona(body, idPersona);
				return new ResponseEntity<Mascota>(mascota, HttpStatus.OK);
			} catch (Exception e) {
				log.error(e);
				return new ResponseEntity<Mascota>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<Mascota>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<ResponseModel> getPersonasMascotas() {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				ResponseModel personasMascotas= this.cliente.getPersonasMascotas();
				return new ResponseEntity<ResponseModel>(personasMascotas, HttpStatus.OK);
			} catch (Exception e) {
				log.error(e);
				return new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<ResponseModel>(HttpStatus.NOT_IMPLEMENTED);
	}
	
}
