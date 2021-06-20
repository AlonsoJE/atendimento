package teste.pratico.atendimento.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class GeradorProtocolo {

    public static String proximoProtocolo(){
        return
        String.valueOf(LocalDate.now().getMonthValue())
                .concat(String.valueOf(LocalDate.now().getYear()))
                .concat(String.valueOf(LocalDate.now().getDayOfMonth()))
                .concat(String.valueOf(LocalDateTime.now().getSecond()))
                .concat(UUID.randomUUID().toString().substring(0,3)).toUpperCase();
    }


}
