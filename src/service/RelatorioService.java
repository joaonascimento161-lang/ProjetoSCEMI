package service;

import model.Relatorios;

public class RelatorioService {
    private final EquipamentoService equipamentoService;
    private final TecnicoService tecnicoService;
    private final ManutencaoService manutencaoService;

    // Construtor que recebe as dependências de EquipamentoService
    public RelatorioService(EquipamentoService equipamentoService,
                             TecnicoService tecnicoService,
                             ManutencaoService manutencaoService) {
        this.equipamentoService = equipamentoService;
        this.tecnicoService = tecnicoService;
        this.manutencaoService = manutencaoService;
    }

    // Método para exibir relatório geral
    public void exibirRelatorioGeral() {
        Relatorios.exibirRelatorioGeral(
                equipamentoService.getArray(), equipamentoService.getTotal(),
                tecnicoService.getArray(), tecnicoService.getTotal(),
                manutencaoService.getArray(), manutencaoService.getTotal());
    }

    // Método para exibir manutenções por equipamento
    public void exibirManutencoesPorEquipamento(int codigoEquipamento) {
        Relatorios.exibirManutencoesPorEquipamento(
                codigoEquipamento, manutencaoService.getArray(), manutencaoService.getTotal());
    }
}
