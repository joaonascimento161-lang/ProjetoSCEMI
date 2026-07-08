package model;

public class Relatorios {

    private Relatorios() {
    }

    public static void exibirRelatorioGeral(Equipamentos[] equipamentos, int totalEquipamentos,
                                             Tecnicos[] tecnicos, int totalTecnicos,
                                             Manutencoes[] manutencoes, int totalManutencoes) {
        System.out.println("========== RELATÓRIOS ==========");
        System.out.println("Total de equipamentos cadastrados: " + totalEquipamentos);
        System.out.println("Total de técnicos cadastrados: " + totalTecnicos);
        System.out.println("Equipamentos em manutenção: "
                + contarEquipamentosPorStatus(equipamentos, totalEquipamentos, Equipamentos.EM_MANUTENCAO));
        System.out.println("Equipamentos ativos (operando): "
                + contarEquipamentosPorStatus(equipamentos, totalEquipamentos, Equipamentos.OPERANDO));
        System.out.println("Equipamentos inativos: "
                + contarEquipamentosPorStatus(equipamentos, totalEquipamentos, Equipamentos.INATIVO));
        System.out.println("Manutenções abertas: " + contarManutencoesAbertas(manutencoes, totalManutencoes));
        System.out.println("Manutenções finalizadas: " + contarManutencoesFinalizadas(manutencoes, totalManutencoes));
        System.out.println("=================================");
    }

    private static int contarEquipamentosPorStatus(Equipamentos[] equipamentos, int total, String status) {
        int contador = 0;
        for (int i = 0; i < total; i++) {
            if (equipamentos[i].getStatus().equals(status)) {
                contador++;
            }
        }
        return contador;
    }

    private static int contarManutencoesAbertas(Manutencoes[] manutencoes, int total) {
        int contador = 0;
        for (int i = 0; i < total; i++) {
            if (!manutencoes[i].getSituacao().equals(Manutencoes.FINALIZADA)) {
                contador++;
            }
        }
        return contador;
    }

    private static int contarManutencoesFinalizadas(Manutencoes[] manutencoes, int total) {
        int contador = 0;
        for (int i = 0; i < total; i++) {
            if (manutencoes[i].getSituacao().equals(Manutencoes.FINALIZADA)) {
                contador++;
            }
        }
        return contador;
    }

    public static int contarManutencoesPorEquipamento(int codigoEquipamento, Manutencoes[] manutencoes, int total) {
        int contador = 0;
        for (int i = 0; i < total; i++) {
            if (manutencoes[i].getCodigoEquipamento() == codigoEquipamento) {
                contador++;
            }
        }
        return contador;
    }

    public static void exibirManutencoesPorEquipamento(int codigoEquipamento, Manutencoes[] manutencoes, int total) {
        int qtd = contarManutencoesPorEquipamento(codigoEquipamento, manutencoes, total);
        System.out.println("Equipamento " + codigoEquipamento + " possui " + qtd + " manutenção(ões) registrada(s).");
    }
}
