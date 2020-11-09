package nevaland.minbottle.service;

import nevaland.minbottle.domain.Letter;
import nevaland.minbottle.repository.MemoryLetterRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LetterServiceTest {

    MemoryLetterRepository memoryLetterRepository;
    LetterService letterService;

    @BeforeEach
    public void beforeEach() {
        memoryLetterRepository = new MemoryLetterRepository();
        letterService = new LetterService(memoryLetterRepository);
    }

    @AfterEach
    void afterEach() {
        memoryLetterRepository.clearStore();
    }

    @Test
    void write() {
        //given
        Letter letter = new Letter();
        letter.setContent("test");

        //when
        Long saveId = letterService.write(letter);

        //then
        Letter findLetter = letterService.findOne(saveId).get();
        assertThat(letter.getContent()).isEqualTo(findLetter.getContent());
    }
}