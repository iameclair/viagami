package co.uk.eclair.viagami.config;

import co.uk.eclair.viagami.documents.Role;
import co.uk.eclair.viagami.documents.RoleType;
import co.uk.eclair.viagami.documents.User;
import co.uk.eclair.viagami.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ${Eclair} on 8/19/2018.
 */
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class MongoDBConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return new CommandLineRunner(){
            @Override
            public void run(String... strings) throws Exception{
                Set<Role> eclairsRole = new HashSet<>();
                eclairsRole.add(new Role(1, RoleType.ADMIN));
                eclairsRole.add(new Role(2, RoleType.PREMIUM_VOYAGEUR));
                Set<Role> ioannasRole = new HashSet<>();
                ioannasRole.add(new Role(3, RoleType.VOYAGEUR));

                User eclair = new User();
                eclair.setRoles(eclairsRole);
                eclair.setCreatedDate(new Date());
                eclair.setName("Eclair Lumu");
                eclair.setId(13L);
                eclair.setEmail("eclairlumu@gmail.com");

                User pateli = new User();
                pateli.setRoles(ioannasRole);
                pateli.setCreatedDate(new Date());
                pateli.setName("Ioanna Pateli");
                pateli.setId(14L);
                eclair.setEmail("pateli@gmail.com");

                userRepository.save(eclair);
                userRepository.save(pateli);
            }
        };
    }
}
