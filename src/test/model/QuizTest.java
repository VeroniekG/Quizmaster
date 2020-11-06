package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {


    @Test
    void testToString() {
        Quiz toStringTest = new Quiz("finalcountdown",15);
        assertEquals("finalcountdown", toStringTest.toString());
    }

    @Test
    void getIdQuiz() {
    }

    @Test
    void setIdQuiz() {
    }

    @Test
    void getQuizName() {
    }

    @Test
    void setQuizName() {
    }
}