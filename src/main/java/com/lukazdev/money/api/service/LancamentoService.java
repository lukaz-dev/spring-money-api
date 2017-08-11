package com.lukazdev.money.api.service;

import com.lukazdev.money.api.model.Lancamento;
import com.lukazdev.money.api.model.Pessoa;
import com.lukazdev.money.api.repository.LancamentoRepository;
import com.lukazdev.money.api.repository.PessoaRepository;
import com.lukazdev.money.api.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Lancamento salvar(Lancamento lancamento) {
        Long codigoPessoa = Optional.ofNullable(lancamento.getPessoa().getCodigo())
                .orElseThrow(() -> new PessoaInexistenteOuInativaException());

        Optional<Pessoa> pessoaOptional = Optional.ofNullable(pessoaRepository.findOne(codigoPessoa));
        if (!pessoaOptional.isPresent() || pessoaOptional.get().isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }

        return lancamentoRepository.save(lancamento);
    }
}
