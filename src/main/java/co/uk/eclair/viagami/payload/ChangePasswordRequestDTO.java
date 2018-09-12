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

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword(final String newPassword)
    {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword()
    {
        return confirmPassword;
    }

    public void setConfirmPassword(final String confirmPassword)
    {
        this.confirmPassword = confirmPassword;
    }
}
