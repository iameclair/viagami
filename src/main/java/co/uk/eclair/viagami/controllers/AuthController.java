package co.uk.eclair.viagami.controllers;
import co.uk.eclair.viagami.documents.UserDocument;
import co.uk.eclair.viagami.facades.UserFacade;
import co.uk.eclair.viagami.payload.ApiResponse;
import co.uk.eclair.viagami.payload.JWTAuthenticationResponse;
import co.uk.eclair.viagami.payload.LoginRequest;
import co.uk.eclair.viagami.payload.SignUpRequest;
import co.uk.eclair.viagami.repositories.UserRepository;
import co.uk.eclair.viagami.security.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by ${Eclair} on 8/19/2018.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserFacade userFacade;
    @Autowired
    private JWTTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  loginRequest.getEmail(),
                  loginRequest.getPassword()
          )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthenticationResponse(jwt));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
        if(userRepository.existsByEmail(signUpRequest.getEmail())){
            return new ResponseEntity<>(new ApiResponse(false, "Email is already exist!"),
                    HttpStatus.BAD_REQUEST);
        }
        UserDocument userDocument = new UserDocument();
        userDocument.setEmail(signUpRequest.getEmail());
        userDocument.setName(signUpRequest.getName());
        userDocument.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        userFacade.saveUserDocument(userDocument);
        UserDocument result = userRepository.save(userDocument);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getEmail()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "account registered"));
    }

    @GetMapping("/all")
    public List<UserDocument> getAll() {
        return userRepository.findAll();
    }

}
