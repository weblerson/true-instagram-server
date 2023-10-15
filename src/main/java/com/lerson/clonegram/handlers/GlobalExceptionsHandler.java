package com.lerson.clonegram.handlers;

import com.lerson.clonegram.dto.HandlerDTO;
import com.lerson.clonegram.exceptions.AllQueryParamsMissingException;
import com.lerson.clonegram.exceptions.DBException;
import com.lerson.clonegram.exceptions.StorageException;
import com.lerson.clonegram.exceptions.IllegalDateFormat;
import com.lerson.clonegram.util.TimeUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {


    @ExceptionHandler({ StorageException.class, DBException.class })
    public ResponseEntity<HandlerDTO> handleStorageException() {

        return new ResponseEntity<>(
                new HandlerDTO("Erro interno do sistema.", TimeUtil.getCurrentTimeMillis()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler({ AllQueryParamsMissingException.class })
    public ResponseEntity<HandlerDTO> handleAllQueryParamsMissingException() {

        return new ResponseEntity<>(
                new HandlerDTO("Envie ao menos um parâmetro de requisição", TimeUtil.getCurrentTimeMillis()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({ IllegalDateFormat.class })
    public ResponseEntity<HandlerDTO> handleIllegalDateFormatException() {

        return new ResponseEntity<>(
                new HandlerDTO("Envie um formato de data válido.", TimeUtil.getCurrentTimeMillis()),
                HttpStatus.BAD_REQUEST
        );
    }
}
