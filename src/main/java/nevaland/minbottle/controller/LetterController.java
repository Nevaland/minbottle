package nevaland.minbottle.controller;

import nevaland.minbottle.controller.form.LetterForm;
import nevaland.minbottle.domain.Letter;
import nevaland.minbottle.service.LetterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LetterController {

    private final LetterService letterService;

    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }

    @GetMapping("/letter")
    public String letter() {
        return "letter/letter";
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

    @GetMapping("/letter/list")
    public String listLetterForm(Model model) {
        List<Letter> letters = letterService.findLetters();
        model.addAttribute("letters", letters);
        return "letter/listLetterForm";
    }

}
