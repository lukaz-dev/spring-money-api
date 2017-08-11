package com.lukazdev.money.api.repository;

import com.lukazdev.money.api.model.Lancamento;
import com.lukazdev.money.api.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
}
