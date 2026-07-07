import model.Equipamentos;
import model.Manutencoes;
import model.Tecnicos;
import exception.CodigoDuplicadoException;
import java.util.Scanner;

public void main(String[] args) {
    Equipamentos[] equipamentos = new Equipamentos[100];
    Manutencoes[] manutencoes = new Manutencoes[100];
    Tecnicos[] tecnicos = new Tecnicos[100];
    int totalEquipamentos = 0;
    int totalTecnicos = 0;
    int totalManutencao = 0;
    Scanner sc = new Scanner(System.in);
    int opcao = -1;

    do {
        System.out.println("==============================================");
        System.out.println("SISTEMA DE CONTROLE DE EQUIPAMENTOS");
        System.out.println("==============================================");
        System.out.println("1 - Equipamentos");
        System.out.println("2 - Técnicos");
        System.out.println("3 - Manutenções");
        System.out.println("4 - Relatórios");
        System.out.println("0 - Encerrar sistema");
        System.out.print("Escolha uma opção: ");

        try {
            opcao = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite um número válido.");
            continue;
        }

        switch (opcao) {
            case 1:
                System.out.println("==============================================");
                System.out.println("                EQUIPAMENTOS");
                System.out.println("==============================================");
                System.out.println("1 - Cadastrar equipamento");
                System.out.println("2 - Consultar equipamento");
                System.out.println("3 - Alterar enformações");
                System.out.println("4 - Excluir equipamento");
                System.out.println("5 - Listar equipamentos");
                System.out.println("0 - Voltar ao menu principal");
                System.out.print("Escolha uma opção: ");
                int subOpcaoEquip = Integer.parseInt(sc.nextLine());

                switch (subOpcaoEquip) {
                    case 1:
                        try {
                            if (totalEquipamentos < 100) {
                                equipamentos[totalEquipamentos] = Equipamentos.cadastrarEquipamento(equipamentos);
                                totalEquipamentos++;
                                System.out.println("Equipamento cadastrado!");
                            } else {
                                System.out.println("Limite atingido.");
                            }
                        } catch (CodigoDuplicadoException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.print("Código: ");
                        int codE = Integer.parseInt(sc.nextLine());
                        boolean achouE = false;
                        for (int cont = 0; cont < totalEquipamentos; cont++) {
                            if (equipamentos[cont].getCodigo() == codE) {
                                equipamentos[cont].exibirInformacoes();
                                achouE = true;
                                break;
                            }
                        }
                        if (!achouE) System.out.println("Não encontrado.");
                        break;
                    case 3:
                        System.out.print("Código: ");
                        int codAltE = Integer.parseInt(sc.nextLine());
                        for (int cont = 0; cont < totalEquipamentos; cont++) {
                            if (equipamentos[cont].getCodigo() == codAltE) {
                                equipamentos[cont].alterarInformacoes();
                                break;
                            }
                        }
                        break;
                    case 4:
                        System.out.print("Código: ");
                        int codExcE = Integer.parseInt(sc.nextLine());
                        for (int cont = 0; cont < totalEquipamentos; cont++) {
                            if (equipamentos[cont].getCodigo() == codExcE) {
                                equipamentos[cont].excluir();
                                break;
                            }
                        }
                        break;
                    case 5:
                        for (int cont = 0; cont < totalEquipamentos; cont++) {
                            equipamentos[cont].exibirInformacoes();
                        }
                        break;
                }
                break;

            case 2:
                System.out.println("==============================================");
                System.out.println("                TÉCNICOS");
                System.out.println("==============================================");
                System.out.println("1 - Cadastrar técnico");
                System.out.println("2 - Consultar técnico");
                System.out.println("3 - Alterar informações");
                System.out.println("4 - Excluir técnico");
                System.out.println("5 - Listar técnicos");
                System.out.println("0 - Voltar");
                System.out.print("Escolha: ");
                int subOpcaoTec = Integer.parseInt(sc.nextLine());

                switch (subOpcaoTec) {
                    case 1:
                        if (totalTecnicos < 100) {
                            tecnicos[totalTecnicos] = Tecnicos.cadastrarTecnico();
                            totalTecnicos++;
                            System.out.println("Técnico cadastrado!");
                        } else {
                            System.out.println("Limite atingido.");
                        }
                        break;
                    case 2:
                        System.out.print("Código: ");
                        int codT = Integer.parseInt(sc.nextLine());
                        boolean achouT = false;
                        for (int cont = 0; cont < totalTecnicos; cont++) {
                            if (tecnicos[cont].getCodigo() == codT) {
                                tecnicos[cont].exibirInformacoes();
                                achouT = true;
                                break;
                            }
                        }
                        if (!achouT) System.out.println("Não encontrado.");
                        break;
                    case 3:
                        System.out.print("Código: ");
                        int codAltT = Integer.parseInt(sc.nextLine());
                        for (int cont = 0; cont < totalTecnicos; cont++) {
                            if (tecnicos[cont].getCodigo() == codAltT) {
                                tecnicos[cont].alterarInformacoes();
                                break;
                            }
                        }
                        break;
                    case 4:
                        System.out.print("Código: ");
                        int codExcT = Integer.parseInt(sc.nextLine());
                        for (int cont = 0; cont < totalTecnicos; cont++) {
                            if (tecnicos[cont].getCodigo() == codExcT) {
                                tecnicos[cont].excluir();
                                break;
                            }
                        }
                        break;
                    case 5:
                        for (int cont = 0; cont < totalTecnicos; cont++) {
                            tecnicos[cont].exibirInformacoes();
                        }
                        break;
                }
                break;

            case 3:
                System.out.println("==============================================");
                System.out.println("                MANUTENÇÃO");
                System.out.println("==============================================");
                System.out.println(" ");
                System.out.println("1 - Registrar manutenção");
                System.out.println("2 - Consultar manutenção");
                System.out.println("3 - Alterar Situação");
                System.out.println("4 - Finalizar manutenção");
                System.out.println("5 - Listar manutenção");
                System.out.println("0 - Voltar ao menu principal");
                System.out.println("Escolha uma opção: ");
                int opcaoManutencao = Integer.parseInt(sc.nextLine());

                switch (opcaoManutencao) {
                    case 1:
                        if (totalManutencao < 100) {
                            manutencoes[totalManutencao] = Manutencoes.registrarManutencao();
                            totalManutencao++;
                            System.out.println("Manutenção registrada!");
                        } else {
                            System.out.println("Limite atingido.");
                        }
                        break;
                    case 2:
                        System.out.println("Código: ");
                        int codM = Integer.parseInt(sc.nextLine());
                        boolean achouM = false;
                        for (int cont = 0; cont < totalManutencao; cont++) {
                            if (manutencoes[cont].getCodigo() == codM) {
                                manutencoes[cont].ConsultarManutencao();
                                achouM = true;
                                break;
                            }
                        }
                        if (!achouM) System.out.println("Não encontrado.");
                        break;
                    case 3:
                        System.out.println("Código: ");
                        int codAltM = Integer.parseInt(sc.nextLine());
                        for (int cont = 0; cont < totalManutencao; cont++) {
                            if (manutencoes[cont].getCodigo() == codAltM) {
                                manutencoes[cont].AlterarSituacao();
                                break;
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Código: ");
                        int codExcM = Integer.parseInt(sc.nextLine());
                        for (int cont = 0; cont < totalManutencao; cont++) {
                            if (manutencoes[cont].getCodigo() == codExcM) {
                                manutencoes[cont].FinalizarManutencao();
                                break;
                            }
                        }
                        break;
                    case 5:
                        for (int cont = 0; cont < totalManutencao; cont++) {
                            manutencoes[cont].ListarManutencoes();
                            break;
                        }
                        break;
                    case 4:
                        System.out.println("Relatórios em desenvolvimento...");
                        break;
                    case 0:
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
    }while (opcao != 0) ;
}