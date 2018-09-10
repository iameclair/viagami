package co.uk.eclair.viagami.payload;

import javax.validation.constraints.NotBlank;

/**
 * Created by ${Eclair} on 8/27/2018.
 */
public class LoginRequestDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

