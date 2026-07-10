package exception;
//Erro de Manutenção inválida, caso o usuário tente cadastrar uma manutenção com um equipamento ou técnico que não existe.
public class ManutencaoInvalidaException extends Exception {
    public ManutencaoInvalidaException(String message) {
        super(message);
    }
}
