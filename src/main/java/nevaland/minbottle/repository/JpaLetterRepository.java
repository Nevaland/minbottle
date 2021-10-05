package nevaland.minbottle.repository;

import nevaland.minbottle.domain.Letter;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaLetterRepository implements LetterRepository {

    private final EntityManager em;

    public JpaLetterRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Letter save(Letter letter) {
        em.persist(letter);
        return letter;
    }

    @Override
    public Optional<Letter> findById(Long id) {
        Letter letter = em.find(Letter.class, id);
        return Optional.ofNullable(letter);
    }

    @Override
    public List<Letter> findAll() {
        return em.createQuery("select l from Letter l", Letter.class).getResultList();
    }

    @Override
    public Letter pick() {  // TODO: To be Optional, Have to Control empty letters
        List<Letter> letters = findAll();
        int randomIndex = (int)((Math.random() * 10000) % letters.size());
        return letters.get(randomIndex);
    }
}
