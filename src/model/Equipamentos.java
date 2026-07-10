package model;

import exception.CodigoDuplicadoException;

import java.time.LocalDateTime;

import static util.Entrada.sc;

public class Equipamentos {
    private int codigo;
    private String nome;
    private String categoria;
    private String fabricante;
    private String modelo;
    private String setor;
    private String data;
    private String status;

    public static final String OPERANDO = "Operando";
    public static final String EM_MANUTENCAO = "Em manutenção";
    public static final String INATIVO = "Inativo";

    public Equipamentos(int codigo, String nome, String categoria, String fabricante, String modelo, String setor, String data) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.setor = setor;
        this.data = data;
        this.status = OPERANDO;
    }

    //getters e setters
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

    public String getStatus() { return status; }

    public void setStatus(String status) {
        if (!status.equals(OPERANDO) && !status.equals(EM_MANUTENCAO) && !status.equals(INATIVO)) {
            throw new IllegalArgumentException("Status inválido: " + status);
        }
        this.status = status;
    }

    // Método para ler campos obrigatórios de equipamentos, garantindo que não fiquem vazios.
    private static String lerCampoObrigatorio(String mensagem) {
        String valor;
        do {
            System.out.print(mensagem);
            valor = sc.nextLine().trim();
            if (valor.isEmpty()) {
                System.out.println("Este campo não pode ficar vazio.");
            }
        } while (valor.isEmpty());
        return valor;
    }
    // Método para cadastrar um novo equipamento
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

                for (Equipamentos e : equipamentos) {
                    if (e != null && e.getCodigo() == codigoDigitado) {
                        throw new CodigoDuplicadoException("Erro: O código " + codigoDigitado + " já está cadastrado!");
                    }
                }
                break;
            } catch (NumberFormatException ex) {
                System.out.print("Entrada inválida! Digite um número inteiro para o código: ");
            }
        }

        String nome = lerCampoObrigatorio("Digite o nome: ");
        String categoria = lerCampoObrigatorio("Digite a categoria: ");
        String fabricante = lerCampoObrigatorio("Digite o fabricante: ");
        String modelo = lerCampoObrigatorio("Digite o modelo: ");
        String setor = lerCampoObrigatorio("Digite o setor: ");
        String data = lerCampoObrigatorio("Digite a data da instalação/aquisição [dd/mm/aaaa HH:mm]: ");

        return new Equipamentos(codigoDigitado, nome, categoria, fabricante, modelo, setor, data);
    }
    // Método para consultar um equipamento pelo código.
    public void consultarEquipamento(int codigoConsultado) {
        if (codigoConsultado == this.codigo) {
            System.out.println("\n--- CONSULTA DE EQUIPAMENTO (Código: " + this.codigo + ") ---");
            exibirInformacoes();
        }
    }
    // Método para exibir as informações do equipamento.
    public void exibirInformacoes() {
        if (this.status.equals(INATIVO)) {
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
        System.out.println("Status:     " + this.status);
    }
    // Método para alterar informações do equipamento.
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
                    this.nome = lerCampoObrigatorio("Novo nome: ");
                    break;
                case 2:
                    this.categoria = lerCampoObrigatorio("Nova categoria: ");
                    break;
                case 3:
                    this.fabricante = lerCampoObrigatorio("Novo fabricante: ");
                    break;
                case 4:
                    this.modelo = lerCampoObrigatorio("Novo modelo: ");
                    break;
                case 5:
                    this.setor = lerCampoObrigatorio("Novo setor: ");
                    break;
                case 6:
                    this.data = lerCampoObrigatorio("Nova data [dd/mm/aaaa HH:mm]: ");
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
    // Método para excluir o equipamento (marcar como inativo).
    public void excluir() {
        this.status = INATIVO;
        System.out.println("Equipamento código " + this.codigo + " excluído com sucesso!");
    }
}
