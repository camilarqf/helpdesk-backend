package com.projeto.helpdesk.service;

import com.projeto.helpdesk.dto.TecnicoDTO;
import com.projeto.helpdesk.service.exceptions.ObjectNotFoundException;
import com.projeto.helpdesk.modelo.Tecnico;
import com.projeto.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id){
       Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
       return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID:" + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO tecnicoDTO){
        Tecnico tecnico = new Tecnico(tecnicoDTO);
        return  tecnicoRepository.save(tecnico);
    }

}
