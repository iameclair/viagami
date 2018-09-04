package co.uk.eclair.viagami.repositories;

import co.uk.eclair.viagami.documents.PasswordResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ${Eclair} on 9/3/2018.
 */
public interface PasswordTokenRepository extends MongoRepository<PasswordResetToken, Long> {
    PasswordResetToken save(PasswordResetToken token);
}
