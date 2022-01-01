package com.projeto.helpdesk.service;

import com.projeto.helpdesk.modelo.Chamado;
import com.projeto.helpdesk.repository.ChamadoRepository;
import com.projeto.helpdesk.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id){
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }
}
