package database.couchdb;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Quiz;
import model.QuizResult;

import java.util.List;

public class QuizResultCouchDBDAO {
    private DBAccess dbAccess;
    private Gson gson;

    public QuizResultCouchDBDAO(DBAccess dbAccess){
        super();
        this.dbAccess = dbAccess;
        gson = new Gson();
    }

//@AuthorVG - method to store QuizResults
    public String saveQuizResult(QuizResult quizResult){
        String jsonstring = gson.toJson(quizResult);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonstring).getAsJsonObject();
        String doc_Id = dbAccess.saveDocument(jsonObject);
        return doc_Id;
    }

    // VG retrieve quizresult from CouchDB
    public QuizResult getQuizResult(String doc_Id){
        JsonObject json = dbAccess.getClient().find(JsonObject.class, doc_Id);
        QuizResult result = gson.fromJson(json, QuizResult.class);
        return result;
    }

}
