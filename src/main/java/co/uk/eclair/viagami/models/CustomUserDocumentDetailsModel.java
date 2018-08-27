package co.uk.eclair.viagami.models;

import co.uk.eclair.viagami.documents.UserDocument;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by ${Eclair} on 8/21/2018.
 */
public class CustomUserDocumentDetailsModel extends UserDocument implements UserDetails {

    public CustomUserDocumentDetailsModel(UserDocument userDocument) {
        super(userDocument);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
