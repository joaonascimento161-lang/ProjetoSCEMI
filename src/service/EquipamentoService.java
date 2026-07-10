package service;

import model.Equipamentos;
import exception.CodigoDuplicadoException;
import exception.EquipamentoNaoEncontradoException;
import exception.ManutencaoInvalidaException;

public class EquipamentoService {
    private final Equipamentos[] equipamentos = new Equipamentos[100];
    private int total = 0;
    private ManutencaoService manutencaoService;

    // Setter para injetar a dependência do ManutencaoService
    public void setManutencaoService(ManutencaoService manutencaoService) {
        this.manutencaoService = manutencaoService;
    }


    public Equipamentos[] getArray() { return equipamentos; }
    public int getTotal() { return total; }

    // Método para cadastrar um novo equipamento, garantindo que o código seja único.
    public void cadastrar() throws CodigoDuplicadoException {
        if (total >= equipamentos.length) {
            System.out.println("Limite de equipamentos atingido.");
            return;
        }
        equipamentos[total] = Equipamentos.cadastrarEquipamento(equipamentos);
        total++;
        System.out.println("Equipamento cadastrado!");
    }

    // Método para buscar um equipamento por código.
    public Equipamentos buscarPorCodigo(int codigo) throws EquipamentoNaoEncontradoException {
        for (int i = 0; i < total; i++) {
            if (equipamentos[i].getCodigo() == codigo) {
                return equipamentos[i];
            }
        }
        throw new EquipamentoNaoEncontradoException("Equipamento com código " + codigo + " não encontrado.");
    }

    // Método para consultar as informações de um equipamento por código.
    public void consultar(int codigo) throws EquipamentoNaoEncontradoException {
        buscarPorCodigo(codigo).exibirInformacoes();
    }

    // Método para alterar as informações de um equipamento por código.
    public void alterar(int codigo) throws EquipamentoNaoEncontradoException {
        buscarPorCodigo(codigo).alterarInformacoes();
    }

    // Método para excluir um equipamento por código, verificando se há manutenções em aberto.
    public void excluir(int codigo) throws EquipamentoNaoEncontradoException, ManutencaoInvalidaException {
        Equipamentos e = buscarPorCodigo(codigo);

        if (manutencaoService != null && manutencaoService.possuiManutencaoAbertaParaEquipamento(codigo)) {
            throw new ManutencaoInvalidaException(
                    "Este equipamento possui uma manutenção em aberto e não pode ser excluído.");
        }

        e.excluir();
    }

    // Método para listar todos os equipamentos cadastrados.
    public void listar() {
        for (int i = 0; i < total; i++) {
            equipamentos[i].exibirInformacoes();
        }
    }

    // Método para atualizar o status de um equipamento por código.
    public void atualizarStatus(int codigo, String novoStatus) throws EquipamentoNaoEncontradoException {
        buscarPorCodigo(codigo).setStatus(novoStatus);
    }
}