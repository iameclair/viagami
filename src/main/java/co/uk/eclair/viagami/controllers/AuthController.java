package co.uk.eclair.viagami.controllers;
import co.uk.eclair.viagami.documents.PasswordResetToken;
import co.uk.eclair.viagami.documents.UserDocument;
import co.uk.eclair.viagami.facades.UserFacade;
import co.uk.eclair.viagami.payload.*;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
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

    private static final String FROM = "eclairlumu@gmail.com";
    private static final String SUBJECT_PASSWORD_RESET = "Reset Your Password";

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  loginRequestDTO.getEmail(),
                  loginRequestDTO.getPassword()
          )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthenticationResponseDTO(jwt));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO){
        if(userRepository.existsByEmail(signUpRequestDTO.getEmail())){
            return new ResponseEntity<>(new ApiResponseDTO(false, "Email is already exist!"),
                    HttpStatus.BAD_REQUEST);
        }
        UserDocument userDocument = new UserDocument();
        userDocument.setEmail(signUpRequestDTO.getEmail());
        userDocument.setName(signUpRequestDTO.getName());
        userDocument.setPassword(bCryptPasswordEncoder.encode(signUpRequestDTO.getPassword()));
        userFacade.saveUserDocument(userDocument);
        UserDocument result = userRepository.save(userDocument);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getEmail()).toUri();

        return ResponseEntity.created(location).body(new ApiResponseDTO(true, "account registered"));
    }

    @PostMapping("/getemail")
    public ResponseEntity<?> getEmailFromUser(@Valid @RequestBody EmailRequestDTO email){
        Optional<UserDocument> userDocument = userRepository.findByEmail(email.getEmail());
        userDocument.orElseThrow(() -> new UsernameNotFoundException("user not found exception"));
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = passwordResetService.createPasswordResetTokenForUser(userDocument.get(), token);
        PasswordResetToken result =  passwordTokenRepository.save(passwordResetToken);
        //send email using the email service
        Mail mail = new Mail(FROM, userDocument.get().getEmail(), SUBJECT_PASSWORD_RESET, passwordResetToken.getToken());
        emailService.sendMessage(mail);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/send/{token}")
    public ResponseEntity<?> sendToken(@PathVariable("token") String token){
        Optional<PasswordResetToken> passwordResetToken = passwordTokenRepository.findByToken(token);
        final PasswordResetTokenResponseDTO result = new PasswordResetTokenResponseDTO();
        if (passwordResetToken.isPresent()) {
            Long diff = passwordResetToken.get().getExpiryDate().getTime() - new Date().getTime();
            if(diff > 0){
                result.setTokenIsValid(true);
                result.setTokenIsPresent(true);
                result.setToken(token);
                return ResponseEntity.ok(result);
            }
        }else{
            result.setTokenIsPresent(false);
            result.setTokenIsValid(false);
            result.setToken(null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/resetpassword/{token}")
    public ResponseEntity<?> resetPassword(@PathVariable("token") String token, @RequestBody ResetPasswordRequestDTO resetPassword){
        //find the user
        //change password
        //return response
        return null;
    }
    @GetMapping("/all")
    public List<UserDocument> getAll() {
        return userRepository.findAll();
    }

}
