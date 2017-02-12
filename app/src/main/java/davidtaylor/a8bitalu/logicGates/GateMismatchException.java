package davidtaylor.a8bitalu.logicGates;

/**
 * Created by Nova on 2/1/2017.
 */
public class GateMismatchException extends Exception
{

    public GateMismatchException() {
    }

    public GateMismatchException(String message)
    {
        super(message);
    }

    public GateMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public GateMismatchException(Throwable cause) {
        super(cause);
    }

    public GateMismatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
