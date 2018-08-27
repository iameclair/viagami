package co.uk.eclair.viagami.errors;

import java.util.function.Supplier;

/**
 * Created by ${Eclair} on 8/26/2018.
 */
public class ViagamiRoleNotFoundException extends Exception  {
    public ViagamiRoleNotFoundException(String role_is_not_found) {
        super(role_is_not_found);
    }
}
