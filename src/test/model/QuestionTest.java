package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Nested
    @DisplayName("Testen van een aantal getters")
    class testGetters {
        @Test
        @DisplayName("Laat zien of de getter van idQuiz werkt")
        void getIdQuiz() {
            Quiz u = new Quiz(3, "Blokken", 3);
            assertEquals(3, u.getIdQuiz());
        }
        @Test
        @DisplayName("Laat zien of de getter van idQuestion werkt")
        void getIdQuestion() {
            Question q = new Question(1, "Wat is 1+1?", "2", "15", "166", "345", 0);
            assertEquals(1, q.getIdQuestion());
            //om zeker te zijn dat de tests werken heb ik een test toegevoegd die altijd faalt:
//        assertEquals(43, 234);
        }
        @Test
        @DisplayName("Laat zien of de getter van description werkt")
        void getDescription() {
            Question vraag = new Question(5, "Wat is 60/2?", "30", "545", "6", "23", 0);
            assertEquals("Wat is 60/2?", vraag.getDescription());
            assertEquals("30", vraag.getAnswerRight());
        }
    }
    @Test
    @DisplayName("Laat zien of de toString methode van object Question werkt")
    void testToString() {
        Question toStringTest = new Question("Wat is 1+1?", "2", "15", "166", "345");
        assertEquals("Wat is 1+1?", toStringTest.toString());
    }
}