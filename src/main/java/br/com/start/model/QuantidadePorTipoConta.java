package br.com.start.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuantidadePorTipoConta {
	private TipoConta tipoConta;
	private Long quantidade;
	
	public QuantidadePorTipoConta(TipoConta tipoConta, Long quantidade) {
		this.tipoConta = tipoConta;
		this.quantidade = quantidade;
	}
	

}
