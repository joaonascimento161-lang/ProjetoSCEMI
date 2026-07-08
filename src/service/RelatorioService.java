package service;

import model.Relatorios;

public class RelatorioService {
    private final EquipamentoService equipamentoService;
    private final TecnicoService tecnicoService;
    private final ManutencaoService manutencaoService;

    public RelatorioService(EquipamentoService equipamentoService,
                             TecnicoService tecnicoService,
                             ManutencaoService manutencaoService) {
        this.equipamentoService = equipamentoService;
        this.tecnicoService = tecnicoService;
        this.manutencaoService = manutencaoService;
    }

    public void exibirRelatorioGeral() {
        Relatorios.exibirRelatorioGeral(
                equipamentoService.getArray(), equipamentoService.getTotal(),
                tecnicoService.getArray(), tecnicoService.getTotal(),
                manutencaoService.getArray(), manutencaoService.getTotal());
    }

    public void exibirManutencoesPorEquipamento(int codigoEquipamento) {
        Relatorios.exibirManutencoesPorEquipamento(
                codigoEquipamento, manutencaoService.getArray(), manutencaoService.getTotal());
    }
}
