package model;

import controller.FillOutQuizController;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import database.mysql.UserDAO;
import model.Question;
import model.Quiz;
import view.Main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

//@AuthorVG - In QuizResult we store: Username, quizname, datum, answersCorrect and answers given by Student.
public class QuizResult {

    private User currentUser;
    private QuizDAO quizName;
    private int date;
    private String antwoordCorrect1 = "Amsterdam";
    private String antwoordCorrect2 = "London";
    private String gegevenAntwoord1 = "Parijs";
    private String gegevenAntwoord2 = "Berlijn";

//lijst met vragen en correcte antwoord
    // arraylist van Filloutquiz

    public ArrayList<String> getAnswersCorrect(){
        ArrayList<String> correcteAntwoorden = new ArrayList<String>();
        correcteAntwoorden.add(antwoordCorrect1);
        correcteAntwoorden.add(antwoordCorrect2);
        return correcteAntwoorden;
    }
    public ArrayList<String> getAnswersGiven() {
        ArrayList<String> gegevenAntwoorden = new ArrayList<String>();
        gegevenAntwoorden.add(gegevenAntwoord1);
        gegevenAntwoorden.add(gegevenAntwoord2);
        return gegevenAntwoorden;

    }

}

