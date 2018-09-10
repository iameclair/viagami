package co.uk.eclair.viagami;

import co.uk.eclair.viagami.controllers.AuthController;
import co.uk.eclair.viagami.facades.UserFacade;
import co.uk.eclair.viagami.payload.SignUpRequestDTO;
import co.uk.eclair.viagami.repositories.UserRepository;
import co.uk.eclair.viagami.security.JWTTokenProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
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

    private SignUpRequestDTO signUpRequestDTO;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setName("Eclair Lumu");
        signUpRequestDTO.setEmail("eclairlumu@gmail.com");
        signUpRequestDTO.setPassword("eclair");
    }

    @Test
    public void registerUser(){
       // ResponseEntity<?> responseEntity = authController.registerUser(signUpRequestDTO);
    }
}
