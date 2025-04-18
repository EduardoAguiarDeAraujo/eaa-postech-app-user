package br.eng.eaa.app_user.controller.handler;

import br.eng.eaa.app_user.dto.DataIntegrityViolationDTO;
import br.eng.eaa.app_user.dto.ResourceNotFoundDTO;
import br.eng.eaa.app_user.dto.UnauthorizedDTO;
import br.eng.eaa.app_user.dto.ValidationErrorDTO;
import br.eng.eaa.app_user.exception.ResourceNotFoundException;
import br.eng.eaa.app_user.exception.UnauthorizedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
        var status = HttpStatus.NOT_FOUND;
        var resourceNotFoundDTO = new ResourceNotFoundDTO(ex.getMessage(), status.value());
        return ResponseEntity.status(status).body(resourceNotFoundDTO);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<UnauthorizedDTO> handleUnauthorizedException(UnauthorizedException ex) {
        var status = HttpStatus.UNAUTHORIZED;
        var unauthorizedDTO = new UnauthorizedDTO(ex.getMessage(), status.value());
        return ResponseEntity.status(status).body(unauthorizedDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handleResourceNotFoundException(MethodArgumentNotValidException ex) {
        var status = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<String>();
        ex.getFieldErrors().forEach(error -> {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        });
        var resourceNotFoundDTO = new ResourceNotFoundDTO(ex.getMessage(), status.value());
        return ResponseEntity.status(status).body(new ValidationErrorDTO(errors, status.value()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DataIntegrityViolationDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        var status = HttpStatus.CONFLICT;
        var dataIntegrityViolationDTO = new DataIntegrityViolationDTO("Email ou login já cadastrados", status.value());
        return ResponseEntity.status(status).body(dataIntegrityViolationDTO);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DataIntegrityViolationDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        var status = HttpStatus.BAD_REQUEST;
        var dataIntegrityViolationDTO = new DataIntegrityViolationDTO("Nome de perfil incorreto", status.value());
        return ResponseEntity.status(status).body(dataIntegrityViolationDTO);
    }
}