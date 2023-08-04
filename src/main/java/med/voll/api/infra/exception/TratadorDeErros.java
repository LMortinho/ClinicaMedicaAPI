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
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new));
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity EmailUsado(SQLIntegrityConstraintViolationException ex){
        String mensagemErro = ex.getMessage();
        String campo = "";

        if (mensagemErro.contains("'") && mensagemErro.contains("for key")) {
            int startIndex = mensagemErro.indexOf("'") + 1;
            int endIndex = mensagemErro.lastIndexOf("for key") - 2;
            campo = mensagemErro.substring(startIndex, endIndex);
        }
        String erroDetalhado = String.format("O campo '%s' já está sendo utilizado!", campo);

        return ResponseEntity.badRequest().body(erroDetalhado);
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity ValidacaoExceptionerror(ValidacaoException ex) {

        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
