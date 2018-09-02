package co.uk.eclair.viagami;

import co.uk.eclair.viagami.controllers.AuthController;
import co.uk.eclair.viagami.facades.UserFacade;
import co.uk.eclair.viagami.payload.JWTAuthenticationResponse;
import co.uk.eclair.viagami.payload.SignUpRequest;
import co.uk.eclair.viagami.repositories.UserRepository;
import co.uk.eclair.viagami.security.JWTTokenProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by ${Eclair} on 9/1/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {
    @InjectMocks
    private AuthController authController;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private UserFacade userFacade;
    @Mock
    private JWTTokenProvider jwtTokenProvider;

    private SignUpRequest signUpRequest;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        signUpRequest = new SignUpRequest();
        signUpRequest.setName("Eclair Lumu");
        signUpRequest.setEmail("eclairlumu@gmail.com");
        signUpRequest.setPassword("eclair");
    }

    @Test
    public void registerUser(){
       // ResponseEntity<?> responseEntity = authController.registerUser(signUpRequest);
    }
}
