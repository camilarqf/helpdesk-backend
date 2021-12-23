package com.projeto.helpdesk.repository;

import com.projeto.helpdesk.modelo.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
