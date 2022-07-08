package io.swagger.app.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import io.swagger.app.facade.MascotaFacade;
import io.swagger.app.facade.PersonaFacade;
import io.swagger.app.services.IPersonas;
import io.swagger.commons.entity.Mascota;
import io.swagger.commons.entity.Persona;
import io.swagger.commons.model.ResponseModel;

@Service
public class PersonasServices implements IPersonas {

	private PersonaFacade personafacade;
	private MascotaFacade mascotaFacade;

	public PersonasServices(PersonaFacade personafacade, MascotaFacade mascotaFacade) {
		super();
		this.personafacade = personafacade;
		this.mascotaFacade = mascotaFacade;
	}

	@Override
	public Persona addPersona(Persona body) {
		Persona p = this.personafacade.addPersona(body);
		return p;
	}

	@Override
	public Mascota addMascotaPersona(Mascota body, Long idPersona) {
		body.setPlaca(RandomStringUtils.randomAlphanumeric(12));
		Optional<Persona> p = this.personafacade.findById(idPersona);
		if (p.isPresent()) 
			body.setPersona(p.get());
		Mascota m = this.mascotaFacade.addMascotaPersona(body);
		return m;
	}

	@Override
	public ResponseModel getPersonasMascotas() {
		ResponseModel response = new ResponseModel();
		HashMap<String , List<Persona>> personas = this.personafacade.getPersonas();
		response.setPersonas(personas);
		List<Persona> personal = this.personafacade.findAll();
		HashMap<String , List<Mascota>> mascotas = this.mascotaFacade.getMascotas(personal);	
		response.setMascotas(mascotas);
		return response;
	}

	@Override
	public ResponseModel getPersonasJPQL () {
		ResponseModel response = new ResponseModel();
		HashMap<String , List<Persona>> personas = this.personafacade.getPersonasJPQL();
		response.setPersonas(personas);
		return response;
	}

}
