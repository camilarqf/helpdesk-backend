package com.projeto.helpdesk.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
public abstract class Pessoa implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String nome;

    @Column(unique = true)
    protected String cpf;
    @Column(unique = true)
    protected String email;

    protected String senha;
    @ElementCollection(fetch = FetchType.EAGER) //carregamento imediato e o Lazy sob demanda
    @CollectionTable(name = "Perfis")
    protected Set<Perfil> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public Pessoa() {
        super();
        setPerfis(Collections.singleton(Perfil.CLIENTE));
    }

    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        setPerfis(Collections.singleton(Perfil.CLIENTE));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) && Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }
}
