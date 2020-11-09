package nevaland.minbottle;

import nevaland.minbottle.repository.JpaLetterRepository;
import nevaland.minbottle.repository.LetterRepository;
import nevaland.minbottle.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public LetterService letterService() {
        return new LetterService(letterRepository());
    }

    @Bean
    public LetterRepository letterRepository() {
        return new JpaLetterRepository(em);
    }
}
