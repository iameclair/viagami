package co.uk.eclair.viagami.controllers;
import co.uk.eclair.viagami.documents.UserDocument;
import co.uk.eclair.viagami.facades.UserFacade;
import co.uk.eclair.viagami.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ${Eclair} on 8/19/2018.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserFacade userFacade;

    public UserController(UserRepository userRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          UserFacade userFacade) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userFacade = userFacade;
    }

    @PostMapping("/sign-up")
    public void signup(@RequestBody UserDocument userDocument){
        userDocument.setPassword(bCryptPasswordEncoder.encode(userDocument.getPassword()));
        userFacade.saveUserDocument(userDocument);
        userRepository.save(userDocument);
    }

    @GetMapping("/all")
    public List<UserDocument> getAll() {
        return userRepository.findAll();
    }

}
