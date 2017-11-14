package exception;

public class MyException extends RuntimeException {

    public MyException (String errorType) {
        super(errorType);
    }

}