package com.projeto.helpdesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.helpdesk.modelo.Cliente;
import com.projeto.helpdesk.modelo.enums.Perfil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = -6646816812547167698L;

    protected Integer id;

    @NotNull(message = "O campo NOME é obrigatório")
    protected String nome;

    @NotNull(message = "O campo CPF é obrigatório")
    @Column(unique = true)
    protected String cpf;

    @NotNull(message = "O campo EMAIL é obrigatório")
    @Column(unique = true)
    protected String email;

    @NotNull(message = "O campo SENHA é obrigatório")
    protected String senha;

    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public ClienteDTO() {

    }

    public ClienteDTO(Cliente cliente) {
        super();
        this.id = cliente.getId();
        this.cpf = cliente.getCpf();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.senha = cliente.getSenha();
        this.perfis = cliente.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = cliente.getDataCriacao();
        addPerfil(Perfil.CLIENTE);
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }
}
