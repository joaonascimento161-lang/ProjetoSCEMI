package model;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import exception.CodigoDuplicadoException;

public class Equipamentos {
    private int codigo;
    private String nome;
    private String categoria;
    private String fabricante;
    private String modelo;
    private String setor;
    private String data;
    private boolean status;

    private static final Scanner sc = new Scanner(System.in);
    private static List<Integer> codigosRegistrados = new ArrayList<>();

    public Equipamentos(int codigo, String nome, String categoria, String fabricante, String modelo, String setor, String data) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.setor = setor;
        this.data = data;
        this.status = true; // Por padrão, nasce ativo
    }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getFabricante() { return fabricante; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public static Equipamentos cadastrarEquipamento(Equipamentos[] equipamentos) throws CodigoDuplicadoException {
        int codigoDigitado = 0;

        System.out.println("\n--- CADASTRO DE EQUIPAMENTO ---");
        System.out.print("Digite o código do equipamento: ");

        while (true) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                System.out.print("O código não pode ser vazio. Digite novamente: ");
                continue;
            }
            try {
                codigoDigitado = Integer.parseInt(line);
                if (codigosRegistrados.contains(codigoDigitado)) {
                    throw new CodigoDuplicadoException("Erro: O código " + codigoDigitado + " já está cadastrado!");
                }
                codigosRegistrados.add(codigoDigitado);
                break;
            } catch (NumberFormatException ex) {
                System.out.print("Entrada inválida! Digite um número inteiro para o código: ");
            }
        }

        System.out.print("Digite o nome: ");
        String nome = sc.nextLine().trim();

        System.out.print("Digite a categoria: ");
        String categoria = sc.nextLine().trim();

        System.out.print("Digite o fabricante: ");
        String fabricante = sc.nextLine().trim();

        System.out.print("Digite o modelo: ");
        String modelo = sc.nextLine().trim();

        System.out.print("Digite o setor: ");
        String setor = sc.nextLine().trim();

        System.out.print("Digite a data (dd/mm/aaaa): ");
        String data = sc.nextLine().trim();

        return new Equipamentos(codigoDigitado, nome, categoria, fabricante, modelo, setor, data);
    }

    public void ConsultarEquipamento() {
        if(codigo == this.codigo) {
            System.out.println("\n--- CONSULTA DE EQUIPAMENTO (Código: " + this.codigo + ") ---");
            exibirInformacoes();
        }
    }

    public void exibirInformacoes() {
        if (!this.status) {
            System.out.println("Equipamento [Código: " + this.codigo + "] está marcado como EXCLUÍDO.");
            return;
        }
        System.out.println("----------------------------------------------");
        System.out.println("Código:     " + this.codigo);
        System.out.println("Nome:       " + this.nome);
        System.out.println("Categoria:  " + this.categoria);
        System.out.println("Fabricante: " + this.fabricante);
        System.out.println("Modelo:     " + this.modelo);
        System.out.println("Setor:      " + this.setor);
        System.out.println("Data:       " + this.data);
        System.out.println("Status:     Ativo");
    }

    public void alterarInformacoes() {
        System.out.println("\n--- ALTERAR INFORMAÇÕES (Código: " + this.codigo + ") ---");
        System.out.println("1 - Nome (" + this.nome + ")");
        System.out.println("2 - Categoria (" + this.categoria + ")");
        System.out.println("3 - Fabricante (" + this.fabricante + ")");
        System.out.println("4 - Modelo (" + this.modelo + ")");
        System.out.println("5 - Setor (" + this.setor + ")");
        System.out.println("6 - Data (" + this.data + ")");
        System.out.println("0 - Cancelar");
        System.out.print("Escolha o que deseja alterar: ");

        try {
            int opcao = Integer.parseInt(sc.nextLine());
            switch (opcao) {
                case 1:
                    System.out.print("Novo nome: ");
                    this.nome = sc.nextLine().trim();
                    break;
                case 2:
                    System.out.print("Nova categoria: ");
                    this.categoria = sc.nextLine().trim();
                    break;
                case 3:
                    System.out.print("Novo fabricante: ");
                    this.fabricante = sc.nextLine().trim();
                    break;
                case 4:
                    System.out.print("Novo modelo: ");
                    this.modelo = sc.nextLine().trim();
                    break;
                case 5:
                    System.out.print("Novo setor: ");
                    this.setor = sc.nextLine().trim();
                    break;
                case 6:
                    System.out.print("Nova data: ");
                    this.data = sc.nextLine().trim();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida.");
                    return;
            }
            System.out.println("Informação atualizada com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite um número válido.");
        }
    }

    public void excluir() {
        this.status = false;
        System.out.println("Equipamento código " + this.codigo + " excluído com sucesso!");
    }
}