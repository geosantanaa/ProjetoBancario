package br.com.start.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClienteAlterarDto {
	
	@NotEmpty(message = "campo obrigatório")
	@Size( max=200, message="ERRO, máximo permitido é 200 caracters")
	private String nome;
	
	@Digits(integer = 7, fraction = 2, message = "inválido")
	@DecimalMin(value = "5.00", message = "O valor mínimo para depósito na conta é 5.00")
	private BigDecimal saldo;
	
	@Digits(integer = 7, fraction = 2, message = "inválido")
	@DecimalMin(value = "1000.00", message = "O valor mínimo de sua renda é de 1000.00")
	private BigDecimal renda;
	
	@NotNull(message = "campo obrigatório")
	@Pattern(regexp = "CONTA_CORRENTE|CONTA_SALARIO|CONTA_CONJUNTA", message = "inválido")
	private String tipoConta;


}
