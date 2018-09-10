package co.uk.eclair.viagami.payload;

/**
 * Created by ${Eclair} on 9/10/2018.
 */
public class PasswordResetTokenResponseDTO {
    private boolean tokenIsPresent;
    private boolean tokenIsValid;
    private String token;

    public boolean isTokenIsPresent() {
        return tokenIsPresent;
    }

    public void setTokenIsPresent(boolean tokenIsPresent) {
        this.tokenIsPresent = tokenIsPresent;
    }

    public boolean isTokenIsValid() {
        return tokenIsValid;
    }

    public void setTokenIsValid(boolean tokenIsValid) {
        this.tokenIsValid = tokenIsValid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
