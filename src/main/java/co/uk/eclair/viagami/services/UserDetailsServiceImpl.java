package co.uk.eclair.viagami.services;

import co.uk.eclair.viagami.documents.PasswordResetToken;
import co.uk.eclair.viagami.documents.UserDocument;
import co.uk.eclair.viagami.security.UserPrincipal;
import co.uk.eclair.viagami.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by ${Eclair} on 8/21/2018.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserDocument> user = userRepository.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("user not found"));

       return UserPrincipal.create(user.get());
    }

    @Transactional
    public UserDetails loadByUserId(Long id) {
        UserDocument userDocument = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id "+ id)
        );

        return UserPrincipal.create(userDocument);
    }

    public void createPasswordResetTokenForUser(UserDocument userDocument, String token){
        PasswordResetToken theToken = new PasswordResetToken(token, userDocument);

    }
}
