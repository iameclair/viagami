package co.uk.eclair.viagami.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by ${Eclair} on 8/24/2018.
 */
@Document(collection = "roles")
public class Role {
    @Id private int roleId;
    private RoleType role;

    public int getRoleId() {
        return roleId;
    }

    public Role() {
    }

    public Role(int roleId, RoleType role) {
        this.roleId = roleId;
        this.role = role;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
