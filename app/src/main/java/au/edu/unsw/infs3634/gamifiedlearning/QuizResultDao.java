package au.edu.unsw.infs3634.gamifiedlearning;
//INFS3634 Gamified Learning App Assignment T3 2020
//Group 20
//Caitlin O'Dowd z5183007
//Lauren Bleach z5208547
//Neil Matani z5162753

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuizResultDao {

    @Query("SELECT * FROM QuizResult WHERE User == :user AND Module == :module ORDER BY result DESC")
    List<QuizResult> getResults(String user, String module);

    @Query("SELECT AVG(Result) FROM QuizResult WHERE User == :user AND Module == 'Politics'")
    int getAveragePoliticsQuizResult(String user);

    @Query("SELECT AVG(Result) FROM QuizResult WHERE User == :user AND Module == 'Pandemic'")
    int getAveragePandemicQuizResult(String user);

    @Query("SELECT AVG(Result) FROM QuizResult WHERE User == :user AND Module == 'Australia'")
    int getAverageAustraliaQuizResult(String user);

    @Query("SELECT * FROM QuizResult WHERE Module == :module ORDER BY Result DESC")
    List<QuizResult> getLeaderResult(String module);

    @Insert
    void insertQuizResult(QuizResult quizResult);
}
