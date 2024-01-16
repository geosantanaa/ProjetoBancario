package br.com.start.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.start.service.ClienteRelatorioService;

@RestController
@RequestMapping("cliente/relatorio")
@Validated
public class ClienteRelatorioController {

	@Autowired
	private ClienteRelatorioService service;

	@GetMapping(value = "quantidade/TipoConta", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] quantidadePorTipoContaImagem() throws IOException {
		return service.quantidadePorTipoContaImagem();

	}
	
	@GetMapping(value = "quantidade/ClassificacaoCliente", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] quantidadePorClassificacaoClienteImagem() throws IOException {
		return service.quantidadePorClassificacaoClienteImagem();

	}
}
