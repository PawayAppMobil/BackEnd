package com.paway.spring.data.kmoneta.reports.service;

public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException(String message) {
        super(message); // Llama al constructor de la clase base con el mensaje
    }
}
