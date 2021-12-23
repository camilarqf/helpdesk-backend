package com.projeto.helpdesk.repository;

import com.projeto.helpdesk.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}


