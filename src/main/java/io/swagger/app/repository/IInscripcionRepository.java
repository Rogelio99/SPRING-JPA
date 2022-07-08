package io.swagger.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.swagger.commons.entity.Inscripcion;

@Repository
public interface IInscripcionRepository extends JpaRepository<Inscripcion, Long> {

}
