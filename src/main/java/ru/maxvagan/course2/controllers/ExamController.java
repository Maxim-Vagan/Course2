package ru.maxvagan.course2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.maxvagan.course2.services.ExaminerService;

import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/exam")
public class ExamController {
    private final ExaminerService examiner;

    public ExamController(ExaminerService examiner) {
        this.examiner = examiner;
    }

    @GetMapping(path = "/get", params = "amount")
    public String getQuestions(@RequestParam("amount") int inpAmountOfQuestion) {
        return examiner.getQuestions(inpAmountOfQuestion).stream()
                .map(e -> "<tr><h3>" + e.getQuestion() + "</h3></tr>")
                .collect(Collectors.joining(""));
    }
}
