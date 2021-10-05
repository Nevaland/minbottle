package nevaland.minbottle.repository;

import nevaland.minbottle.domain.Letter;

import java.util.List;
import java.util.Optional;

public interface LetterRepository {
    Letter save(Letter letter);
    Optional<Letter> findById(Long id);
    List<Letter> findAll();
    Letter pick();
}
