package co.uk.eclair.viagami.payload;

import javax.validation.constraints.NotBlank;

/**
 * Created by ${Eclair} on 9/10/2018.
 */
public class ChangePasswordRequestDTO {
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;
    @NotBlank
    private String confirmPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
