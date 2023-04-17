package com.dev2023.services.exception;
import com.dev2023.resources.exceptions.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ErroGeral.class)
    public ResponseEntity<StandarError> entityNotFound(ErroGeral e, HttpServletRequest request) {
        StandarError standarError = new StandarError();
        standarError.setTimestamp(Instant.now());
        standarError.setStatus(HttpStatus.NOT_FOUND.value());
        standarError.setMessage("Resource not Found");
        standarError.setError("not Found");
        standarError.setMessage(e.getMessage());
        standarError.setPath(request.getRequestURI());
        return ResponseEntity.status(standarError.getStatus()).body(standarError);
    }
    @ExceptionHandler(DatabaseExption.class)
    public ResponseEntity<StandarError> entityNotFound(DatabaseExption e, HttpServletRequest request) {
        StandarError standarError = new StandarError();
        standarError.setTimestamp(Instant.now());
        standarError.setStatus(HttpStatus.BAD_REQUEST.value());
        standarError.setMessage("Resource not Found");
        standarError.setError("Data base Exeption");
        standarError.setMessage(e.getMessage());
        standarError.setPath(request.getRequestURI());
        return ResponseEntity.status(standarError.getStatus()).body(standarError);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError err = new ValidationError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        err.setError("Validation Exeception");
        //  err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.AddError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(err.getStatus()).body(err);
    }
}
