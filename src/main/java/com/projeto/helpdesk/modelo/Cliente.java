package com.projeto.helpdesk.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.helpdesk.dto.ClienteDTO;
import com.projeto.helpdesk.modelo.enums.Perfil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class Cliente extends Pessoa {

    private static final Long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        super();
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
    }


    public Cliente(ClienteDTO clienteDTO) {
        this.cpf = clienteDTO.getCpf();
        this.email = clienteDTO.getEmail();
        this.id = clienteDTO.getId();
        this.nome = clienteDTO.getNome();
        this.perfis = clienteDTO.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.senha = clienteDTO.getSenha();
        this.dataCriacao = clienteDTO.getDataCriacao();
    }
}
