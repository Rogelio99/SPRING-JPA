package io.swagger.commons.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.commons.utils.MascotaCategoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ApiModel(description = "Contiene información de mascota")
@Table(name = "mascota", indexes = { @Index(name = "mascota_idx", columnList = "id") })
public class Mascota {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonIgnore
	private Long id;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "persona_id", foreignKey = @ForeignKey(name = "mascota_persona_id_fk"), nullable = false)
	private Persona persona;
	
	@NotNull(message = "El nombre de la mascota no es válido")
	@Column(name = "nombre", nullable = false, length = 245)
	@ApiModelProperty(value = "Nombre de la mascota")
	private String nombre;
	
	@NotNull(message = "Categoria de la mascota no es válida")
	@Column(columnDefinition = "smallint")
    private MascotaCategoria categoria;
	
	@Column(name = "placa", nullable = true, length = 12)
	@ApiModelProperty(value = "Placa identificadora de mascota")
	private String placa;
}