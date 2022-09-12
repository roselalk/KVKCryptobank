package com.example.kamervankrypto.config;

import com.example.kamervankrypto.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //Response status exception:
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDTO> generateException(ResponseStatusException re) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setTimestamp(new Date().toString());
        errorDTO.setStatus(String.valueOf( re.getStatus().value()));
        errorDTO.setErrorMessage(re.getMessage());
        log.error("Exception occurred: ", re);
        return new ResponseEntity<>(errorDTO, re.getStatus());
    }

    //Bad credentials exception:
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDTO> generateException(BadCredentialsException re) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setTimestamp(new Date().toString());
        errorDTO.setStatus("401");
        errorDTO.setErrorMessage(re.getMessage());
        log.error("Exception occurred: ", re);
        return new ResponseEntity<>(errorDTO, HttpStatus.UNAUTHORIZED);
    }
}
