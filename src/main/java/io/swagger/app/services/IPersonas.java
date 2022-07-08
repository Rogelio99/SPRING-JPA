package io.swagger.app.services;

import javax.validation.Valid;

import io.swagger.commons.entity.Mascota;
import io.swagger.commons.entity.Persona;
import io.swagger.commons.model.ResponseModel;

public interface IPersonas {

	Persona addPersona(@Valid Persona body);

	Mascota addMascotaPersona(Mascota body, Long idPersona);

	ResponseModel getPersonasMascotas();

	ResponseModel getPersonasJPQL();

}
