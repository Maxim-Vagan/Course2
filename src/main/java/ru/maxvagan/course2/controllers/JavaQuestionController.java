package ru.maxvagan.course2.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.maxvagan.course2.services.QuestionService;
import ru.maxvagan.course2.services.impls.JavaQuestionService;
import ru.maxvagan.course2.storeclasses.Question;

import java.util.List;
import java.util.stream.Collectors;
import static ru.maxvagan.course2.services.impls.JavaQuestionService.toJsonString;

@RestController
@RequestMapping(path = "/exam/java")
public class JavaQuestionController {
    private final QuestionService service;

    public JavaQuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping
    public List<String> getQuestions() {
        return service.getAllQuestions().stream()
                .map(JavaQuestionService::toJsonString)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/add")
    public String addQuestion(@RequestParam(value = "question", required = false) String inpQuestion
            , @RequestParam(value = "answer", required = false) String inpAnswer){
        return toJsonString(service.addQuestion(inpQuestion, inpAnswer));
    }

    @GetMapping(path = "/remove")
    public String removeQuestion(@RequestParam(value = "question", required = false) String inpQuestion
            , @RequestParam(value = "answer", required = false) String inpAnswer) {
        return toJsonString(service.removeQuestion(new Question(inpQuestion, inpAnswer)));
    }
}
