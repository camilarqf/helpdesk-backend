package com.projeto.helpdesk.repository;

import com.projeto.helpdesk.modelo.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
