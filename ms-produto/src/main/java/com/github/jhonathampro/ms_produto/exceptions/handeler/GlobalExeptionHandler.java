package com.github.jhonathampro.ms_produto.exceptions.handeler;

import com.github.jhonathampro.ms_produto.exceptions.ResourceNotFoundExeption;
import com.github.jhonathampro.ms_produto.exceptions.dto.CustomErrorDTO;
import com.github.jhonathampro.ms_produto.exceptions.dto.ValidationErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler(ResourceNotFoundExeption.class)
    public ResponseEntity<CustomErrorDTO> handleResourceNotFound(ResourceNotFoundExeption e,
                                                                 HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDTO errorDTO = new CustomErrorDTO(Instant.now(), status.value(),
               e.getMessage(), request.getRequestURI() );

        return ResponseEntity.status(status).body(errorDTO);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorDTO> methodArgumentNotValid(MethodArgumentNotValidException e,
                                                                 HttpServletRequest request){

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorDTO err = new ValidationErrorDTO(Instant.now(), status.value(),
                "Dados inválidos", request.getRequestURI());

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()){
            err.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);
    }

    // 400 - JSON malformado / corpo inválido (não dá para desserializar)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomErrorDTO> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
                                                                       HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST; //400
        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(),
                "Requisição inválida (JSON malformado ou corpo não interpretável).",
                request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    // 400 - tipo inválido em PathVariable/RequestParam (ex.: /produtos/abc quando espera Long)

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CustomErrorDTO> handleTypeMismatch(MethodArgumentTypeMismatchException e,
                                                             HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        CustomErrorDTO err = new CustomErrorDTO(
                Instant.now(), status.value(), "Requisição inválida (parâmetro com tipo/formato incorreto).",
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    // 500 - fallback para qualquer erro não tratado
    @ExceptionHandler(Exception.class)

    public ResponseEntity<CustomErrorDTO> handleGenericExcepetion(Exception e,
                                                                  HttpServletRequest request){
        HttpStatus status =  HttpStatus.INTERNAL_SERVER_ERROR;
        CustomErrorDTO err = new CustomErrorDTO(
                Instant.now(), status.value(),
                "erro interno inseperado.",request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    };





}
