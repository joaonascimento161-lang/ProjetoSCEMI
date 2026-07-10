package service;

import model.Manutencoes;
import model.Equipamentos;
import exception.ManutencaoInvalidaException;
import exception.EquipamentoNaoEncontradoException;

public class ManutencaoService {
    private final Manutencoes[] manutencoes = new Manutencoes[100];
    private int total = 0;

    private final EquipamentoService equipamentoService;
    private final TecnicoService tecnicoService;

    // Construtor que recebe as dependências de EquipamentoService e TecnicoService
    public ManutencaoService(EquipamentoService equipamentoService, TecnicoService tecnicoService) {
        this.equipamentoService = equipamentoService;
        this.tecnicoService = tecnicoService;
    }

    public Manutencoes[] getArray() { return manutencoes; }
    public int getTotal() { return total; }

    // Método para registrar uma nova manutenção, garantindo que o limite de manutenções não seja excedido.
    public void registrar() throws EquipamentoNaoEncontradoException {
        if (total >= manutencoes.length) {
            System.out.println("Limite de manutenções atingido.");
            return;
        }

        Manutencoes m = Manutencoes.registrarManutencao(
                manutencoes, equipamentoService.getArray(), tecnicoService.getArray());
        manutencoes[total] = m;
        total++;

        equipamentoService.atualizarStatus(m.getCodigoEquipamento(), Equipamentos.EM_MANUTENCAO);

        System.out.println("Manutenção registrada!");
    }

    // Método para buscar uma manutenção por código.
    public Manutencoes buscarPorCodigo(int codigo) throws ManutencaoInvalidaException {
        for (int i = 0; i < total; i++) {
            if (manutencoes[i].getCodigoManutencao() == codigo) {
                return manutencoes[i];
            }
        }
        throw new ManutencaoInvalidaException("Manutenção com código " + codigo + " não encontrada.");
    }

    // Método para consultar as informações de uma manutenção por código.
    public void consultar(int codigo) throws ManutencaoInvalidaException {
        buscarPorCodigo(codigo).exibirInformacoes();
    }

    // Método para alterar a situação de uma manutenção por código.
    public void alterarSituacao(int codigo) throws ManutencaoInvalidaException, EquipamentoNaoEncontradoException {
        Manutencoes m = buscarPorCodigo(codigo);
        m.alterarSituacao();

        if (m.getSituacao().equals(Manutencoes.FINALIZADA)) {
            equipamentoService.atualizarStatus(m.getCodigoEquipamento(), Equipamentos.OPERANDO);
        }
    }

    // Método para finalizar uma manutenção por código.
    public void finalizar(int codigo) throws ManutencaoInvalidaException, EquipamentoNaoEncontradoException {
        Manutencoes m = buscarPorCodigo(codigo);
        m.finalizarManutencao();

        if (m.getSituacao().equals(Manutencoes.FINALIZADA)) {
            equipamentoService.atualizarStatus(m.getCodigoEquipamento(), Equipamentos.OPERANDO);
        }
    }

    // Método para listar todas as manutenções cadastradas.
    public void listar() {
        for (int i = 0; i < total; i++) {
            manutencoes[i].exibirInformacoes();
        }
    }

    // Método para verificar se há manutenções em aberto para um equipamento.
    public boolean possuiManutencaoAbertaParaEquipamento(int codigoEquipamento) {
        for (int i = 0; i < total; i++) {
            if (manutencoes[i].getCodigoEquipamento() == codigoEquipamento
                    && !manutencoes[i].getSituacao().equals(Manutencoes.FINALIZADA)) {
                return true;
            }
        }
        return false;
    }

    // Método para verificar se há manutenções em aberto para um técnico.
    public boolean possuiManutencaoAbertaParaTecnico(int codigoTecnico) {
        for (int i = 0; i < total; i++) {
            if (manutencoes[i].getCodigoTecnico() == codigoTecnico
                    && !manutencoes[i].getSituacao().equals(Manutencoes.FINALIZADA)) {
                return true;
            }
        }
        return false;
    }
}
