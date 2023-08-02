package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity erro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity erro400EmailDuplicado(SQLIntegrityConstraintViolationException exception) {
        var ex = exception.getLocalizedMessage();
        if (ex.contains("Duplicate entry")) {
            return ResponseEntity.badRequest().body("Email já está em uso!");
        }
        return ResponseEntity.badRequest().body(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity erro400(MethodArgumentNotValidException exception) {
        var ex = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(ex.stream().map(DadosDetalhamentoErro::new).toList());
    }

    public record DadosDetalhamentoErro(String field, String defaultMessage) {
        public DadosDetalhamentoErro(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
