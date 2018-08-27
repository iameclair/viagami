package co.uk.eclair.viagami.facades;

import co.uk.eclair.viagami.documents.Role;
import co.uk.eclair.viagami.documents.UserDocument;
import co.uk.eclair.viagami.exception.ViagamiRoleNotFoundException;
import co.uk.eclair.viagami.services.RoleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by ${Eclair} on 8/27/2018.
 */
@Component
public class UserFacadeImpl implements UserFacade {
    @Autowired
    private RoleDetailsService roleDetailsService;
    @Override
    public void saveUserDocument(UserDocument userDocument) {
        final Set<Role> roles = new HashSet<>();
        final Role role = getRole(2);
        roles.add(role);
        userDocument.setId(new Random().nextInt(10)+new Date().getTime());
        userDocument.setCreatedDate(new Date());
        userDocument.setRoles(roles);
    }

    protected Role getRole(int id){
        try {
            final Role role = roleDetailsService.loadRoleById(id);
            return role;
        } catch (ViagamiRoleNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
