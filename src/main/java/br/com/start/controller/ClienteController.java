package br.com.start.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.start.model.ClassificacaoCliente;
import br.com.start.model.QuantidadePorClassificacaoCliente;
import br.com.start.model.QuantidadePorTipoConta;
import br.com.start.model.TipoConta;
import br.com.start.model.dto.ClienteAlterarDto;
import br.com.start.model.dto.ClienteEntradaDto;
import br.com.start.model.dto.ClienteSaidaDto;
import br.com.start.repository.ClienteRepository;
import br.com.start.service.ClienteService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("cliente")
@Log4j2
@Validated
public class ClienteController {

	@Autowired
	ClienteService service;
	
	@Autowired
	ClienteRepository repository; 

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public ClienteSaidaDto criar(@Valid @RequestBody ClienteEntradaDto entrada) {
		log.info("Recebendo uma requisição: metodo = criar, entradaDto{}", entrada);
		return service.criar(entrada);
	}

	@PutMapping("id/{id}")
	public void alterar( @PathVariable ("id") Long id, @Valid @RequestBody ClienteAlterarDto alterar) {
		log.info("alterar: {} {}", id, alterar);
		service.alterar(id, alterar);
	}

	@GetMapping("id/{id}")
	public ClienteSaidaDto pegarUm(@PathVariable ("id") Long id) {
		log.info("PegarUm: {}", id);
		return service.pegarUm(id);
	}
	
	@PatchMapping("id/{id}/renda/{renda}")
	public void alterarRenda(@PathVariable("id") Long id, @PathVariable("renda") BigDecimal renda) {
		log.info("alterar: {}", id);
		service.alterarRenda(id, renda);
	}

	@DeleteMapping ("id/{id}")
	public void excluir(@PathVariable ("id") Long id) {
		log.info("excluir: {}", id);
		service.excluir(id);
	}

	@GetMapping 
	public List<ClienteSaidaDto> listar() {
		log.info("listar");
		return service.listar();
	}
	
	@GetMapping("quantidade/tipoConta/{tipoConta}")
	public long quantidadeTipoConta(@PathVariable("tipoConta") TipoConta tipoConta) {
		log.info("quantidade: {}", tipoConta);
		
		return repository.countByTipoConta(tipoConta);
	}
	
	@GetMapping("quantidade/classificacao/{classificacao}")
	public long quantidadeClassificacao(@PathVariable("classificacao") ClassificacaoCliente classificacao) {
		log.info("quantidade: {}", classificacao);
		
		return repository.countByClassificacao(classificacao);
	}
	
	@GetMapping("relatorioTipoConta")
    public List<QuantidadePorTipoConta> gerarRelatorioTipoConta() {
        return service.gerarRelatorioPorTipoConta();
    }
	
	@GetMapping("relatorioClassificacao")
    public List<QuantidadePorClassificacaoCliente> gerarRelatorioClassificacao() {
        return service.gerarRelatorioPorClassificacaoCliente();
    }
	

}
