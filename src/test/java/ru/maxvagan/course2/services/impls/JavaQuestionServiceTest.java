package ru.maxvagan.course2.services.impls;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.maxvagan.course2.exceptions.BadArgumentException;
import ru.maxvagan.course2.storeclasses.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private static final List<Question> testQuestions = new ArrayList<Question>();
    private static final Random rnd = new Random();
    private JavaQuestionService testService;

    private static void fillTestQuestions() {
        testQuestions.add(0, new Question("В какой директории проекта следует создавать тесты?",
                "В src.test.java"));
        testQuestions.add(1, new Question("Яркий пример логарифмической сложности?",
                "Бинарный поиск"));
        testQuestions.add(2, new Question("Как называется общий родитель всех ошибок и исключений?",
                "Throwable"));
        testQuestions.add(3, new Question("Какова высота самой высокой точки Европы и России - г. Эльбрус?",
                "5642 метра"));
        testQuestions.add(4, new Question("Кубический корень из 1331?",
                "11"));
        testQuestions.add(5, new Question("Чему равен COS() нуля?",
                "1"));
    }

    private static Stream<Arguments> getParamsForMethodsWithTwoStrings() {
        return Stream.of(Arguments.of("Чему равна длина гипотенузы у треугольника со сторонами 3 и 4?",
                        "5"),
                Arguments.of("Кубический корень из 1331?", "11"),
                Arguments.of("Чему равен COS() нуля?", "")
        );
    }

    private static Stream<Arguments> getBadParamsForTest() {
        return Stream.of(Arguments.of("", "11"),
                Arguments.of("Кубический корень из 1331?", "")
        );
    }

    private static Stream<Arguments> getParamsForMethodsWithQbjectVar() {
        fillTestQuestions();
        return Stream.of(Arguments.of(testQuestions.get(rnd.nextInt(6))),
                Arguments.of(new Question("Что такое бриллиант?", "Алмаз, прошедший процесс огранки")),
                Arguments.of(new Question("", ""))
        );
    }

    @BeforeEach
    void setUp() {
        testService = new JavaQuestionService();
        fillTestQuestions();
        testService.addQuestion(testQuestions.get(4));
        testService.addQuestion(testQuestions.get(2));
        testService.addQuestion(testQuestions.get(3));
        testService.addQuestion(testQuestions.get(0));
        testService.addQuestion(testQuestions.get(1));
    }

    @ParameterizedTest
    @MethodSource("getParamsForMethodsWithTwoStrings")
    void addQuestionWithQuestionAnswerTest(String strQuestion, String strAnswer) {
        Question testResult = new Question(strQuestion, strAnswer);
        assertEquals(testResult, testService.addQuestion(strQuestion, strAnswer));
    }

    @ParameterizedTest
    @MethodSource("getParamsForMethodsWithQbjectVar")
    void AddQuestionWithObjectTest(Question inpQ) {
        assertEquals(inpQ, testService.addQuestion(inpQ));
    }

    @ParameterizedTest
    @MethodSource("getBadParamsForTest")
    void AddQuestionWithStringsBadArgumentException(String strQuestion, String strAnswer) {
        assertThrows(BadArgumentException.class, () -> testService.addQuestion(strQuestion, strAnswer));
    }

    @ParameterizedTest
    @MethodSource("getParamsForMethodsWithQbjectVar")
    void removeQuestionWithObjectTest(Question inpQ) {
        assertDoesNotThrow(() -> testService.removeQuestion(inpQ));
    }

    @Test
    void getAllQuestionsTest() {
        assertTrue(testQuestions.containsAll(testService.getAllQuestions()));
    }

    @Test
    void getRandomQuestionTest() throws JsonProcessingException {
        System.out.println(testService.getRandomQuestion());
        assertTrue(testQuestions.contains(testService.getRandomQuestion()));
    }
}