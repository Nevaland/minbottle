package nevaland.minbottle.service;

import nevaland.minbottle.domain.Letter;
import nevaland.minbottle.repository.MemoryLetterRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
    public void afterEach() {
        memoryLetterRepository.clearStore();
    }

    @Test
    public void writeLetter() {
        //given
        Letter letter = new Letter();
        letter.setContent("letter test content");

        //when
        Long saveId = letterService.write(letter);

        //then
        Letter findLetter = letterService.findOne(saveId).get();
        assertThat(letter.getContent()).isEqualTo(findLetter.getContent());
    }

    @Test
    public void pickLetter_one() {
        //given
        Letter letter = new Letter();
        letter.setContent("letter test content");
        letterService.write(letter);

        //when
        Letter pickedLetter = letterService.pickLetter();

        //then
        assertThat(letter.getContent()).isEqualTo(pickedLetter.getContent());
    }

    @Test
    public void pickLetter_many() {
        //given
        Letter letter1 = new Letter();
        Letter letter2 = new Letter();
        Letter letter3 = new Letter();
        letter1.setContent("letter test content 1");
        letter2.setContent("letter test content 2");
        letter3.setContent("letter test content 3");
        letterService.write(letter1);
        letterService.write(letter2);
        letterService.write(letter3);

        //when
        Letter pickedLetter = letterService.pickLetter();

        //then
        List<String> letterContentList = new ArrayList<>();
        letterContentList.add(letter1.getContent());
        letterContentList.add(letter2.getContent());
        letterContentList.add(letter3.getContent());
        assertThat(letterContentList).contains(pickedLetter.getContent());
    }
}