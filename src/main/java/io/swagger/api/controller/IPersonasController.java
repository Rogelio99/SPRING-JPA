/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.34).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiParam;
import io.swagger.commons.entity.Mascota;
import io.swagger.commons.entity.Persona;
import io.swagger.commons.model.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Validated
public interface IPersonasController {

	@Operation(summary = "Agrega información de una persona", description = "", tags = { "personas" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Persona.class))),
			@ApiResponse(responseCode = "500", description = "Persona no válida") })
	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<Persona> addPersona(@Valid @RequestBody Persona body);

	@Operation(summary = "Agrega información de una mascota y relación con persona", description = "", tags = {
			"personas" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Mascota.class))),
			@ApiResponse(responseCode = "500", description = "Mascota no válida") })
	@RequestMapping(value = "{id}/mascota", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<Mascota> addMascotaPersona(@RequestBody Mascota body,
			@ApiParam(value = "Identificador de la persona", required = true) @PathVariable("id") Long idPersona);

	@Operation(summary = "Consulta información de persona", description = "Uso de multiples metodos de busqueda", tags = {
			"personas" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation") })
	@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<ResponseModel> getPersonasMascotas();

	@Operation(summary = "Consulta información de persona", description = "Uso de multiples metodos de busqueda", tags = {
		"personas" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation") })
	@RequestMapping(value = "/jpql", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<ResponseModel> getPersonasJQPL();


}
