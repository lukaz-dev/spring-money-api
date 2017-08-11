package com.lukazdev.money.api.repository.lancamento;

import com.lukazdev.money.api.model.Lancamento;
import com.lukazdev.money.api.repository.filter.LancamentoFilter;
import com.lukazdev.money.api.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {

    Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable);

    Page<ResumoLancamento> resumir(LancamentoFilter filter, Pageable pageable);
}
