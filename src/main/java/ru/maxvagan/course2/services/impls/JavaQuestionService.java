package ru.maxvagan.course2.services.impls;

import org.springframework.stereotype.Service;
import ru.maxvagan.course2.exceptions.BadArgumentException;
import ru.maxvagan.course2.exceptions.QuestionNotFoundException;
import ru.maxvagan.course2.services.QuestionService;
import ru.maxvagan.course2.storeclasses.Question;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questionSet = new HashSet<>();

    @Override
    public Question addQuestion(String inpQ, String inpA) {
        if (inpQ != null && inpA != null) {
            Question questionInstance = new Question(inpQ, inpA);
            questionSet.add(questionInstance);
            if (questionSet.contains(questionInstance)) return questionInstance;
            else return null;
        } else throw new BadArgumentException("Неверный входной параметр");
    }

    @Override
    public Question addQuestion(Question inpQ) {
        if (inpQ != null) {
            questionSet.add(inpQ);
            if (questionSet.contains(inpQ)) return inpQ;
            else return null;
        } else throw new BadArgumentException("Неверный входной параметр");
    }

    @Override
    public Question removeQuestion(Question inpQ) {
        if (inpQ != null) {
            if (questionSet.isEmpty()) throw new QuestionNotFoundException("Источник вопросов пуст!");
            Optional<Question> questionFromSet = questionSet.stream()
                    .filter(e -> e.equals(inpQ))
                    .findFirst();
            if (questionFromSet.isPresent()) questionSet.remove(questionFromSet);
            return questionFromSet.orElseThrow(() -> new QuestionNotFoundException("Вопрос не найден!"));
        } else throw new BadArgumentException("Вопрос не может быть пустым");
    }

    @Override
    public Collection<Question> getAllQuestions() {
        return questionSet;
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int idxOfQuestion = random.nextInt(questionSet.size());
        return (Question) questionSet.toArray()[idxOfQuestion];
    }
}
