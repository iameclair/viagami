package co.uk.eclair.viagami.repositories;

import co.uk.eclair.viagami.documents.PasswordResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by ${Eclair} on 9/3/2018.
 */
@Repository
public interface PasswordTokenRepository extends MongoRepository<PasswordResetToken, Long> {
    PasswordResetToken save(PasswordResetToken token);
    Optional<PasswordResetToken> findByToken(String token);
}
