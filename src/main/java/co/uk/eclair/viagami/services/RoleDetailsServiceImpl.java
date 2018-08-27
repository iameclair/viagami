package co.uk.eclair.viagami.services;

import co.uk.eclair.viagami.documents.Role;
import co.uk.eclair.viagami.errors.ViagamiRoleNotFoundException;
import co.uk.eclair.viagami.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by ${Eclair} on 8/26/2018.
 */
@Service("roleDetailsService")
public class RoleDetailsServiceImpl implements RoleDetailsService{
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role loadRoleById(int id) throws ViagamiRoleNotFoundException{
       Optional<Role> role = roleRepository.findById(id);
       role.orElseThrow(() -> new ViagamiRoleNotFoundException("role is not found"));
       return role.get();
    }
}
