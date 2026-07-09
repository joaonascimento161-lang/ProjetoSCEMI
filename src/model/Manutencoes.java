package model;

import static util.Entrada.sc;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Manutencoes {
    private int codigoManutencao;
    private int codigoEquipamento;
    private int codigoTecnico;
    private String dataAbertura;
    private String dataEncerramento;
    private String tipo;
    private String descricao;
    private String situacao;

    public static final String ABERTA = "Aberta";
    public static final String EM_ANDAMENTO = "Em andamento";
    public static final String FINALIZADA = "Finalizada";

    public Manutencoes(int codigoManutencao, int codigoEquipamento, int codigoTecnico, String dataAbertura, String tipo, String descricao) {
        this.codigoManutencao = codigoManutencao;
        this.codigoEquipamento = codigoEquipamento;
        this.codigoTecnico = codigoTecnico;
        this.dataAbertura = dataAbertura;
        this.tipo = tipo;
        this.descricao = descricao;
        this.situacao = ABERTA;
        this.dataEncerramento = "---";
    }

    public int getCodigoManutencao() { return codigoManutencao; }
    public int getCodigoEquipamento() { return codigoEquipamento; }
    public int getCodigoTecnico() { return codigoTecnico; }
    public String getSituacao() { return situacao; }

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

    public static Manutencoes registrarManutencao(Manutencoes[] manutencoes, Equipamentos[] equipamentos, Tecnicos[] tecnicos) {
        int codM = 0;
        System.out.println("\n--- REGISTRAR NOVA MANUTENÇÃO ---");
        System.out.print("Código da Manutenção: ");
        while (true) {
            try {
                codM = Integer.parseInt(sc.nextLine());
                final int codigoFinal = codM;
                boolean duplicado = false;
                for (Manutencoes m : manutencoes) {
                    if (m != null && m.getCodigoManutencao() == codigoFinal) {
                        duplicado = true;
                        break;
                    }
                }
                if (duplicado) {
                    System.out.print("Erro: Código já existe. Digite outro: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Digite um número válido: ");
            }
        }

        int codE;
        while (true) {
            System.out.print("Código do Equipamento: ");
            try {
                codE = Integer.parseInt(sc.nextLine());
                final int codEFinal = codE;
                boolean existe = false;
                for (Equipamentos eq : equipamentos) {
                    if (eq != null && eq.getCodigo() == codEFinal) {
                        existe = true;
                        break;
                    }
                }
                if (!existe) {
                    System.out.println("Erro: Equipamento não encontrado. Tente novamente.");
                    continue;
                }

                boolean manutencaoAberta = false;
                for (Manutencoes m : manutencoes) {
                    if (m != null && m.getCodigoEquipamento() == codEFinal && !m.getSituacao().equals(FINALIZADA)) {
                        manutencaoAberta = true;
                        break;
                    }
                }
                if (manutencaoAberta) {
                    System.out.println("Erro: Este equipamento já possui uma manutenção em aberto.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }

        int codT;
        while (true) {
            System.out.print("Código do Técnico Responsável: ");
            try {
                codT = Integer.parseInt(sc.nextLine());
                final int codTFinal = codT;
                boolean existe = false;
                for (Tecnicos t : tecnicos) {
                    if (t != null && t.getCodigo() == codTFinal) {
                        existe = true;
                        break;
                    }
                }
                if (!existe) {
                    System.out.println("Erro: Técnico não encontrado. Tente novamente.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }

        String dataA = lerCampoObrigatorio("Data de Abertura (dd/mm/aaaa): ");

        System.out.print("Tipo (1-Preventiva / 2-Corretiva): ");
        String tipoManut = sc.nextLine().trim().equals("1") ? "Preventiva" : "Corretiva";

        String desc = lerCampoObrigatorio("Descrição do Problema: ");

        return new Manutencoes(codM, codE, codT, dataA, tipoManut, desc);
    }

    public void exibirInformacoes() {
        System.out.println("----------------------------------------------");
        System.out.println("CÓD. MANUTENÇÃO: " + codigoManutencao);
        System.out.println("SITUAÇÃO:        " + situacao);
        System.out.println("EQUIPAMENTO:     " + codigoEquipamento);
        System.out.println("TÉCNICO:         " + codigoTecnico);
        System.out.println("TIPO:            " + tipo);
        System.out.println("ABERTURA:        " + dataAbertura);
        System.out.println("ENCERRAMENTO:    " + dataEncerramento);
        System.out.println("DESCRIÇÃO:       " + descricao);
    }

    public void alterarSituacao() {
        if (this.situacao.equals(FINALIZADA)) {
            System.out.println("Erro: esta manutenção já foi finalizada e não pode ser alterada.");
            return;
        }

        System.out.println("\nAlterar Situação (Atual: " + situacao + "):");
        System.out.println("1 - Aberta\n2 - Em andamento\n3 - Finalizada");
        System.out.print("Escolha: ");
        String op = sc.nextLine().trim();

        if (op.equals("1")) {
            this.situacao = ABERTA;
            System.out.println("Situação atualizada com sucesso!");
        } else if (op.equals("2")) {
            this.situacao = EM_ANDAMENTO;
            System.out.println("Situação atualizada com sucesso!");
        } else if (op.equals("3")) {
            finalizarManutencao();
        } else {
            System.out.println("Opção inválida.");
        }
    }

    public void finalizarManutencao() {
        if (this.situacao.equals(FINALIZADA)) {
            System.out.println("Erro: esta manutenção já foi finalizada anteriormente. ");
            return;
        }
        this.situacao = FINALIZADA;
        this.dataEncerramento = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Manutenção finalizada com sucesso! Data da finalização: " + this.dataEncerramento);
    }
}
