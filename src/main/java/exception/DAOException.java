package exception;

/**
 * Исключение слоя доступа к данным.
 * Оборачивает технические исключения (SQLException и пр.) в проверяемое исключение домена.
 *
 * @author Демидко М. Д., группа ПИZ-331
 */
public class DAOException extends Exception {

    private static final long serialVersionUID = 1L;

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
