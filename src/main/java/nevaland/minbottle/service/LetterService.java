package nevaland.minbottle.service;

import nevaland.minbottle.domain.Letter;
import nevaland.minbottle.repository.LetterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class LetterService {

    private final LetterRepository letterRepository;

    @Autowired
    public LetterService(LetterRepository letterRepository) {
        this.letterRepository = letterRepository;
    }

    /**
     * Write Letter
     */
    public Long write(Letter letter) {
        letterRepository.save(letter);
        return letter.getId();
    }

    /**
     * Find Letter
     */
    public List<Letter> findLetters() {
        return letterRepository.findAll();
    }

    public Optional<Letter> findOne(Long letterId) {
        return letterRepository.findById(letterId);
    }
}
