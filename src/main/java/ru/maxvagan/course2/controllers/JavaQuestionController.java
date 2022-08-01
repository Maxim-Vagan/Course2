package ru.maxvagan.course2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.maxvagan.course2.services.QuestionService;
import ru.maxvagan.course2.storeclasses.Question;

import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/exam/java")
public class JavaQuestionController {
    private final QuestionService service;

    public JavaQuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping
    public String getQuestions() {
        return service.getAllQuestions().stream()
                .map(e -> "<tr><h3>" + e.getQuestion() + "</h3></tr>")
                .collect(Collectors.joining(""));
    }

    @GetMapping(path = "/add", params = {"question", "answer"})
    public String addQuestion(@RequestParam("question") String inpQuestion
            , @RequestParam("answer") String inpAnswer) {
        return service.addQuestion(inpQuestion, inpAnswer).toString();
    }

    @GetMapping(path = "/remove", params = {"question", "answer"})
    public String removeQuestion(@RequestParam("question") String inpQuestion
            , @RequestParam("answer") String inpAnswer) {
        return service.removeQuestion(new Question(inpQuestion, inpAnswer)).toString();
    }
}
