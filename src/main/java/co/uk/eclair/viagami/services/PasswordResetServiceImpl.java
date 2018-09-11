package co.uk.eclair.viagami.services;

import co.uk.eclair.viagami.documents.PasswordResetToken;
import co.uk.eclair.viagami.documents.UserDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by ${Eclair} on 9/6/2018.
 */
@Service
public class PasswordResetServiceImpl implements PasswordResetService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordResetServiceImpl.class);
    @Value("${viagami.resetPasswordTokenInMilliseconds}")
    private Long resetPasswordTokenInMilliseconds;
    public PasswordResetToken createPasswordResetTokenForUser(UserDocument userDocument, String token){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + resetPasswordTokenInMilliseconds);
        return new PasswordResetToken(now.getTime()+new Random().nextInt(5),token, userDocument, expiryDate);
    }
}
