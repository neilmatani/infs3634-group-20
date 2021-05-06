package au.edu.unsw.infs3634.gamifiedlearning;
//INFS3634 Gamified Learning App Assignment T3 2020
//Group 20
//Caitlin O'Dowd z5183007
//Lauren Bleach z5208547
//Neil Matani z5162753

import java.util.ArrayList;

public class Answer {

    private int id;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String answerCorrect;

    public Answer(int id, String answerA, String answerB, String answerC, String answerD, String answerCorrect) {
        this.id = id;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.answerCorrect = answerCorrect;
    }

    public Answer () {

    }
    //GETTERS
    public int getId() {
        return id;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    //create arrayList of answer objects
    public ArrayList<Answer> getAnswers(String title) {
        ArrayList<Answer> answers = new ArrayList<Answer>();
        if(title.equals("Pandemic")) {
            //add answers
            answers.add(new Answer(0, "Loss of Taste", "Fever", "Migranes", "Fatigue", "Migranes"));
            answers.add(new Answer(1, "Anthony Fauci", "Tedros Adhanom", "Margaret Chan", "Vikram Patel", "Tedros Adhanom"));
            answers.add(new Answer(2, "Thailand", "South Korea", "Japan", "United States", "Thailand"));
            answers.add(new Answer(3, "Boris Johnson (UK)", "Jair Bolsonaro (Brazil)", "Mikhail Mishustin (Russia)", "Pedro Sánchez (Spain)", "Pedro Sánchez (Spain)"));
            answers.add(new Answer(4, "Hydroxychloroquine", "Lopinavir/Ritonavir ", "Regeneron", "Remdesivir", "Remdesivir"));

        } else if (title.equals("Politics")) {
            //add answers
            answers.add(new Answer(0, "Bernie Sanders", "Peter Buttigieg", "Kamala Harris", "Joe Biden", "Peter Buttigieg"));
            answers.add(new Answer(1, "4", "8", "5", "9", "5"));
            answers.add(new Answer(2, "New Zealand", "Poland", "Mexico", "Argentina", "New Zealand"));
            answers.add(new Answer(3, "Secretary of State, Mike Pompeo", "White House Press Secretary, Kayleigh McEnany ", "Secretary of Treasury, Steven Mnuchin", "Secretary of Defense, Mark Esper", "Secretary of Defense, Mark Esper"));
            answers.add(new Answer(4, "Arizona", "Texas", "North Carolina", "Maryland", "Maryland"));

        } else if (title.equals("Australia")){
            //add answers
            answers.add(new Answer(0, "Lakes Entrance", "Marlo", "Mallacoota", "Bemm River", "Mallacoota"));
            answers.add(new Answer(1, "Cigarette litter", "Lightning strikes", "Arsonists", "Electrical wires", "Lightning strikes"));
            answers.add(new Answer(2, "Sydney", "Canberra", "Brisbane", "Melbourne", "Melbourne"));
            answers.add(new Answer(3, "Chinese Communist Party influence", "Investigation into human trafficking", "For tax evasion", "Leaking classified government information to the media", "Chinese Communist Party influence"));
            answers.add(new Answer(4, "$70 Billion", "$40 Billion", "$170 Billion", "$130 Billion", "$130 Billion"));
        }

        return answers;
    }
}



