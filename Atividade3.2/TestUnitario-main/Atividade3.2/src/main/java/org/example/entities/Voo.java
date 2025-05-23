package org.example.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Voo {

    private int id;
    private String origem;
    private String destino;
    private LocalDateTime dataHora;
    private Aviao aviao;
    private List<Passageiro> passageiros;

    public Voo(int id, String origem, String destino, LocalDateTime dataHora, Aviao aviao) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.dataHora = dataHora;
        this.aviao = aviao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Aviao getAviao() {
        return aviao;
    }

    public void setAviao(Aviao aviao) {
        this.aviao = aviao;
    }

    public List<Passageiro> getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(List<Passageiro> passageiros) {
        this.passageiros = passageiros;
    }

    public int getCapacidadeDisponivel() {
        if (passageiros == null) {
            return aviao.getCapacidade();
        }
        return aviao.getCapacidade() - passageiros.size();
    }
}
