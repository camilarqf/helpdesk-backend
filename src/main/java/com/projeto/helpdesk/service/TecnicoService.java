package com.projeto.helpdesk.service;

import com.projeto.helpdesk.dto.TecnicoDTO;
import com.projeto.helpdesk.modelo.Pessoa;
import com.projeto.helpdesk.repository.PessoaRepository;
import com.projeto.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.projeto.helpdesk.service.exceptions.ObjectNotFoundException;
import com.projeto.helpdesk.modelo.Tecnico;
import com.projeto.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Tecnico findById(Integer id){
       Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
       return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID:" + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(@Valid TecnicoDTO tecnicoDTO){
        validaPorCpfeEmail(tecnicoDTO);
        tecnicoDTO.setSenha(encoder.encode(tecnicoDTO.getSenha()));
        Tecnico tecnico = new Tecnico(tecnicoDTO);
        return  tecnicoRepository.save(tecnico);
    }

    private void validaPorCpfeEmail(TecnicoDTO tecnicoDTO) {
        Optional<Pessoa> pessoaCpf = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
        Optional<Pessoa> pessoaEmail = pessoaRepository.findByEmail(tecnicoDTO.getEmail());

        if (pessoaCpf.isPresent() && pessoaCpf.get().getId() != tecnicoDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

        if(pessoaEmail.isPresent() && pessoaEmail.get().getId() != tecnicoDTO.getId()){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
        }
    }

    public Tecnico update(@Valid Integer id, TecnicoDTO tecnicoDTO){
        tecnicoDTO.setId(id);
        Tecnico oldTecnico = findById(id);

        validaPorCpfeEmail(tecnicoDTO);
        Tecnico tecnico = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(tecnico);
    }

    public void delete(Integer id){
        Tecnico tecnico = findById(id);
        if(tecnico.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }
        tecnicoRepository.deleteById(id);
    }


}
