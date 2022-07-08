package io.swagger.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.swagger.commons.entity.Curso;

@Repository
public interface ICursoRepository extends JpaRepository<Curso, Long> {

}
