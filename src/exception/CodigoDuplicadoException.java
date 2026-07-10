package exception;
//Erro de Codigo duplicado, caso o usuário tente cadastrar um equipamento ou técnico com um código já existente.
public class CodigoDuplicadoException extends Exception {
    public CodigoDuplicadoException(String message) {
        super(message);
    }
}
