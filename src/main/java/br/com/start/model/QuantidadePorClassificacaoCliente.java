package br.com.start.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class QuantidadePorClassificacaoCliente {


	private ClassificacaoCliente classificacao;
	private Long quantidade;
	
	public QuantidadePorClassificacaoCliente(ClassificacaoCliente classificacao, Long quantidade) {
		this.classificacao = classificacao;
		this.quantidade = quantidade;
	}
}
