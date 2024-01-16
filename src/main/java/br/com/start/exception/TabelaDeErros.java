package br.com.start.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TabelaDeErros {
	
	VALIDACAO (HttpStatus.BAD_REQUEST ,"1000 - 1000", "DADOS DE REQUISIÇÃO INVÁLIDOS, TENTE NOVAMENTE"),
	
	CLIENTE_NAO_ENCONTRADO (HttpStatus.NOT_FOUND,"1000 - 3001", "INFORMAÇÕES INVÁLIDAS, CLIENTE NÃO ENCONTRADO"),
	BANCO_NAO_ENCONTRADO (HttpStatus.NOT_FOUND,"1000 - 3001", "INFORMAÇÕES INVÁLIDAS, BANCO NÃO ENCONTRADO"),
	
	BANCO_CINCULADO_A_CLIENTE(HttpStatus.CONFLICT, "1000 - 2002", "BANCO VINCULADO A UM CLIENTE"),
	BANCO_JA_CADASTRADO(HttpStatus.CONFLICT, "1000 - 2001", "O BANCO JÁ CONSTA NA BASE DE DADOS"),
	CPF_JA_CADASTRADO (HttpStatus.CONFLICT ,"1000 - 4009", "O CPF JÁ CONSTA NA BASE DE DADOS"),
	
	SISTEMA(HttpStatus.INTERNAL_SERVER_ERROR,"1000 - 5000", "SISTEMA INDISPONÍVEL, TENTE MAIS TARDE");

	private final HttpStatus codigoHttp;
	private final String codigoErro;
	private final String mensagem;
	

}
