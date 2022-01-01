package com.projeto.helpdesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.helpdesk.modelo.Chamado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "O campo PRIORIDADE é obrigatório")
    private Integer prioridade;
    @NotNull(message = "O campo STATUS é obrigatório")
    private Integer status;
    @NotNull(message = "O campo TÍTULO é obrigatório")
    private String titulo;
    @NotNull(message = "O campo OBERVAÇÕES é obrigatório")
    private String observacoes;
    @NotNull(message = "O campo TÉCNICO é obrigatório")
    private Integer tecnico;
    @NotNull(message = "O campo CLIENTE é obrigatório")
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
