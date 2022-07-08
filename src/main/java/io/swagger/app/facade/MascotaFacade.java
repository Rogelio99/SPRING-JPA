package io.swagger.app.facade;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.swagger.app.repository.IMascotaRepository;
import io.swagger.commons.entity.Mascota;
import io.swagger.commons.entity.Persona;
import io.swagger.commons.utils.Constantes;
import io.swagger.commons.utils.MascotaCategoria;

@Component
@Transactional(readOnly = true)
public class MascotaFacade {

	private IMascotaRepository mascotaRepo;

	public MascotaFacade(IMascotaRepository mascotaRepo) {
		super();
		this.mascotaRepo = mascotaRepo;
	}

	@Transactional(readOnly = false)
	public Mascota addMascotaPersona(@Valid Mascota body) {
		return this.mascotaRepo.save(body);
	}

	public HashMap<String, List<Mascota>> getMascotas(List<Persona> personal) {
		HashMap<String, List<Mascota>> mascotas = new HashMap<String, List<Mascota>>();
		System.out.println(personal.get(Constantes.INTEGER_INDEX_PERSONA).getId() + " ==> "+ personal.get(Constantes.INTEGER_INDEX_PERSONA).getNombre());
		mascotas.put("findByPersona", this.mascotaRepo.findByPersona(personal.get(Constantes.INTEGER_INDEX_PERSONA)));
		mascotas.put("findTop3ByOrderByCategoriaAsc", this.mascotaRepo.findTop3ByOrderByCategoriaAsc());
		mascotas.put("findByCategoriaGreaterThanEqual", this.mascotaRepo.findByCategoriaGreaterThanEqual(MascotaCategoria.PERRO));
		mascotas.put("findByNombreNotLike", this.mascotaRepo.findByNombreNotLike(Constantes.STRING_NOMBRE_MASCOTA));
		return mascotas;
	}

}
