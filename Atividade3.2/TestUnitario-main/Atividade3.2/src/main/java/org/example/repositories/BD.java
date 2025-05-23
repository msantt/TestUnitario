package org.example.repositories;

import org.example.entities.Passageiro;
import org.example.entities.Aviao;
import org.example.entities.Voo;
import org.example.entities.Reserva;

import java.util.ArrayList;
import java.util.List;

public class BD {
    public static final List<Passageiro> passageiros = new ArrayList<>();
    public static final List<Aviao> avioes = new ArrayList<>();
    public static final List<Voo> voos = new ArrayList<>();
    public static final List<Reserva> reservas = new ArrayList<>();
}