package exception;
//Erro de Equipamento não encontrado, caso o usuário tente acessar um equipamento que não existe.
public class EquipamentoNaoEncontradoException extends Exception {
    public EquipamentoNaoEncontradoException(String message) {
        super(message);
    }
}
