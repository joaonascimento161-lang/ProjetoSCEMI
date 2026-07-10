package model;

import static util.Entrada.sc;

public class Tecnicos {
    private int codigo;
    private String nome;
    private String matricula;
    private String setor;
    private String telefone;
    private boolean status;

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
    public String getMatricula() { return matricula; }
    public String getSetor() { return setor; }
    public String getTelefone() { return telefone; }
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    // Método para ler campos obrigatórios do técnico, garantindo que não fiquem vazios.
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

    // Método para cadastrar um novo técnico, garantindo que o código e a matrícula sejam únicos.
    public static Tecnicos cadastrarTecnico(Tecnicos[] tecnicos) {
        int cod = 0;
        System.out.println("\n--- CADASTRO DE TÉCNICO ---");
        System.out.print("Código: ");
        while (true) {
            try {
                cod = Integer.parseInt(sc.nextLine());
                final int codigoFinal = cod;
                boolean codigoDuplicado = false;
                for (Tecnicos t : tecnicos) {
                    if (t != null && t.getCodigo() == codigoFinal) {
                        codigoDuplicado = true;
                        break;
                    }
                }
                if (codigoDuplicado) {
                    System.out.print("Erro: Código já existe. Digite outro: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Digite um número válido para o código: ");
            }
        }

        String nome = lerCampoObrigatorio("Nome: ");

        String matricula;
        while (true) {
            matricula = lerCampoObrigatorio("Matrícula: ");
            final String matriculaFinal = matricula;
            boolean matriculaDuplicada = false;
            for (Tecnicos t : tecnicos) {
                if (t != null && t.getMatricula().equalsIgnoreCase(matriculaFinal)) {
                    matriculaDuplicada = true;
                    break;
                }
            }
            if (matriculaDuplicada) {
                System.out.println("Erro: Matrícula já cadastrada para outro técnico.");
                continue;
            }
            break;
        }

        String setor = lerCampoObrigatorio("Setor: ");
        String telefone = lerCampoObrigatorio("Telefone: ");

        return new Tecnicos(cod, nome, matricula, setor, telefone);
    }

    // Método para exibir as informações do técnico, incluindo o status de exclusão.
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

    // Método para alterar as informações do técnico, permitindo a alteração de nome, matrícula, setor e telefone.
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
                case 1: this.nome = lerCampoObrigatorio("Novo Nome: "); break;
                case 2: this.matricula = lerCampoObrigatorio("Nova Matrícula: "); break;
                case 3: this.setor = lerCampoObrigatorio("Novo Setor: "); break;
                case 4: this.telefone = lerCampoObrigatorio("Novo Telefone: "); break;
                case 0: return;
                default: System.out.println("Opção inválida."); return;
            }
            System.out.println("Alterado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro na entrada.");
        }
    }

    // Método para excluir o técnico, alterando seu status para false.
    public void excluir() {
        this.status = false;
        System.out.println("Técnico código " + codigo + " excluído.");
    }
}
