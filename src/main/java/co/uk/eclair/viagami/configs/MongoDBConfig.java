package co.uk.eclair.viagami.configs;

import co.uk.eclair.viagami.documents.Role;
import co.uk.eclair.viagami.documents.RoleType;
import co.uk.eclair.viagami.repositories.RoleRepository;
import co.uk.eclair.viagami.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by ${Eclair} on 8/19/2018.
 */
@Configuration
public class MongoDBConfig {
   /* @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Role admin = new Role(0, RoleType.ADMIN);
                Role voyageur = new Role(1, RoleType.VOYAGEUR);
                Role premium_voyageur = new Role(2, RoleType.PREMIUM_VOYAGEUR);

                roleRepository.save(admin);
                roleRepository.save(voyageur);
                roleRepository.save(premium_voyageur);
            }
        };
    }*/
}
