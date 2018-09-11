package co.uk.eclair.viagami.payload;

/**
 * Created by ${Eclair} on 8/27/2018.
 */
public class ApiResponseDTO {
    private Boolean success;
    private String message;

    public ApiResponseDTO(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
