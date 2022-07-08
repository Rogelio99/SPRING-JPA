/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.34).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.commons.model.CursoModel;
import io.swagger.commons.model.EstudianteModel;
import io.swagger.commons.model.EstudiantesCursosModel;
import io.swagger.commons.model.ResponseColegioModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Validated
public interface IColegiosController {

	@Operation(summary = "Agrega un estudiante nuevo", description = "", tags = { "colegios" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstudianteModel.class))),
			@ApiResponse(responseCode = "500", description = "Estudiante no válido") })
	@RequestMapping(value = "/estudiantes", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<EstudianteModel> addEstudiante(@Valid @RequestBody EstudianteModel body);

	@Operation(summary = "Agrega un curso nuevo", description = "", tags = { "colegios" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CursoModel.class))),
			@ApiResponse(responseCode = "500", description = "Curso no válido") })
	@RequestMapping(value = "/cursos", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<CursoModel> addCurso(@Valid @RequestBody CursoModel body);

	@Operation(summary = "Agrega inscripciones de estudiantes a cursos", description = "", tags = { "colegios" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstudiantesCursosModel.class))),
			@ApiResponse(responseCode = "500", description = "Curso no válido") })
	@RequestMapping(value = "/inscripciones", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<EstudiantesCursosModel> addInscripciones(@Valid @RequestBody EstudiantesCursosModel body);

	@Operation(summary = "Consulta información de estudiantes inscritos en cursos", description = "Uso de multiples metodos de busqueda JPQL", tags = {
			"colegios" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation") })
	@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<ResponseColegioModel> getEstudiantesCursos();

}
