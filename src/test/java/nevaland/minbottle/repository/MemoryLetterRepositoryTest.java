package nevaland.minbottle.repository;

import nevaland.minbottle.domain.Letter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryLetterRepositoryTest {

    MemoryLetterRepository repository = new MemoryLetterRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        //given
        Letter letter = new Letter();
        letter.setContent("test");

        //when
        repository.save(letter);

        //then
        Letter findLetter = repository.findById(letter.getId()).get();
        assertThat(letter.getContent()).isEqualTo(findLetter.getContent());
    }

}