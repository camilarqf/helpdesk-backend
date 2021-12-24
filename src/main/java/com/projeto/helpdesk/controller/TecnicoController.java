package com.projeto.helpdesk.controller;

import com.projeto.helpdesk.dto.TecnicoDTO;
import com.projeto.helpdesk.modelo.Tecnico;
import com.projeto.helpdesk.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        Tecnico tecnico = tecnicoService.findById(id);
        return new ResponseEntity<>(new TecnicoDTO(tecnico), HttpStatus.OK);
    }
}
