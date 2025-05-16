package org.example;

import org.example.entities.Person;
import org.example.repositories.PersonRepository;
import org.example.util.Validator;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PersonRepository personRepository = new PersonRepository();
        Validator validator = new Validator();

        int opcao;
        do {
            System.out.println("\n--- MENU PASSAGEIROS ---");
            System.out.println("1. Adicionar passageiro");
            System.out.println("2. Listar passageiros");
            System.out.println("3. Remover passageiro por ID");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    validator.validarCPF(cpf);

                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    validator.validarEmail(email);

                    personRepository.addPerson(new Person(id, nome, cpf, email));
                    System.out.println("Person Add!");
                    break;

                case 2:
                    System.out.println("\n--- Lista de Passageiros ---");
                    for (Person p : personRepository.searchAll()) {
                        System.out.println("ID: " + p.getId() + ", Nome: " + p.getName() + ", CPF: " + p.getCpf());
                    }
                    break;

                case 3:
                    System.out.print("ID do passageiro para remover: ");
                    int idRemover = scanner.nextInt();
                    personRepository.removePerson(idRemover);
                    System.out.println("Remoção concluída (se existia).");
                    break;

                case 4:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 4);

        scanner.close();
    }
}
