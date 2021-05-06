package au.edu.unsw.infs3634.gamifiedlearning;
//INFS3634 Gamified Learning App Assignment T3 2020
//Group 20
//Caitlin O'Dowd z5183007
//Lauren Bleach z5208547
//Neil Matani z5162753

import java.util.ArrayList;

public class Module {

    private String moduleId;
    private String title;
    private String description;
    private int progressBar;
    private int image;

    public Module () {

    }

    public Module (String moduleId, String title, String description, int progressBar, int image) {
        this.moduleId = moduleId;
        this.title = title;
        this.description = description;
        this.progressBar = progressBar;
        this.image = image;
    }

    //setter methods


    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProgressBar(int progressBar) {
        this.progressBar = progressBar;
    }

    public void setImage(int image) {
        this.image = image;
    }

    //getter methods

    public String getModuleId() {
        return moduleId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getProgressBar() {
        return progressBar;
    }

    public int getImage() {
        return image;
    }



    //return array of module
    public static ArrayList<Module> returnModuleArray () {
        ArrayList<Module> moduleArrayList = new ArrayList<Module>();
        moduleArrayList.add(new Module("pol","Politics", "In this module, we'll be delving into the geopolitical landscape of 2020. Marked by one of the most historically significant and polarizing US elections, 2020 marked a important turning point for politics.", 0, R.drawable.politics));
        moduleArrayList.add(new Module("pan","Pandemic", "The Pandemic module offers an insight into the COVID-19 virus on the modern world. The functionality of world economies and societies were brought to a standstill, causing lasting effects.", 0, R.drawable.pandemic));
        moduleArrayList.add(new Module("aus","Australia", "How did the world events of 2020 affect citizens domestically? The Australia module takes a dive into the Australian experience of 2020, one of the tumultuous years on record for our country.", 0, R.drawable.australia));
        return moduleArrayList;
    }
}

