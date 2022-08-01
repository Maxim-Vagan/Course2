package ru.maxvagan.course2.services.impls;

import org.springframework.stereotype.Service;
import ru.maxvagan.course2.exceptions.BadRequestException;
import ru.maxvagan.course2.services.ExaminerService;
import ru.maxvagan.course2.services.QuestionService;
import ru.maxvagan.course2.storeclasses.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;
    private final Random random = new Random();

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getAllQuestions().size() || amount <= 0) throw new BadRequestException("BAD_REQUEST");
        Set<Question> setOfExamQuestions = new HashSet<>();
        for (int i=1;i<=amount;i++) {
            setOfExamQuestions.add(questionService.getRandomQuestion());
        }
        return setOfExamQuestions;
    }
}
