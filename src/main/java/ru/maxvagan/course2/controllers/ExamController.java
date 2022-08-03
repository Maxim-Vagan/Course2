package ru.maxvagan.course2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.maxvagan.course2.services.ExaminerService;
import ru.maxvagan.course2.services.impls.JavaQuestionService;

import java.util.List;
import java.util.stream.Collectors;

import static ru.maxvagan.course2.services.impls.JavaQuestionService.toJsonString;

@RestController
@RequestMapping(path = "/exam")
public class ExamController {
    private final ExaminerService examiner;

    public ExamController(ExaminerService examiner) {
        this.examiner = examiner;
    }

    @GetMapping(path = "/get")
    public List<String> getQuestions(@RequestParam(value = "amount") int inpAmountOfQuestion) {
        return examiner.getQuestions(inpAmountOfQuestion).stream()
                .map(JavaQuestionService::toJsonString)
                .collect(Collectors.toList());
    }
}
