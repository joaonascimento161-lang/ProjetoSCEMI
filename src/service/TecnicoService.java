package service;

import model.Tecnicos;
import exception.TecnicoNaoEncontradoException;
import exception.ManutencaoInvalidaException;

public class TecnicoService {
    private final Tecnicos[] tecnicos = new Tecnicos[100];
    private int total = 0;
    private ManutencaoService manutencaoService;

    public void setManutencaoService(ManutencaoService manutencaoService) {
        this.manutencaoService = manutencaoService;
    }

    public Tecnicos[] getArray() { return tecnicos; }
    public int getTotal() { return total; }

    public void cadastrar() {
        if (total >= tecnicos.length) {
            System.out.println("Limite de técnicos atingido.");
            return;
        }
        tecnicos[total] = Tecnicos.cadastrarTecnico(tecnicos);
        total++;
        System.out.println("Técnico cadastrado!");
    }

    public Tecnicos buscarPorCodigo(int codigo) throws TecnicoNaoEncontradoException {
        for (int i = 0; i < total; i++) {
            if (tecnicos[i].getCodigo() == codigo) {
                return tecnicos[i];
            }
        }
        throw new TecnicoNaoEncontradoException("Técnico com código " + codigo + " não encontrado.");
    }

    public void consultar(int codigo) throws TecnicoNaoEncontradoException {
        buscarPorCodigo(codigo).exibirInformacoes();
    }

    public void alterar(int codigo) throws TecnicoNaoEncontradoException {
        buscarPorCodigo(codigo).alterarInformacoes();
    }

    public void excluir(int codigo) throws TecnicoNaoEncontradoException, ManutencaoInvalidaException {
        Tecnicos t = buscarPorCodigo(codigo);

        if (manutencaoService != null && manutencaoService.possuiManutencaoAbertaParaTecnico(codigo)) {
            throw new ManutencaoInvalidaException(
                    "Este técnico é responsável por uma manutenção em aberto e não pode ser excluído.");
        }

        t.excluir();
    }

    public void listar() {
        for (int i = 0; i < total; i++) {
            tecnicos[i].exibirInformacoes();
        }
    }
}
