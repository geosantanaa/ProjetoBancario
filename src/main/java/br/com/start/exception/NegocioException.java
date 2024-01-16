package br.com.start.exception;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class NegocioException extends RuntimeException{

private static final long serialVersionUID = 1L;
	
	private final String codigoErro;
	private final HttpStatus codigoHttp;
	private final String mensagem;
	
	public NegocioException(TabelaDeErros tabela) {
		this.codigoErro = tabela.getCodigoErro();
		this.codigoHttp = tabela.getCodigoHttp();
		this.mensagem = tabela.getMensagem();
	}

}
