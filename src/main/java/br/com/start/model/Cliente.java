package br.com.start.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "cliente")

public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 200)
	private String nome;
	
	@Column(nullable = true,unique = true, length = 11)
	private String cpf;
	
	@Column(name = "saldo_cliente")
	private BigDecimal saldo;
	
	@Column(name = "renda_cliente")
	private BigDecimal renda;
	
	@Column(name = "numero_conta",nullable = false, unique = true, length = 5)
	private String numeroConta;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_conta", nullable = false, length = 30)
	private TipoConta tipoConta;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "classificacao_cliente", nullable = false, length = 30)
	private ClassificacaoCliente classificacao;
	
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	
	@Column(name = "data_abertura_conta")
	private LocalDate dataAberturaConta;
	
	@ManyToOne
	@JoinColumn(name = "id_banco")
	private Banco banco;
	

	

}

