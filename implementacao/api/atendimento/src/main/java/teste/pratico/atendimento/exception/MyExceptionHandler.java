package teste.pratico.atendimento.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Log4j2
public class MyExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> generic(RuntimeException e){
        log.error(e);
        return new ResponseEntity(
                ApiErrorModel.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .dateTime(LocalDateTime.now())
                .exception(e.getClass().getName())
                .message(e.getMessage())
                .build(),
                HttpStatus.BAD_REQUEST);
    }

}
