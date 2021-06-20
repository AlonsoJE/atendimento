package teste.pratico.atendimento.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiErrorModel {

    private int status;
    private LocalDateTime dateTime;
    private String exception;
    private String message;

}
