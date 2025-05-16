package org.example.util;

import org.example.entities.Person;

public class Validator {
    Person person;

    public boolean validarCPF(String cpf) {
        if (cpf == null) {
            return false;
        } else {

            // Remove caracteres não numéricos
            cpf = cpf.replaceAll("\\D", "");

            // Verifica se tem 11 dígitos e se não é tudo igual (tipo 00000000000)
            if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {

                return false;
            }

            try {
                int soma1 = 0, soma2 = 0;
                for (int i = 0; i < 9; i++) {
                    int num = Character.getNumericValue(cpf.charAt(i));
                    soma1 += num * (10 - i);
                    soma2 += num * (11 - i);
                }

                int dig1 = (soma1 * 10) % 11;
                dig1 = (dig1 == 10) ? 0 : dig1;

                soma2 += dig1 * 2;
                int dig2 = (soma2 * 10) % 11;
                dig2 = (dig2 == 10) ? 0 : dig2;

                return dig1 == Character.getNumericValue(cpf.charAt(9)) &&
                        dig2 == Character.getNumericValue(cpf.charAt(10));

            } catch (Exception e) {
                return false;
            }
        }
    }

    public boolean validarEmail(String email) {
        if (person.getEmail() == null || person.getEmail() == "") return false;
        // Regex básico para validar formato de e-mail comum
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return person.getEmail().matches(regex);
    }

}
