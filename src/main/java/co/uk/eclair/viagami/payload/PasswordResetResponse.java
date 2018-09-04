package co.uk.eclair.viagami.payload;

/**
 * Created by ${Eclair} on 9/3/2018.
 */
public class PasswordResetResponse {
    private String message;
    private String error;

    public PasswordResetResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
