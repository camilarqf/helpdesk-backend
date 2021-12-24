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
        Tecnico tec2 = new Tecnico(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", ("1234"));
        Tecnico tec3 = new Tecnico(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com", ("1234"));
        Tecnico tec4 = new Tecnico(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", ("1234"));
        Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", ("1234"));

        Cliente cliente = new Cliente(null, "cliente", "63056592062", "cliente@email.com", "1234");
        Cliente cli2 = new Cliente(null, "Marie Curie", "322.429.140-06", "curie@mail.com", ("1234"));
        Cliente cli3 = new Cliente(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com", ("1234"));
        Cliente cli4 = new Cliente(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com", ("1234"));
        Cliente cli5 = new Cliente(null, "Max Planck", "081.399.300-83", "planck@mail.com", ("1234"));

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado",
                admin, cliente);
        Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 2", "Teste chamado 2", admin, cli2);
        Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 3", "Teste chamado 3", tec2, cli3);
        Chamado c4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 4", "Teste chamado 4", tec3, cli3);
        Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 5", "Teste chamado 5", tec2, cliente);
        Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 7", "Teste chamado 6", admin, cli5);

        tecnicoRepository.saveAll(Arrays.asList(admin,tec2, tec3, tec4, tec5));
        clienteRepository.saveAll(Arrays.asList(cliente,cli2, cli3, cli4, cli5));
        chamadoRepository.saveAll(Arrays.asList(c1,c2, c3, c4, c5, c6));
    }
}
