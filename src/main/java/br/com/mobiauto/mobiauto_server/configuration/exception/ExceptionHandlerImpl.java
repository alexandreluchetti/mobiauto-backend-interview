package br.com.mobiauto.mobiauto_server.configuration.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerImpl {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDto> handleIllegalArgumentException(IllegalArgumentException ex) {
        ExceptionDto exceptionEntity = new ExceptionDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionEntity);
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ExceptionDto> handleSecurityException(SecurityException ex) {
        ExceptionDto exceptionEntity = new ExceptionDto(HttpStatus.FORBIDDEN.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionEntity);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleEntityNotFoundException(EntityNotFoundException ex) {
        ExceptionDto exceptionEntity = new ExceptionDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionEntity);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionDto> handleUnauthorizedException(UnauthorizedException ex) {
        ExceptionDto exceptionEntity = new ExceptionDto(HttpStatus.FORBIDDEN.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionEntity);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ExceptionDto> handleInvalidDataException(InvalidDataException ex) {
        ExceptionDto exceptionEntity = new ExceptionDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionEntity);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ExceptionDto> handleDatabaseException(DatabaseException ex) {
        ExceptionDto exceptionEntity = new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionEntity);
    }

    @ExceptionHandler(NoneResultException.class)
    public ResponseEntity<ExceptionDto> handleNoneResultException(NoneResultException ex) {
        ExceptionDto exceptionEntity = new ExceptionDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionEntity);
    }

    @ExceptionHandler(OperationException.class)
    public ResponseEntity<ExceptionDto> handleOperationException(OperationException ex) {
        ExceptionDto exceptionEntity = new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionEntity);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleException(Exception ex) {
        ExceptionDto exceptionEntity = new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionEntity);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
