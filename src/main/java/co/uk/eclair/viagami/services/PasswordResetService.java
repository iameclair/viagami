package co.uk.eclair.viagami.services;

import co.uk.eclair.viagami.documents.PasswordResetToken;
import co.uk.eclair.viagami.documents.UserDocument;

/**
 * Created by ${Eclair} on 9/6/2018.
 */
public interface PasswordResetService {
   PasswordResetToken createPasswordResetTokenForUser(UserDocument userDocument, String token);
}
