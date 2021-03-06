package co.uk.eclair.viagami.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.Date;

/**
 * Created by ${Eclair} on 9/3/2018.
 */
@Document(collection="passwordresettoken")
public class PasswordResetToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    private Long id;
    private String token;
    @DBRef
    private UserDocument userDocument;
    private Date expiryDate;

    public PasswordResetToken(Long id, String token, UserDocument userDocument, Date expiryDate){
        this.id = id;
        this.token = token;
        this.userDocument = userDocument;
        this.expiryDate=expiryDate;
    }

    public static int getEXPIRATION() {
        return EXPIRATION;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDocument getUserDocument() {
        return userDocument;
    }

    public void setUserDocument(UserDocument userDocument) {
        this.userDocument = userDocument;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
