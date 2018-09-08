package co.uk.eclair.viagami.services;

import co.uk.eclair.viagami.documents.PasswordResetToken;
import co.uk.eclair.viagami.documents.UserDocument;
import org.springframework.stereotype.Service;

/**
 * Created by ${Eclair} on 9/6/2018.
 */
@Service
public class PasswordResetServiceImpl implements PasswordResetService {
    public PasswordResetToken createPasswordResetTokenForUser(UserDocument userDocument, String token){
        return new PasswordResetToken(token, userDocument);
    }
}
