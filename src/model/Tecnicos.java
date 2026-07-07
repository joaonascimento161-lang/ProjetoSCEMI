package model;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Tecnicos {
    private int codigo;
    private String nome;
    private String matricula;
    private String setor;
    private String telefone;
    private boolean status;

    private static final Scanner sc = new Scanner(System.in);
    private static List<Integer> codigosRegistrados = new ArrayList<>();

    public Tecnicos(int codigo, String nome, String matricula, String setor, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.matricula = matricula;
        this.setor = setor;
        this.telefone = telefone;
        this.status = true;
    }

    // Getters e Setters
    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public void setStatus(boolean status) { this.status = status; }

    public static Tecnicos cadastrarTecnico() {
        int cod = 0;
        System.out.println("\n--- CADASTRO DE TÉCNICO ---");
        System.out.print("Código: ");
        while (true) {
            try {
                cod = Integer.parseInt(sc.nextLine());
                if (codigosRegistrados.contains(cod)) {
                    System.out.print("Erro: Código já existe. Digite outro: ");
                    continue;
                }
                codigosRegistrados.add(cod);
                break;
            } catch (NumberFormatException e) {
                System.out.print("Digite um número válido para o código: ");
            }
        }

        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Matrícula: ");
        String matricula = sc.nextLine();
        System.out.print("Setor: ");
        String setor = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        return new Tecnicos(cod, nome, matricula, setor, telefone);
    }

    public void exibirInformacoes() {
        if (!status) {
            System.out.println("Técnico [Código: " + codigo + "] - EXCLUÍDO");
            return;
        }
        System.out.println("----------------------------------------------");
        System.out.println("Código:    " + codigo);
        System.out.println("Nome:      " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Setor:     " + setor);
        System.out.println("Telefone:  " + telefone);
    }

    public void alterarInformacoes() {
        System.out.println("\n--- ALTERAR TÉCNICO (Código: " + codigo + ") ---");
        System.out.println("1 - Nome (" + nome + ")");
        System.out.println("2 - Matrícula (" + matricula + ")");
        System.out.println("3 - Setor (" + setor + ")");
        System.out.println("4 - Telefone (" + telefone + ")");
        System.out.println("0 - Cancelar");
        System.out.print("Escolha: ");

        try {
            int op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1: System.out.print("Novo Nome: "); this.nome = sc.nextLine(); break;
                case 2: System.out.print("Nova Matrícula: "); this.matricula = sc.nextLine(); break;
                case 3: System.out.print("Novo Setor: "); this.setor = sc.nextLine(); break;
                case 4: System.out.print("Novo Telefone: "); this.telefone = sc.nextLine(); break;
                case 0: return;
            }
            System.out.println("Alterado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro na entrada.");
        }
    }

    public void excluir() {
        this.status = false;
        System.out.println("Técnico código " + codigo + " excluído.");
    }
}
