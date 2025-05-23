package org.example.entities;

import org.example.main.Validador;

import java.util.regex.Pattern;

public class Passageiro {
    private int id;
    private String nome;
    private String cpf;
    private String email;

    public Passageiro(int id, String nome, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        setCpf(cpf);
        setEmail(email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (!Validador.isCpfValido(cpf)) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!Validador.isEmailValido(email)) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        this.email = email;
    }
}