package co.uk.eclair.viagami.repository;

import co.uk.eclair.viagami.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ${Eclair} on 8/19/2018.
 */
public interface UserRepository extends MongoRepository<User, Long> {
}
