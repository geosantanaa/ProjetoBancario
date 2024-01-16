package br.com.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.start.model.Banco;

public interface BancoRepository extends JpaRepository<Banco, Long> {


}
