package ru.maxvagan.course2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.maxvagan.course2.services.QuestionService;
import ru.maxvagan.course2.storeclasses.Question;

import java.util.Collection;

@RestController
@RequestMapping(path = "/exam/java")
public class JavaQuestionController {
    private final QuestionService service;

    public JavaQuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<Question> getQuestions() {
        return service.getAllQuestions();
    }

    @GetMapping(path = "/add")
    public Question addQuestion(@RequestParam(value = "question", required = false) String inpQuestion
            , @RequestParam(value = "answer", required = false) String inpAnswer){
        return service.addQuestion(inpQuestion, inpAnswer);
    }

    @GetMapping(path = "/remove")
    public Question removeQuestion(@RequestParam(value = "question", required = false) String inpQuestion
            , @RequestParam(value = "answer", required = false) String inpAnswer) {
        return service.removeQuestion(new Question(inpQuestion, inpAnswer));
    }
}
