package org.example.entities;

import java.time.LocalDateTime;

public class Reserva {
    private int id;
    private Passageiro passageiro;
    private Voo voo;
    private LocalDateTime dataReserva;

    public Reserva(int id, Passageiro passageiro, Voo voo, LocalDateTime dataReserva) {
        if (voo.getCapacidadeDisponivel() <= 0) {
            throw new IllegalArgumentException("Não há vagas disponíveis neste voo.");
        }
        this.id = id;
        this.passageiro = passageiro;
        this.voo = voo;
        this.dataReserva = dataReserva;
        // Adiciona o passageiro à lista do voo
        voo.getPassageiros().add(passageiro);
    }

    public int getId() {
        return id;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public Voo getVoo() {
        return voo;
    }

    public LocalDateTime getDataReserva() {
        return dataReserva;
    }
}