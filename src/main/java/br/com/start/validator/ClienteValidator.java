package br.com.start.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.start.exception.NegocioException;
import br.com.start.exception.TabelaDeErros;
import br.com.start.model.dto.ClienteEntradaDto;
import br.com.start.repository.ClienteRepository;

@Component
public class ClienteValidator {

	@Autowired
	private ClienteRepository repository;

	public void validarIdCliente(Long id) {
		if (!repository.existsById(id)) {
			throw new NegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO);
		}
	}


	public void validarCpf(ClienteEntradaDto entrada) {
		if (repository.existsByCpf(entrada.getCpf())) {
			throw new NegocioException(TabelaDeErros.CPF_JA_CADASTRADO);
		}
	}

}
