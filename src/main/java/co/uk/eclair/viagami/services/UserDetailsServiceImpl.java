package co.uk.eclair.viagami.services;

import co.uk.eclair.viagami.documents.UserDocument;
import co.uk.eclair.viagami.models.CustomUserDocumentDetailsModel;
import co.uk.eclair.viagami.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by ${Eclair} on 8/21/2018.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserDocument> user = userRepository.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return new CustomUserDocumentDetailsModel(user.get());
    }
}
