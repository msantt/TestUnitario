package org.example.entities;

import org.example.main.Validador;

public class Passageiro {
    private int id;
    private String nome;
    private String cpf;
    private String email;

    public Passageiro(int id, String nome, String cpf, String email) throws IllegalAccessException {
        if (!Validador.isCpfValido(cpf)){
            throw new IllegalAccessException("Esse CPF é inválido");
        }

        if (!Validador.isEmailValido(email)) {
            throw new IllegalArgumentException("E-mail inválido");
        }

        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
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
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
