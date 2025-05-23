package org.example;

import org.example.entities.Aviao;
import org.example.entities.Passageiro;
import org.example.entities.Reserva;
import org.example.entities.Voo;
import org.example.repositories.BD;

import java.time.LocalDateTime;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("===== MENU PRINCIPAL =====");
            System.out.println("1 - Cadastrar passageiro");
            System.out.println("2 - Listar passageiros");
            System.out.println("3 - Cadastrar avião");
            System.out.println("4 - Listar aviões");
            System.out.println("5 - Cadastrar voo");
            System.out.println("6 - Listar voos");
            System.out.println("7 - Reservar passagem");
            System.out.println("8 - Listar reservas");
            System.out.println("9 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarPassageiro(scanner);
                    break;
                case 2:
                    listarPassageiros();
                    break;
                case 3:
                    cadastrarAviao(scanner);
                    break;
                case 4:
                    listarAvioes();
                    break;
                case 5:
                    cadastrarVoo(scanner);
                    break;
                case 6:
                    listarVoos();
                    break;
                case 7:
                    reservarPassagem(scanner);
                    break;
                case 8:
                    listarReservas();
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            System.out.println();
        } while (opcao != 9);

        scanner.close();
    }

    private static void cadastrarPassageiro(Scanner scanner) {

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF (apenas números): ");
        String cpf = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        try {
            Passageiro passageiro = new Passageiro(id, nome, cpf, email);
            BD.passageiros.add(passageiro);

            System.out.println("Passageiro cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar passageiro: " + e.getMessage());
        }
    }

    private static void listarPassageiros() {
        if (BD.passageiros.isEmpty()) {
            System.out.println("Nenhum passageiro cadastrado.");
        } else {
            System.out.println("Lista de passageiros:");
            for (Passageiro p : BD.passageiros) {
                System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | CPF: " + p.getCpf() + " | Email: " + p.getEmail());
            }
        }
    }

    private static void cadastrarAviao(Scanner scanner) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Capacidade: ");
        int capacidade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Fabricante: ");
        String fabricante = scanner.nextLine();

        try {
            Aviao aviao = new Aviao(id, modelo, capacidade, fabricante);
            BD.avioes.add(aviao);
            System.out.println("Avião cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar avião: " + e.getMessage());
        }
    }

    private static void listarAvioes() {
        if (BD.avioes.isEmpty()) {
            System.out.println("Nenhum avião cadastrado.");
        } else {
            System.out.println("Lista de aviões:");
            for (Aviao a : BD.avioes) {
                System.out.println("ID: " + a.getId() + " | Modelo: " + a.getModelo() + " | Capacidade: " + a.getCapacidade() + " | Fabricante: " + a.getFabricante());
            }
        }
    }

    private static void cadastrarVoo(Scanner scanner) {
        System.out.print("ID do voo: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Origem: ");
        String origem = scanner.nextLine();

        System.out.print("Destino: ");
        String destino = scanner.nextLine();

        System.out.print("Data e hora (yyyy-MM-ddTHH:mm): ");
        String dataHoraStr = scanner.nextLine();

        System.out.print("ID do avião: ");
        int idAviao = scanner.nextInt();
        scanner.nextLine();

        Aviao aviao = BD.avioes.stream().filter(a -> a.getId() == idAviao).findFirst().orElse(null);
        if (aviao == null) {
            System.out.println("Avião não encontrado.");
            return;
        }

        try {
            LocalDateTime dataHora = java.time.LocalDateTime.parse(dataHoraStr);
            Voo voo = new Voo(id, origem, destino, dataHora, aviao);
            voo.setPassageiros(new java.util.ArrayList<>());
            BD.voos.add(voo);

            System.out.println("Voo cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar voo: " + e.getMessage());

        }
    }

    private static void listarVoos() {
        if (BD.voos.isEmpty()) {
            System.out.println("Nenhum voo cadastrado.");
        } else {
            System.out.println("Lista de voos:");
            for (Voo v : BD.voos) {
                System.out.println("ID: " + v.getId() + " | Origem: " + v.getOrigem() + " | Destino: " + v.getDestino() +
                        " | Data/Hora: " + v.getDataHora() + " | Avião: " + v.getAviao().getModelo() +
                        " | Capacidade disponível: " + v.getCapacidadeDisponivel());
            }
        }
    }

    private static void reservarPassagem(Scanner scanner) {
        System.out.print("ID da reserva: ");
        int idReserva = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID do passageiro: ");
        int idPassageiro = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID do voo: ");
        int idVoo = scanner.nextInt();
        scanner.nextLine();

        Passageiro passageiro = BD.passageiros.stream().filter(p -> p.getId() == idPassageiro).findFirst().orElse(null);
        Voo voo = BD.voos.stream().filter(v -> v.getId() == idVoo).findFirst().orElse(null);

        if (passageiro == null) {
            System.out.println("Passageiro não encontrado.");
            return;
        }
        if (voo == null) {
            System.out.println("Voo não encontrado.");
            return;
        }

        try {
            Reserva reserva = new Reserva(idReserva, passageiro, voo, java.time.LocalDateTime.now());
            BD.reservas.add(reserva);
            System.out.println("Reserva realizada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao reservar passagem: " + e.getMessage());
        }
    }

    private static void listarReservas() {
        if (BD.reservas.isEmpty()) {
            System.out.println("Nenhuma reserva cadastrada.");
        } else {
            System.out.println("Lista de reservas:");
            for (Reserva r : BD.reservas) {
                System.out.println("ID: " + r.getId() + " | Passageiro: " + r.getPassageiro().getNome() +
                        " | Voo: " + r.getVoo().getId() + " | Data da reserva: " + r.getDataReserva());
            }
        }
    }
}