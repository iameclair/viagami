package co.uk.eclair.viagami.payload;

/**
 * Created by ${Eclair} on 8/27/2018.
 */
public class JWTAuthenticationResponse {

    private String accessToken;
    private String tokenType = "Bearer";

    public JWTAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
