package co.uk.eclair.viagami.security;

import co.uk.eclair.viagami.documents.UserDocument;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ${Eclair} on 8/21/2018.
 */
public class UserPrincipal extends UserDocument implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(UserDocument userDocument, Collection<? extends GrantedAuthority> authorities) {
        super(userDocument);
        this.authorities = authorities;
    }

    public static UserPrincipal create(UserDocument userDocument) {
        List<GrantedAuthority> authorities = userDocument.getRoles().stream().map(role ->
            new SimpleGrantedAuthority(role.getRole().name())
        ).collect(Collectors.toList());

        return new UserPrincipal(
          userDocument, authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
