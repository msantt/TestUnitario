package org.example.entities;

import org.example.main.Validador;
import org.example.repositories.BD;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PassageiroTest {

    @Test
    public void CpfValido() {
        Assertions.assertTrue(Validador.isCpfValido("52998224725"));
    }

    @Test
    public void CpfInvalido() {
        Assertions.assertFalse(Validador.isCpfValido("12345678900"));
    }

    @Test
    public void EmailValido() {
        Assertions.assertTrue(Validador.isEmailValido("ana.souza@email.com"));
    }

    @Test
    public void EmailInvalido() {
        Assertions.assertFalse(Validador.isEmailValido("ana.souza@com"));
    }

    @Test
    public void CadastroPassageiroValido() {
        Passageiro p = new Passageiro(1, "Ana Souza", "52998224725", "ana.souza@email.com");
        BD.passageiros.add(p);
        Assertions.assertEquals(1, BD.passageiros.size());
    }

    @Test
    public void CadastroPassageiroCpfInvalido() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Passageiro p = new Passageiro(1, "Ana Souza", "12345678900", "joao@email.com");
        });
    }
}