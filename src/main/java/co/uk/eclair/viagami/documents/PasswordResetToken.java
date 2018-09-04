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

    public PasswordResetToken(String token, UserDocument userDocument){
        this.token = token;
        this.userDocument = userDocument;
    }
}
