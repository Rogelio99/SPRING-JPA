package io.swagger.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.swagger.commons.entity.Mascota;
import io.swagger.commons.entity.Persona;
import io.swagger.commons.utils.MascotaCategoria;

@Repository
public interface IMascotaRepository extends JpaRepository<Mascota, Long> {
	List<Mascota> findByPersona(Persona persona);
	List<Mascota> findTop3ByOrderByCategoriaAsc();
	List<Mascota> findByCategoriaGreaterThanEqual(MascotaCategoria perro);
	List<Mascota> findByNombreNotLike(String nombre);
}
