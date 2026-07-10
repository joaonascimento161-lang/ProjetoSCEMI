package exception;
//Erro de Técnico não encontrado, caso o usuário tente acessar um técnico que não existe.
public class TecnicoNaoEncontradoException extends Exception {
    public TecnicoNaoEncontradoException(String message) {
        super(message);
    }
}
