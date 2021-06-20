package teste.pratico.atendimento.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexCenter {

    private static Matcher matcher;

    public static final Pattern regexNome = Pattern.compile("([a-zA-Z\\s])*");

    public static void verifyName(String value){
        if(!match(regexNome, value)){
            throw new RuntimeException(String.format("'(%s)' Deve conter apenas letras", value));
        }
    }

    private static Boolean match(Pattern pattern, String value){
        matcher = pattern.matcher(value);
        return matcher.matches();
    }

}
