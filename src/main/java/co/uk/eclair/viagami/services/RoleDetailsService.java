package co.uk.eclair.viagami.services;

import co.uk.eclair.viagami.documents.Role;
import co.uk.eclair.viagami.errors.ViagamiRoleNotFoundException;

/**
 * Created by ${Eclair} on 8/26/2018.
 */
public interface RoleDetailsService {
    Role loadRoleById(int id) throws ViagamiRoleNotFoundException;
}
