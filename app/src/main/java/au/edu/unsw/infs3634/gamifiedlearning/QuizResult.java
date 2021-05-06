package au.edu.unsw.infs3634.gamifiedlearning;
//INFS3634 Gamified Learning App Assignment T3 2020
//Group 20
//Caitlin O'Dowd z5183007
//Lauren Bleach z5208547
//Neil Matani z5162753

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity
public class QuizResult {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int quizResultId;
    @SerializedName("User")
    @Expose
    @NonNull
    private String user;
    @SerializedName("Module")
    @Expose
    @NonNull
    private String module;
    @SerializedName("Result")
    @Expose
    @NonNull
    private int result;
    @SerializedName("Date")
    @Expose
    @NonNull
    private String resultDate;

    public QuizResult (String user, String module, int result, String resultDate) {
        this.user = user;
        this.module = module;
        this.result = result;
        this.resultDate = resultDate;
    }

    //SETTERS
    public void setQuizResultId(int quizResultId) {
        this.quizResultId = quizResultId;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setResultDate(String resultDate) {
        this.resultDate = resultDate;
    }

    //GETTERS
    public int getQuizResultId() {
        return this.quizResultId;
    }

    public String getUser() {
        return user;
    }

    public String getModule() {
        return module;
    }

    public int getResult() {
        return result;
    }

    public String getResultDate() {
        return resultDate;
    }


}
