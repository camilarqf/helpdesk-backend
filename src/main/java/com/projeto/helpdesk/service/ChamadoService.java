package com.projeto.helpdesk.service;

import com.projeto.helpdesk.dto.ChamadoDTO;
import com.projeto.helpdesk.modelo.Chamado;
import com.projeto.helpdesk.modelo.Cliente;
import com.projeto.helpdesk.modelo.Tecnico;
import com.projeto.helpdesk.modelo.enums.Prioridade;
import com.projeto.helpdesk.modelo.enums.Status;
import com.projeto.helpdesk.repository.ChamadoRepository;
import com.projeto.helpdesk.repository.ClienteRepository;
import com.projeto.helpdesk.repository.TecnicoRepository;
import com.projeto.helpdesk.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id){
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<Chamado> findAll() {
        List<Chamado> chamados = chamadoRepository.findAll();
        return chamados;
    }

    public Chamado create(@Valid ChamadoDTO chamadoDTO) {
        return chamadoRepository.save(newChamado(chamadoDTO));
    }

    private Chamado newChamado(ChamadoDTO chamadoDTO){
        Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico());
        Cliente cliente = clienteService.findById(chamadoDTO.getCliente());

        Chamado chamado = new Chamado();

        if(chamadoDTO.getId() != null){ //ou seja, se for diferente de nulo quer atualizar e não criar um novo
            chamado.setId(chamadoDTO.getId());
        }

        if(chamadoDTO.getStatus().equals(2)){
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setCliente(cliente);
        chamado.setObservacoes(chamadoDTO.getObservacoes());
        chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
        chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
        chamado.setTecnico(tecnico);
        chamado.setTitulo(chamadoDTO.getTitulo());
        return chamado;
    }

    public Chamado update(@Valid Integer id, ChamadoDTO chamadoDTO) {
        chamadoDTO.setId(id);
        Chamado oldChamado = findById(id);
        oldChamado = newChamado(chamadoDTO);
        return chamadoRepository.save(oldChamado);
    }
}
