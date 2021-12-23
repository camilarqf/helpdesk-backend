package com.projeto.helpdesk.service;

import com.projeto.helpdesk.modelo.*;
import com.projeto.helpdesk.repository.ChamadoRepository;
import com.projeto.helpdesk.repository.ClienteRepository;
import com.projeto.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB(){
        Tecnico admin = new Tecnico(null, "admin", "93612909088", "admin@email.com", "1234");
        admin.setPerfis(Collections.singleton(Perfil.ADMIN));

        Cliente cliente = new Cliente(null, "cliente", "63056592062", "cliente@email.com", "1234");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado",
                admin, cliente);

        tecnicoRepository.saveAll(Arrays.asList(admin));
        clienteRepository.saveAll(Arrays.asList(cliente));
        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
