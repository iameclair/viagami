package co.uk.eclair.viagami.repositories;

import co.uk.eclair.viagami.documents.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by ${Eclair} on 8/26/2018.
 */
@Repository("roleRepository")
public interface RoleRepository extends MongoRepository<Role, Integer> {
    @Override
    Optional<Role> findById(Integer integer);
}
