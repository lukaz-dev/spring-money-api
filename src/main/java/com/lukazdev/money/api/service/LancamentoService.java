package com.lukazdev.money.api.service;

import com.lukazdev.money.api.model.Lancamento;
import com.lukazdev.money.api.model.Pessoa;
import com.lukazdev.money.api.repository.LancamentoRepository;
import com.lukazdev.money.api.repository.PessoaRepository;
import com.lukazdev.money.api.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Lancamento salvar(Lancamento lancamento) {
        validarPessoa(lancamento);
        return lancamentoRepository.save(lancamento);
    }

    public Lancamento atualizar(Long codigo, Lancamento lancamento) {
        Lancamento lancamentoSalvo = buscarLancamentoExistente(codigo);
        if (!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())) {
            validarPessoa(lancamento);
        }
        BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");
        return lancamentoRepository.save(lancamentoSalvo);
    }

    private void validarPessoa(Lancamento lancamento) {
        Pessoa pessoa = null;
        if (lancamento.getPessoa().getCodigo() != null) {
            pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
        }

        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }
    }

    private Lancamento buscarLancamentoExistente(Long codigo) {
        Optional<Lancamento> lancamentoOptional = Optional.ofNullable(lancamentoRepository.findOne(codigo));
        Lancamento lancamentoSalvo = lancamentoOptional.orElseThrow(() -> new EmptyResultDataAccessException(1));
        return lancamentoSalvo;
    }
}
