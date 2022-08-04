package ru.maxvagan.course2.services.impls;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.stereotype.Service;
import ru.maxvagan.course2.exceptions.BadArgumentException;
import ru.maxvagan.course2.exceptions.QuestionNotFoundException;
import ru.maxvagan.course2.services.QuestionService;
import ru.maxvagan.course2.storeclasses.Question;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questionSet = new HashSet<>();
    private final Random rnd = new Random();
    private static final ObjectMapper objMapper = new JsonMapper();

    public static String toJsonString(Object inpObj){
        String outJson = null;
        try {
            outJson = objMapper.writeValueAsString(inpObj);
        } catch (JsonProcessingException exp) {
            exp.printStackTrace();
        }
        return outJson;
    }

    @Override
    public Question addQuestion(String inpQ, String inpA) {
        return addQuestion(new Question(inpQ, inpA));
    }

    @Override
    public Question addQuestion(Question inpQ) {
        if (!inpQ.getQuestion().isEmpty() && !inpQ.getAnswer().isEmpty()) {
            questionSet.add(inpQ);
            return inpQ;
        } else throw new BadArgumentException("Неверный входной параметр");
    }

    @Override
    public Question removeQuestion(Question inpQ) {
        if (!inpQ.getQuestion().isEmpty() && !inpQ.getAnswer().isEmpty()) {
            if (questionSet.isEmpty()) { throw new QuestionNotFoundException("Источник вопросов пуст!"); }
            else if (questionSet.contains(inpQ)) {
                questionSet.remove(inpQ);
                return inpQ;
            }
            else { throw new QuestionNotFoundException("Вопрос не найден!"); }
        } else throw new BadArgumentException("Вопрос и Ответ не могут быть пустыми");
    }

    @Override
    public Collection<Question> getAllQuestions() {
        return questionSet;
    }

    @Override
    public Question getRandomQuestion() {
        // Для нормализации (без повторов) возвращаемого Вопроса из пула (Недостаток рандомайзера Рандом)
        // было принято решение самому контролировать уникальность выбора индекса Вопроса из пула
        // путём исключения уже выбранных индексов из технического набора индексов вопросов
        int idxOfQuestion = rnd.nextInt(questionSet.size());
        return new ArrayList<Question>(questionSet).get(idxOfQuestion);
    }
}
