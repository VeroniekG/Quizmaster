package model;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.*;

@DisplayName("Tests for class User")
class UserTest implements WithAssertions {

    private static final int ID_USER = 99;
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String USER_NAME = "userName";
    private static final String PASSWORD = "password";
    private static final Role ROLE = Role.STUDENT;


    @Nested
    @DisplayName("Tests for method toString()")
    class TestsForToString {

        @Test
        @DisplayName("If userName is set, return value")
        void Should_ReturnUserName_When_UserNameIsSet() {
            User underTest = new User(ID_USER, FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ROLE);
            assertThat(underTest.toString()).isEqualTo(USER_NAME);
        }

        @Test
        @DisplayName("If userName is not set, return null")
        void Should_ReturnNull_When_UserNameIsNotSet() {
            User underTest = new User(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ROLE);
            System.out.println(underTest.toString());
            assertThat(underTest.toString()).isEqualTo(null);
        }

    }

    @Nested
    @DisplayName("Tests for method equals(Object o)")
    class TestsForEquals {

        @Test
        @DisplayName("if (this == o), return true")
        void Should_ReturnTrue_When_ObjectsHaveSameReference() {
            User underTest = new User(ID_USER, FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ROLE);
            User isEqual = underTest;
            assertThat(underTest.equals(isEqual)).isEqualTo(true);
        }

        @Test
        @DisplayName("if o == null, return false")
        void Should_ReturnFalse_When_ObjectComparedIsNull() {
            User underTest = new User(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ROLE);
            User o = null;
            assertThat(underTest.equals(o)).isEqualTo(false);
        }

        @Test
        @DisplayName("If classes differ, return false")
        void Should_ReturnFalse_When_ObjectsHaveDifferentClasses() {
            User underTest = new User(ID_USER, FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ROLE);
            Object o = new Object();
            assertThat(underTest.equals(o)).isEqualTo(false);
        }

        @Test
        @DisplayName("If attributes are equal, return true")
        void Should_ReturnTrue_When_AttributesAreEqual() {
            User underTestThis = new User(ID_USER, FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ROLE);
            User underTestThat = new User(ID_USER, FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ROLE);
            assertThat(underTestThis.equals(underTestThat)).isEqualTo(true);
        }

    }

    @Nested
    @DisplayName("Tests for method hashCode()")
    class TestsForHashCode {

        @Test
        @DisplayName("If x.equals(y) && x.hashCode() == y.hashCode()")
        void Should_ReturnTrue_When_ObjectsAreEqualAndHashCodesAreEqual() {
            User underTestThis = new User(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ROLE);
            User underTestThat = new User(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ROLE);
            assertThat(underTestThis.equals(underTestThat)).isEqualTo(true);
            assertThat(underTestThis.hashCode() == underTestThat.hashCode());
        }

    }


}