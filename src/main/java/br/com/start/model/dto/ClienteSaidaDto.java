package br.com.start.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.start.model.Banco;
import br.com.start.model.ClassificacaoCliente;
import br.com.start.model.TipoConta;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteSaidaDto {
	private Long id;
	private String nome;
	private String cpf;
	private BigDecimal saldo;
	private BigDecimal renda;
	private String numeroConta;
	private TipoConta tipoConta;
	private ClassificacaoCliente classificacao;
	
	private LocalDate dataNascimento;
	private LocalDate dataAberturaConta;
	private Banco banco;

	
	

}
