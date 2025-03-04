package itu.jca.eval.test.coworking.dto;

import java.util.List;

import lombok.Data;

@Data
public class ImportResponse {
    private String message;
    private List<String> errors;

    public ImportResponse(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

} 
