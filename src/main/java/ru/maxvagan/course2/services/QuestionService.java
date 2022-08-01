package ru.maxvagan.course2.services;

import ru.maxvagan.course2.storeclasses.Question;

import java.util.Collection;

public interface QuestionService {
    Question addQuestion(String inpQ, String inpA);

    Question addQuestion(Question question);

    Question removeQuestion(Question question);

    Collection<Question> getAllQuestions();

    Question getRandomQuestion();

}
