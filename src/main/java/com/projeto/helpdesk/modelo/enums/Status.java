package com.projeto.helpdesk.modelo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    ABERTO(0, "ABERTO"),
    ANDAMENTO(1, "ANDAMENTO"),
    ENCERRADO(2, "ENCERRADO");

    private Integer codigo;
    private String descricao;

    public static Status toEnum(Integer codigo){
        if(codigo == null){
            return null;
        }
        for(Status x: Status.values()){
            if(codigo.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Status inválido");
    }
}
