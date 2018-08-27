package co.uk.eclair.viagami.errors;

/**
 * Created by ${Eclair} on 8/21/2018.
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
