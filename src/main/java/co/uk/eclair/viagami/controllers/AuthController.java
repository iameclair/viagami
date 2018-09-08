package co.uk.eclair.viagami.controllers;
import co.uk.eclair.viagami.documents.PasswordResetToken;
import co.uk.eclair.viagami.documents.UserDocument;
import co.uk.eclair.viagami.exception.UserNotFoundException;
import co.uk.eclair.viagami.facades.UserFacade;
import co.uk.eclair.viagami.payload.ApiResponse;
import co.uk.eclair.viagami.payload.JWTAuthenticationResponse;
import co.uk.eclair.viagami.payload.LoginRequest;
import co.uk.eclair.viagami.payload.SignUpRequest;
import co.uk.eclair.viagami.repositories.PasswordTokenRepository;
import co.uk.eclair.viagami.repositories.UserRepository;
import co.uk.eclair.viagami.security.JWTTokenProvider;
import co.uk.eclair.viagami.services.EmailServiceImpl;
import co.uk.eclair.viagami.services.PasswordResetServiceImpl;
import co.uk.eclair.viagami.services.UserDetailsServiceImpl;
import co.uk.eclair.viagami.utilities.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordResetServiceImpl passwordResetService;
    @Autowired
    private PasswordTokenRepository passwordTokenRepository;
    @Autowired
    private EmailServiceImpl emailService;

    private static final String FROM = "viagamiteam@gmail.com";
    private static final String SUBJECT_PASSWORD_RESET = "Reset Your Password";

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

    @PostMapping("/getemail")
    public ResponseEntity<?> getEmailFromUser(@RequestBody String email){
        Optional<UserDocument> userDocument = userRepository.findByEmail(email);
        userDocument.orElseThrow(() -> new UsernameNotFoundException("user not found exception"));
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = passwordResetService.createPasswordResetTokenForUser(userDocument.get(), token);
        PasswordResetToken result =  passwordTokenRepository.save(passwordResetToken);
        //send email using the email service
        Mail mail = new Mail(FROM, userDocument.get().getEmail(), SUBJECT_PASSWORD_RESET, passwordResetToken.getToken());
        emailService.sendMessage(mail);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/resetpassword")
    public ResponseEntity<?> resetPassword(HttpServletRequest request, @RequestBody String email){

        //return response
        return null;
    }
    @GetMapping("/all")
    public List<UserDocument> getAll() {
        return userRepository.findAll();
    }

}
