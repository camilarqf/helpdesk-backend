package com.projeto.helpdesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.helpdesk.modelo.Chamado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ChamadoDTO implements Serializable {

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    private Integer prioridade;
    private Integer status;
    private String titulo;
    private String observacoes;
    private Integer tecnico;
    private Integer cliente;
    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO(Chamado chamado) {
        this.id = chamado.getId();
        this.dataAbertura = chamado.getDataAbertura();
        this.dataFechamento = chamado.getDataFechamento();
        this.prioridade = chamado.getPrioridade().getCodigo();
        this.status = chamado.getStatus().getCodigo();
        this.titulo = chamado.getTitulo();
        this.observacoes = chamado.getObservacoes();
        this.tecnico = chamado.getTecnico().getId();
        this.cliente = chamado.getCliente().getId();
        this.nomeTecnico = chamado.getTecnico().getNome();
        this.nomeCliente = chamado.getCliente().getNome();
    }
}