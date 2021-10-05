package nevaland.minbottle.repository;

import nevaland.minbottle.domain.Letter;

import java.util.*;

public class MemoryLetterRepository implements LetterRepository {

    private static Map<Long, Letter> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Letter save(Letter letter) {
        letter.setId(++sequence);
        store.put(letter.getId(), letter);
        return letter;
    }

    @Override
    public Optional<Letter> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Letter> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Letter pick() {
        List<Letter> letters = findAll();
        int randomIndex = (int)((Math.random() * 10000) % letters.size());
        return letters.get(randomIndex);
    }

    public void clearStore() {
        store.clear();
    }
}
