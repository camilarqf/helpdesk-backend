package com.projeto.helpdesk.service;

import com.projeto.helpdesk.dto.ClienteDTO;
import com.projeto.helpdesk.modelo.Cliente;
import com.projeto.helpdesk.modelo.Pessoa;
import com.projeto.helpdesk.repository.ClienteRepository;
import com.projeto.helpdesk.repository.PessoaRepository;
import com.projeto.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.projeto.helpdesk.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente findById(Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID:" + id));
    }

    public List<Cliente> findAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    public Cliente create(@Valid ClienteDTO clienteDTO){
        validaPorCpfeEmail(clienteDTO);
        clienteDTO.setSenha(encoder.encode(clienteDTO.getSenha()));
        Cliente cliente = new Cliente(clienteDTO);
        return clienteRepository.save(cliente);
    }

    private void validaPorCpfeEmail(ClienteDTO clienteDTO) {
        Optional<Pessoa> pessoaCpf = pessoaRepository.findByCpf(clienteDTO.getCpf());
        Optional<Pessoa> pessoaEmail = pessoaRepository.findByEmail(clienteDTO.getEmail());

        if (pessoaCpf.isPresent() && pessoaCpf.get().getId() != clienteDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

        if(pessoaEmail.isPresent() && pessoaEmail.get().getId() != clienteDTO.getId()){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
        }
    }

    public Cliente update(@Valid Integer id, ClienteDTO clienteDTO){
        clienteDTO.setId(id);
        Cliente oldCliente = findById(id);

        validaPorCpfeEmail(clienteDTO);
        Cliente cliente = new Cliente(clienteDTO);
        return clienteRepository.save(cliente);
    }

    public void delete(Integer id){
        Cliente cliente = findById(id);
        if(cliente.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
        }
        clienteRepository.deleteById(id);
    }

}
