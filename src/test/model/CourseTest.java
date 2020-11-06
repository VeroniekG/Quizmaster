package model;


import model.Course;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void testToString() {
        Course toStringTest = new Course("Excel-lent", 4);
        assertEquals("Excel-lent", toStringTest.toString());
    }
    @Nested
    @DisplayName("Testen getters")
    class testGetters {
        @Test
        @DisplayName("Test getIdCourse")
        void getIdCourse() {
            Course course = new Course(7, "Skilzz", 7);
            assertEquals(7, course.getIdCourse());
        }
    }

}