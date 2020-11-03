package model;

import controller.FillOutQuizController;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import database.mysql.UserDAO;
import model.Question;
import model.Quiz;
import view.Main;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@AuthorVG - In QuizResult we store: Username, quizname, datum, answersCorrect and answers given by Student.
public class QuizResult {

    private FillOutQuizController quizController;
    User loggedUser;
    private QuizDAO quizName;
    LocalDateTime dateQuiz;

    public void populateDB(){


    }

     //lijst met vragen en correcte antwoord
    // arraylist van Filloutquiz



    }



