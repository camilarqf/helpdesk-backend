package com.projeto.helpdesk.repository;

import com.projeto.helpdesk.modelo.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
