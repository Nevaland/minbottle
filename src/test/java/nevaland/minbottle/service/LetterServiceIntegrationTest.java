package nevaland.minbottle.service;

import nevaland.minbottle.domain.Letter;
import nevaland.minbottle.repository.LetterRepository;
import nevaland.minbottle.repository.MemoryLetterRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class LetterServiceIntegrationTest {

    @Autowired LetterService letterService;
    @Autowired LetterRepository letterRepository;

    @Test
    public void write() {
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