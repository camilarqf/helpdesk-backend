package com.projeto.helpdesk.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable {
    private static final long serialVersionUID = 772659055280620594L;
    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
