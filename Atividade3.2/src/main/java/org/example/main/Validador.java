package org.example.main;

import org.example.entities.Aviao;
import org.example.entities.Voo;

import java.util.regex.Pattern;

public class Validador {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$");

    public static boolean isEmailValido(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isCpfValido(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int soma = 0;
            // Primeiro dígito verificador
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }

            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito > 9) primeiroDigito = 0;

            if (Character.getNumericValue(cpf.charAt(9)) != primeiroDigito) {
                return false;
            }

            // Segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }

            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito > 9) segundoDigito = 0;

            return Character.getNumericValue(cpf.charAt(10)) == segundoDigito;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean ValidarCapacidadeVoo(int capacidade){
        if(capacidade > 100){
            return false;
        }
        return true;
    }
    //private static boolean validarVoo(int id){}

}