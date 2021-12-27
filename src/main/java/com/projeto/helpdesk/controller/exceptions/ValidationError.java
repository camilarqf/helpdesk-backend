package com.projeto.helpdesk.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ValidationError extends StandardError{
    private static final long serialVersionUID = 1945426382556445712L;
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public void addErrors(String fieldName, String message){
        this.errors.add(new FieldMessage(fieldName, message));
    }
}
