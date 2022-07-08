package io.swagger.app.facade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.swagger.app.repository.IPersonaRepository;
import io.swagger.commons.entity.Persona;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@Transactional(readOnly = true)
public class PersonaFacade {

	private IPersonaRepository personaRepo;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public PersonaFacade(IPersonaRepository personaRepo) {
		super();
		this.personaRepo = personaRepo;
	}

	@Transactional(readOnly = false)
	public Persona addPersona(Persona body) {
		return this.personaRepo.save(body);
	}

	public HashMap<String, List<Persona>> getPersonas() {
		HashMap<String, List<Persona>> personas = new HashMap<String, List<Persona>>();
		log.info("TOTAL DE REGISTROS ==> " + this.personaRepo.count());

		personas.put("findByActivo", this.personaRepo.findByActivo(true));
		personas.put("findByActivoAndNombre", this.personaRepo.findByActivoAndNombre(true, "MAXIMILIANO"));
		personas.put("findByActivoOrNombre", this.personaRepo.findByActivoOrNombre(true, "Martin"));
		personas.put("findByNombreIgnoreCase", this.personaRepo.findByNombreIgnoreCase("JaViER"));
		personas.put("findByEdadOrderByFechaRegistroAsc", this.personaRepo.findByEdadOrderByFechaRegistroAsc(17));
		personas.put("findByEdadOrderByFechaRegistroDesc", this.personaRepo.findByEdadOrderByFechaRegistroDesc(17));

		Pageable ordenadoEdadDesc = PageRequest.of(0, 2, Sort.by("edad").descending());
		Page<Persona> paginas = this.personaRepo.findAll(ordenadoEdadDesc);
		System.out.println("TOTAL PAGINAS ==> " + paginas.getTotalPages());
		while (!paginas.isEmpty()) {
			// SALTO A LA SIGUIENTE PAGINA
			ordenadoEdadDesc = ordenadoEdadDesc.next();
			// CICLO 2 ENTIDADES DE HOJA ACTUAL
			paginas.forEach(entity -> System.out.println(entity.getId() + " -- " + entity.getFechaRegistro()));
			// SE CONSULTA LA SIGUIENTE HOJA, CON SUS 2 REGISTROS
			paginas = this.personaRepo.findAll(ordenadoEdadDesc);
		}

		List<Persona> personaSalariosnNoNulos = new ArrayList<>();
		try (Stream<Persona> stream = this.personaRepo.readAllBySalarioNotNull()) {
			stream.forEach(item -> {
				System.out.println(item.getNombre() + " ==> " + item.getSalario());
				personaSalariosnNoNulos.add(item);
			});
		}
		
		personas.put("readAllBySalarioNotNull", personaSalariosnNoNulos);

		String fromDateString = "2017-01-01 00:00:01.000";
		String toDateString = "2018-12-31 00:00:01.000";
		try {
			Date fromDate = formatter.parse(fromDateString);
			Date toDate = formatter.parse(toDateString);
			personas.put("findByFechaRegistroBetween", this.personaRepo.findByFechaRegistroBetween(fromDate, toDate));	
		} catch (Exception e) {
			log.error(e);
		}
		
		return personas;
	}


	public HashMap<String, List<Persona>> getPersonasJPQL(){
		HashMap<String, List<Persona>> personas = new HashMap<String, List<Persona>>();
		personas.put("DISTINCT", this.personaRepo.findDistinctByNombre("Juana"));
		personas.put("AND", this.personaRepo.findByNombreAndActivo("Juana", true));
		personas.put("OR", this.personaRepo.findByNombreOrActivo("Juana", true));
		personas.put("equals", this.personaRepo.findByNombreEquals("Rogelio"));

		try {
			String fromDateString = "2017-01-01 00:00:01.000";
			String toDateString = "2018-12-31 00:00:01.000";
			Date fromDate = formatter.parse(fromDateString);
			Date toDate = formatter.parse(toDateString);
			personas.put("findByFechaRegistroBetween", this.personaRepo.findByFechaRegistroBetween(fromDate, toDate));	
		} catch (Exception e) {
			log.error(e);
		}

		personas.put("lessThan", this.personaRepo.findByEdadLessThanJPQL(18));
		personas.put("greaterThan", this.personaRepo.findByEdadGreaterThanJPQL(18));
		personas.put("lessThanEqual", this.personaRepo.findByEdadLessThanOrEqualToJPQL(18));
		personas.put("greaterThanEqual", this.personaRepo.findByEdadGreaterThanOrEqualToJPQL(18));
		personas.put("Is null", this.personaRepo.findBySalarioIsNullJPQL());
		personas.put("Is not null", this.personaRepo.findBySalarioIsNotNullJPQL());
		personas.put("Like", this.personaRepo.findByNombreLikeJPQL("juana"));
		personas.put("Not Like", this.personaRepo.findByNombreNotLikeJPQL("juana"));
		personas.put("StartingWith", this.personaRepo.findByNombreStartsWithJPQL("%juana"));
		personas.put("EndingWith", this.personaRepo.findByNombreEndsWithJPQL("juana%"));
		personas.put("Containing", this.personaRepo.findByNombreContainsJPQL("%juana%"));
		personas.put("In", this.personaRepo.findByNombreInJPQL(Arrays.asList("Juana", "Rogelio")));
		personas.put("Not In", this.personaRepo.findByNombreNotInJPQL(Arrays.asList("Juana", "Manuel")));
		personas.put("OrderBy asc", this.personaRepo.findByNombreOrderByCustomAscJPQL("nombre"));
		personas.put("OrderBy desc", this.personaRepo.findByNombreOrderByCustomDescJPQL("nombre"));
		personas.put("IgnoreCase", this.personaRepo.findByNombreIgnoreCaseJPQL("juana"));

		
		return personas;
	}
	public Optional<Persona> findById(Long idPersona) {
		return this.personaRepo.findById(idPersona);
	}

	public List<Persona> findAll() {
		return this.personaRepo.findAll();
	}

}
