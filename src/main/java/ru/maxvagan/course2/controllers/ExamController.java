package ru.maxvagan.course2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.maxvagan.course2.services.ExaminerService;
import ru.maxvagan.course2.storeclasses.Question;

import java.util.Collection;

@RestController
@RequestMapping(path = "/exam")
public class ExamController {
    private final ExaminerService examiner;

    public ExamController(ExaminerService examiner) {
        this.examiner = examiner;
    }

    @GetMapping(path = "/get")
    public Collection<Question> getQuestions(@RequestParam(value = "amount") int inpAmountOfQuestion) {
        return examiner.getQuestions(inpAmountOfQuestion);
    }
}
