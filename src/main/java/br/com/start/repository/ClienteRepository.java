package br.com.start.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.start.model.ClassificacaoCliente;
import br.com.start.model.Cliente;
import br.com.start.model.QuantidadePorClassificacaoCliente;
import br.com.start.model.QuantidadePorTipoConta;
import br.com.start.model.TipoConta;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	boolean existsByCpf(String cpf);
	
	Long countByTipoConta(TipoConta tipoConta);
	
	long countByClassificacao(ClassificacaoCliente classificacao);

	
	@Query(value = "SELECT new br.com.start.model.QuantidadePorTipoConta(a.tipoConta, COUNT(a.tipoConta)) FROM cliente a GROUP BY (a.tipoConta)")
	List<QuantidadePorTipoConta> quantidadePorTipoConta();
	
	@Query(value = "SELECT new br.com.start.model.QuantidadePorClassificacaoCliente(a.classificacao, COUNT(a.classificacao)) FROM cliente a GROUP BY (a.classificacao)")
	List<QuantidadePorClassificacaoCliente> quantidadePorClassificacaoCliente();



	
}
