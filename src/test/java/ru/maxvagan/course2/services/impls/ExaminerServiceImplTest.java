package ru.maxvagan.course2.services.impls;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.maxvagan.course2.exceptions.BadRequestException;
import ru.maxvagan.course2.services.QuestionService;
import ru.maxvagan.course2.storeclasses.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionServiceMock;
    private final List<Question> testQuestionCollection = new ArrayList<Question>();;
    private static final Random rnd = new Random();

    @BeforeEach
    void setUp() {
        testQuestionCollection.add(0, new Question("В какой директории проекта следует создавать тесты?"
                , "В src.test.java"));
        testQuestionCollection.add(1, new Question("Кубический корень из 1331?"
                , "11"));
        testQuestionCollection.add(2, new Question("Какова высота самой высокой точки Европы и России - г. Эльбрус?"
                , "5642 метра"));
    }

    @Test
    void getQuestionsTest() {
        when(questionServiceMock.getRandomQuestion()).thenReturn(testQuestionCollection.get(rnd.nextInt(3)));
        assertEquals(testQuestionCollection.get(1), questionServiceMock.getRandomQuestion());
    }

    @Test
    void getQuestionsTestBadRequestExceptionThrow() {
        when(questionServiceMock.getRandomQuestion())
                .thenThrow(BadRequestException.class);

        assertThrows(BadRequestException.class, () -> questionServiceMock.getRandomQuestion());
    }
}