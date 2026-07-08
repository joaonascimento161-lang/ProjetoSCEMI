import service.EquipamentoService;
import service.TecnicoService;
import service.ManutencaoService;
import service.RelatorioService;
import exception.CodigoDuplicadoException;
import exception.EquipamentoNaoEncontradoException;
import exception.TecnicoNaoEncontradoException;
import exception.ManutencaoInvalidaException;
import util.Entrada;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner sc = Entrada.sc;

        EquipamentoService equipamentoService = new EquipamentoService();
        TecnicoService tecnicoService = new TecnicoService();
        ManutencaoService manutencaoService = new ManutencaoService(equipamentoService, tecnicoService);
        RelatorioService relatorioService = new RelatorioService(equipamentoService, tecnicoService, manutencaoService);

        equipamentoService.setManutencaoService(manutencaoService);
        tecnicoService.setManutencaoService(manutencaoService);

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
                    menuEquipamentos(sc, equipamentoService);
                    break;
                case 2:
                    menuTecnicos(sc, tecnicoService);
                    break;
                case 3:
                    menuManutencoes(sc, manutencaoService);
                    break;
                case 4:
                    menuRelatorios(sc, relatorioService);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);
    }

    private static void menuEquipamentos(Scanner sc, EquipamentoService equipamentoService) {
        System.out.println("==============================================");
        System.out.println("                EQUIPAMENTOS");
        System.out.println("==============================================");
        System.out.println("1 - Cadastrar equipamento");
        System.out.println("2 - Consultar equipamento");
        System.out.println("3 - Alterar informações");
        System.out.println("4 - Excluir equipamento");
        System.out.println("5 - Listar equipamentos");
        System.out.println("0 - Voltar ao menu principal");
        System.out.print("Escolha uma opção: ");
        int sub = Integer.parseInt(sc.nextLine());

        try {
            switch (sub) {
                case 1:
                    equipamentoService.cadastrar();
                    break;
                case 2:
                    System.out.print("Código: ");
                    equipamentoService.consultar(Integer.parseInt(sc.nextLine()));
                    break;
                case 3:
                    System.out.print("Código: ");
                    equipamentoService.alterar(Integer.parseInt(sc.nextLine()));
                    break;
                case 4:
                    System.out.print("Código: ");
                    equipamentoService.excluir(Integer.parseInt(sc.nextLine()));
                    break;
                case 5:
                    equipamentoService.listar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } catch (CodigoDuplicadoException | EquipamentoNaoEncontradoException | ManutencaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void menuTecnicos(Scanner sc, TecnicoService tecnicoService) {
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
        int sub = Integer.parseInt(sc.nextLine());

        try {
            switch (sub) {
                case 1:
                    tecnicoService.cadastrar();
                    break;
                case 2:
                    System.out.print("Código: ");
                    tecnicoService.consultar(Integer.parseInt(sc.nextLine()));
                    break;
                case 3:
                    System.out.print("Código: ");
                    tecnicoService.alterar(Integer.parseInt(sc.nextLine()));
                    break;
                case 4:
                    System.out.print("Código: ");
                    tecnicoService.excluir(Integer.parseInt(sc.nextLine()));
                    break;
                case 5:
                    tecnicoService.listar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } catch (TecnicoNaoEncontradoException | ManutencaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void menuManutencoes(Scanner sc, ManutencaoService manutencaoService) {
        System.out.println("==============================================");
        System.out.println("                MANUTENÇÃO");
        System.out.println("==============================================");
        System.out.println("1 - Registrar manutenção");
        System.out.println("2 - Consultar manutenção");
        System.out.println("3 - Alterar Situação");
        System.out.println("4 - Finalizar manutenção");
        System.out.println("5 - Listar manutenção");
        System.out.println("0 - Voltar ao menu principal");
        System.out.print("Escolha uma opção: ");
        int sub = Integer.parseInt(sc.nextLine());

        try {
            switch (sub) {
                case 1:
                    manutencaoService.registrar();
                    break;
                case 2:
                    System.out.print("Código: ");
                    manutencaoService.consultar(Integer.parseInt(sc.nextLine()));
                    break;
                case 3:
                    System.out.print("Código: ");
                    manutencaoService.alterarSituacao(Integer.parseInt(sc.nextLine()));
                    break;
                case 4:
                    System.out.print("Código: ");
                    manutencaoService.finalizar(Integer.parseInt(sc.nextLine()));
                    break;
                case 5:
                    manutencaoService.listar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } catch (ManutencaoInvalidaException | EquipamentoNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void menuRelatorios(Scanner sc, RelatorioService relatorioService) {
        System.out.println("==============================================");
        System.out.println("                RELATÓRIOS");
        System.out.println("==============================================");
        System.out.println("1 - Relatório geral");
        System.out.println("2 - Manutenções por equipamento");
        System.out.println("0 - Voltar ao menu principal");
        System.out.print("Escolha uma opção: ");
        int sub = Integer.parseInt(sc.nextLine());

        switch (sub) {
            case 1:
                relatorioService.exibirRelatorioGeral();
                break;
            case 2:
                System.out.print("Código do equipamento: ");
                int codigo = Integer.parseInt(sc.nextLine());
                relatorioService.exibirManutencoesPorEquipamento(codigo);
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}