package br.com.start.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClienteEntradaDto {
	
	@ApiModelProperty(example = "Luiza de Araujo", value = "Nome do cliente", required = true)
	@NotEmpty(message = "campo obrigatório")
	@Size( max=200, message="máximo permitido é 200 caracters")
	private String nome;
	
	@ApiModelProperty(example = "11111111111", value = "Cpf do cliente", required = true)
	@NotEmpty(message = "campo obrigatório")
	@Size( max=11, message="ERRO, cpf inválido")
	private String cpf;
	
	@ApiModelProperty(example = "20.00", value = "Saldo inicial do cliente", required = true)
	@Digits(integer = 7, fraction = 2, message = "inválido")
	@DecimalMin(value = "5.00", message = "O valor mínimo para abertura da conta é 5.00")
	private BigDecimal saldo;
	
	@ApiModelProperty(example = "1000.00", value = "Renda do cliente", required = true)
	@Digits(integer = 7, fraction = 2, message = "inválido")
	@DecimalMin(value = "1000.00", message = "O valor mínimo de sua renda deve ser de 1000.00")
	private BigDecimal renda;
	
	@ApiModelProperty(example = "11111", value = "Numero da conta", required = true)
	@NotEmpty(message = "campo obrigatório")
	@Size( max=5, message="ERRO, conta inválido")
	private String numeroConta;
	
	@ApiModelProperty(example = "CONTA_CORRENTE", value = "Tipo da conta", required = true)
	@NotNull
	@Pattern(regexp = "CONTA_CORRENTE|CONTA_SALARIO|CONTA_CONJUNTA", message = "inválido")
	private String tipoConta;
	
	@ApiModelProperty(example = "PLATINUM", value = "Classificacao da conta", required = true)
	@NotNull
	@Pattern(regexp = "GOLD|PLATINUM|PREMIUM|BLACK", message = "inválido")
	private String classificacao;
	
	@ApiModelProperty(example = "15/05/2023", value = "Data de abertura da conta", required = false)
	private LocalDate dataAberturaConta;
	
	@ApiModelProperty(example = "01/01/1930", value = "Data de nascimento do cliente", required = true)
	@Past(message = "data inválida")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@ApiModelProperty(example = "1", value = "id do banco do cliente", required = true)
	@NotNull
	private Long idBanco;


}
