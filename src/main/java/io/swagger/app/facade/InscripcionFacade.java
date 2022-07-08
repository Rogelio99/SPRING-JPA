package io.swagger.app.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.swagger.app.repository.IInscripcionRepository;
import io.swagger.commons.entity.Inscripcion;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@Transactional(readOnly = true)
public class InscripcionFacade {

	private IInscripcionRepository inscripcionRepo;

	public InscripcionFacade(IInscripcionRepository inscripcionRepo) {
		super();
		this.inscripcionRepo = inscripcionRepo;
	}

	@Transactional(readOnly = false)
	public Inscripcion addInscripcion(Inscripcion body) {
		return this.inscripcionRepo.save(body);
	}

	public Optional<Inscripcion> findById(Long idInscripcion) {
		return this.inscripcionRepo.findById(idInscripcion);
	}

	public List<Inscripcion> findAll() {
		return this.inscripcionRepo.findAll();
	}

}
