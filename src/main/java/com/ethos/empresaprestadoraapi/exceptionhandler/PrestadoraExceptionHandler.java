package com.ethos.empresaprestadoraapi.exceptionhandler;

import com.ethos.empresaprestadoraapi.exception.EmpresaNaoEncontradaException;
import com.ethos.empresaprestadoraapi.exception.PrestadoraJaCadastradaException;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PrestadoraExceptionHandler {
    private static final String instancia = "/v1.0/prestadoras";

    @ExceptionHandler(PrestadoraJaCadastradaException.class)
    public ProblemDetail handlePrestadoraJaCadastrada(PrestadoraJaCadastradaException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Prestadora já cadastrada");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setInstance(URI.create(instancia));
        problemDetail.setStatus(HttpStatus.CONFLICT);
        return problemDetail;
    }

    @ExceptionHandler(EmpresaNaoEncontradaException.class)
    public ProblemDetail handleEmpresaNaoEncontrada(EmpresaNaoEncontradaException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Empresa não encontrada");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setInstance(URI.create(instancia));
        problemDetail.setStatus(HttpStatus.NOT_FOUND);
        return problemDetail;
    }
}
