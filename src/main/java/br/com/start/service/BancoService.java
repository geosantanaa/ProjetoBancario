package br.com.start.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.start.exception.NegocioException;
import br.com.start.exception.TabelaDeErros;
import br.com.start.model.Banco;
import br.com.start.model.dto.BancoEntradaDto;
import br.com.start.model.dto.BancoSaidaDto;
import br.com.start.repository.BancoRepository;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BancoService {
	
	@Autowired
	private BancoRepository repository;
		
	@Autowired
	private ModelMapper mapper;

	public BancoSaidaDto criar(BancoEntradaDto entrada) {
		try {
			Banco banco = mapper.map(entrada, Banco.class);
			log.info("Processando uma requisição: metodo = criar, entity = {}", banco);

			Banco entityBanco = repository.save(banco);
			log.info("O banco de dados retornou: entityBanco = {}", entityBanco);
			return mapper.map(entityBanco, BancoSaidaDto.class);

		}catch(DataIntegrityViolationException e) {
				throw new NegocioException(TabelaDeErros.BANCO_JA_CADASTRADO);
			}
		}

	public BancoSaidaDto pegarUm(Long id) {
		try {
			Optional<Banco> buscandoBanco = repository.findById(id);
			Banco entityBanco = buscandoBanco.get();
			log.info("O Banco de Dados retornou: entityBanco={}", entityBanco);
			return mapper.map(entityBanco, BancoSaidaDto.class);

		}catch(NoSuchElementException e) {
			throw new NegocioException(TabelaDeErros.BANCO_NAO_ENCONTRADO);
		}
	
	}

	public void excluir(Long id) {	
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e){
			throw new NegocioException(TabelaDeErros.BANCO_NAO_ENCONTRADO);
		}catch(DataIntegrityViolationException e) {
			throw new NegocioException(TabelaDeErros.BANCO_CINCULADO_A_CLIENTE);
		}
		
	}

	public List<BancoSaidaDto> listar() {
		List<Banco> listaBanco = repository.findAll();
		return mapper.map(listaBanco, new TypeToken<List<BancoSaidaDto>>() {}.getType());
	}

}
