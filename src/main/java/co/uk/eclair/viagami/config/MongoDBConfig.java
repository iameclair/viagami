package co.uk.eclair.viagami.config;

import co.uk.eclair.viagami.documents.User;
import co.uk.eclair.viagami.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Date;

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
                userRepository.save(new User(10L, "Eclair", new Date() ));
                userRepository.save(new User(12L, "Pateli", new Date() ));
            }
        };
    }
}
