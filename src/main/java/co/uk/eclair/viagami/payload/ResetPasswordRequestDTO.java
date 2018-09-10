package co.uk.eclair.viagami.payload;

import javax.validation.constraints.NotBlank;

/**
 * Created by ${Eclair} on 9/10/2018.
 */
public class ResetPasswordRequestDTO {
    @NotBlank
    private String newPassword;
    @NotBlank
    private String confirmPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
