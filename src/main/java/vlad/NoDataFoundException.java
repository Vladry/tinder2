package vlad;

public class NoDataFoundException extends RuntimeException {

    public NoDataFoundException(String message, Long id) {
        super(String.format("%s with id %d not found", message, id));
    }

    public NoDataFoundException(String message) {
        super(message);
    }

}