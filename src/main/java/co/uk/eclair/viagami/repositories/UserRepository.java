package co.uk.eclair.viagami.repositories;

import co.uk.eclair.viagami.documents.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by ${Eclair} on 8/19/2018.
 */
@Repository("userRepository")
public interface UserRepository extends MongoRepository<UserDocument, Long> {
    Optional<UserDocument> findByEmail(String email);
}
