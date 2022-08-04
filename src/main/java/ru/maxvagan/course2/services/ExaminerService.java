package ru.maxvagan.course2.services;

import ru.maxvagan.course2.storeclasses.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
