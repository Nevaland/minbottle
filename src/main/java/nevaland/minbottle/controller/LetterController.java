package nevaland.minbottle.controller;

import nevaland.minbottle.controller.form.LetterForm;
import nevaland.minbottle.domain.Letter;
import nevaland.minbottle.service.LetterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LetterController {

    private final LetterService letterService;

    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }

    @GetMapping("/letter")
    public String letter(Model model) {
        Letter letter = letterService.pickLetter();
        model.addAttribute("letter", letter);
        return "letter/letter";
    }
    @PostMapping("/letter")
    @ResponseBody
    public Letter letterLoad() {
        Letter letter = letterService.pickLetter();
        return letter;
    }

    @GetMapping("/letter/new")
    public String writeLetterForm() {
        return "letter/writeLetterForm";
    }

    @PostMapping("/letter/new")
    public String writeLetter(LetterForm form) {
        Letter letter = new Letter();
        letter.setContent(form.getContent());

        letterService.write(letter);

        return "redirect:/";
    }

    @GetMapping("/letter/edit")
    public String editLetterForm() {
        return "letter/editLetterForm";
    }

    @GetMapping("/letter/delete")
    public String deleteLetterForm() {
        return "letter/deleteLetterForm";
    }

    @GetMapping("/letter/list") // Non-production
    public String listLetterForm(Model model) {
        List<Letter> letters = letterService.findLetters();
        model.addAttribute("letters", letters);
        return "letter/listLetterForm";
    }
}
