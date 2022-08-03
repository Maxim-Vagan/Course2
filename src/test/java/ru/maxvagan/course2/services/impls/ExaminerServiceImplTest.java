package ru.maxvagan.course2.services.impls;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.maxvagan.course2.exceptions.BadRequestException;
import ru.maxvagan.course2.storeclasses.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService questionServiceMock;
    @InjectMocks
    private ExaminerServiceImpl examService;

    private final List<Question> testQuestionCollection = new ArrayList<Question>();

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
        when(questionServiceMock.getAllQuestions()).thenReturn(testQuestionCollection);
        when(questionServiceMock.getRandomQuestion())
                .thenReturn(testQuestionCollection.get(2),
                        testQuestionCollection.get(1),
                        testQuestionCollection.get(0));
        assertEquals(testQuestionCollection, examService.getQuestions(3));
    }

    @Test
    void getQuestionsTestBadRequestExceptionThrow() {
        when(questionServiceMock.getAllQuestions()).thenReturn(testQuestionCollection);
        assertThrows(BadRequestException.class, () -> examService.getQuestions(4));
    }
}