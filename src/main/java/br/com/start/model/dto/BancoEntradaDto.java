package br.com.start.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BancoEntradaDto {
	
	@ApiModelProperty(example = "Bradesco", value = "Nome do Banco", required = true)
	@NotEmpty(message = "campo obrigatório")
	@Size( max=200, message="ERRO, máximo permitido é 200 caracters")
	private String nome;
	
	@ApiModelProperty(example = "123", value = "Código do Banco", required = true)
	@NotEmpty(message = "campo obrigatório")
	@Size( max=5, message="ERRO, máximo permitido é 5 caracters")
	private String codigo;

}
