package br.com.atcare.core.usuario.auth.exception;

/**
 * Exceção lançada quando o usuario não está definido
 * no contexto de execução atual.
 */
public class UserNotDefinedException extends IllegalStateException {

    /**
     * Cria a exceção com mensagem padrão.
     */
    public UserNotDefinedException() {
        super("Usuário não definido no contexto.");
    }

    /**
     * Cria a exceção com mensagem customizada.
     *
     * @param message mensagem detalhada
     */
    public UserNotDefinedException(String message) {
        super(message);
    }
}
