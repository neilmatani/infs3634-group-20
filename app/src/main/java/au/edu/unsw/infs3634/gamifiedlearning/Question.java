package au.edu.unsw.infs3634.gamifiedlearning;
//INFS3634 Gamified Learning App Assignment T3 2020
//Group 20
//Caitlin O'Dowd z5183007
//Lauren Bleach z5208547
//Neil Matani z5162753


import java.util.ArrayList;

public class Question {

    private int questionID;
    private String questionText;

    public Question(int questionID, String questionText) {
        this.questionID = questionID;
        this.questionText = questionText;
    }

    public Question() {

    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    //Returns array of questions as per module
    public ArrayList<Question> getQuestions(String title) {
        ArrayList<Question> questions = new ArrayList<Question>();
        if(title.equals("Pandemic")) {
            //add questions
            questions.add(new Question (0, "Which of these is NOT a symptom of COVID-19?"));
            questions.add(new Question (1, "Who was the World Health Organization Director-General during the pandemic?"));
            questions.add(new Question (2, "Which country reported the first COVID-19 case outside China?"));
            questions.add(new Question (3, "Which of these world leaders did NOT contract COVID-19?"));
            questions.add(new Question (4, "Which was the first treatment to be granted emergency use authorization by the U.S. Food and Drug Administration (FDA)?"));


        } else if (title.equals("Politics")) {
            //add questions
            questions.add(new Question (0, "Who won the Iowa caucus in the Democratic Primaries?"));
            questions.add(new Question (1, "How many days after election day did it take for Joe Biden to be declared winner of the US election?"));
            questions.add(new Question (2, "Which country other than the U.S. had general leadership elections held in 2020?"));
            questions.add(new Question (3, "Who from his own cabinet did President Trump fire after the election?"));
            questions.add(new Question (4, "Which of these states was NOT considered a battleground state in the US Presidential Election?"));

        } else {
            //add questions
            questions.add(new Question (0, "Which Victorian town required Army Reserve brigades to help evacuate civilians from the bushfires?"));
            questions.add(new Question (1, "What was considered the major cause of ignition of the bushfires?"));
            questions.add(new Question (2, "Which city reported the first COVID-19 case in Australia?"));
            questions.add(new Question (3, "Why was a NSW Labor MP raided by Australian Federal Police (AFP) in June?"));
            questions.add(new Question (4, "How much was the Morrison Government's wage subsidy program, JobKeeper worth?"));

        }

        return questions;
    }


}
