package com.site;

import com.site.model.Actor;
import com.site.repo.ActorRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initializeData(ActorRepo actorRepo) {
        return args -> {
            if (actorRepo.count() == 0) {
                actorRepo.save(new Actor(null, "Tom Hanks", "Forrest Gump"));
                actorRepo.save(new Actor(null, "Emma Watson", "Harry Potter"));
                actorRepo.save(new Actor(null, "Leonardo DiCaprio", "Titanic"));
            }
        };
    }
}