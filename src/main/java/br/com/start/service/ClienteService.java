package br.com.start.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.start.exception.NegocioException;
import br.com.start.exception.TabelaDeErros;
import br.com.start.model.Banco;
import br.com.start.model.Cliente;
import br.com.start.model.QuantidadePorClassificacaoCliente;
import br.com.start.model.QuantidadePorTipoConta;
import br.com.start.model.dto.ClienteAlterarDto;
import br.com.start.model.dto.ClienteEntradaDto;
import br.com.start.model.dto.ClienteSaidaDto;
import br.com.start.repository.BancoRepository;
import br.com.start.repository.ClienteRepository;
import br.com.start.validator.ClienteValidator;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BancoRepository bancoRepository;
	
	@Autowired
	private ClienteValidator validator;

	@Autowired
	private ModelMapper mapper;

	public ClienteSaidaDto criar(ClienteEntradaDto entrada) {
		validator.validarCpf(entrada);

		Cliente entity = mapper.map(entrada, Cliente.class);
		
		if (entrada.getDataAberturaConta() == null) {
			entity.setDataAberturaConta(LocalDate.now());
		}
		log.info("Processando uma requisição: metodo = criar, entity = {}", entity);
		
		Optional<Banco> buscandoBanco = bancoRepository.findById(entrada.getIdBanco());
	    if (buscandoBanco.isPresent()) {
	        Banco banco = buscandoBanco.get();
	        entity.setBanco(banco);
	    }

		Cliente entityBanco = clienteRepository.save(entity);
		log.info("O banco de dados retornou: entityBanco = {}", entityBanco);


		return mapper.map(entityBanco, ClienteSaidaDto.class);
	}

	public void alterar(Long id, ClienteAlterarDto alterar) {		
		Optional<Cliente> buscandoCliente = clienteRepository.findById(id);
		if(!buscandoCliente.isPresent()){
			throw new NegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO);
		}

		Cliente entityBanco = buscandoCliente.get();
		mapper.map(alterar, entityBanco);

		clienteRepository.save(entityBanco);
		
	}
	
	public void alterarRenda(Long id, BigDecimal renda) {		
		Optional<Cliente> buscandoCliente = clienteRepository.findById(id);
		if(!buscandoCliente.isPresent()){
			throw new NegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO);
		}

		Cliente entityBanco = buscandoCliente.get();
		entityBanco.setRenda(renda);
		clienteRepository.save(entityBanco);
		
	}


	public ClienteSaidaDto pegarUm(Long id) {
		Optional<Cliente> buscandoCliente = clienteRepository.findById(id);
		if(!buscandoCliente.isPresent()){
			throw new NegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO);
		}
		Cliente entityBanco = buscandoCliente.get();
		log.info("O Banco de Dados retornou: entityBanco={}", entityBanco);
		return mapper.map(entityBanco, ClienteSaidaDto.class);
	}

	public void excluir(Long id) {
		validator.validarIdCliente(id);
		clienteRepository.deleteById(id);
	}

	public List<ClienteSaidaDto> listar() {
		List<Cliente> listaClientes = clienteRepository.findAll();
		return mapper.map(listaClientes, new TypeToken<List<ClienteSaidaDto>>() {}.getType());
	}
	
	public List<QuantidadePorTipoConta> gerarRelatorioPorTipoConta(){
		return clienteRepository.quantidadePorTipoConta();
	}
	
	public List<QuantidadePorClassificacaoCliente> gerarRelatorioPorClassificacaoCliente(){
		return clienteRepository.quantidadePorClassificacaoCliente();
	}

}
