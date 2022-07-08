package io.swagger.app.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.swagger.app.repository.ICursoRepository;
import io.swagger.commons.entity.Curso;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@Transactional(readOnly = true)
public class CursoFacade {

	private ICursoRepository cursoRepo;

	public CursoFacade(ICursoRepository cursoRepo) {
		super();
		this.cursoRepo = cursoRepo;
	}

	@Transactional(readOnly = false)
	public Curso addCurso(Curso body) {
		return this.cursoRepo.save(body);
	}

	public Optional<Curso> findById(Long idCurso) {
		return this.cursoRepo.findById(idCurso);
	}

	public List<Curso> findAll() {
		return this.cursoRepo.findAll();
	}

}
