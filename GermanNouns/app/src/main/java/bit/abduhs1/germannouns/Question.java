package bit.abduhs1.germannouns;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Sakinah on 27/03/2016.
 */
public class Question {

    private String german;
    private String english;
    private String germanGender;
    private String gender;
    private int image;
    private Boolean correct;

    public Question() {

    }

    public Question(String german, String english, String germanGender, String gender, int image) {
        this.german = german;
        this.english = english;
        this.germanGender = germanGender;
        this.gender = gender;
        this.image = image;
        correct = false;
    }

    public String getGerman() {
        return german;
    }

    public String getEnglish() {
        return english;
    }

    public String getGermanGender(){ return germanGender; };

    public String getGender() {
        return gender;
    }

    public int getImage() {
        return image;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean bool) {
        correct = bool;
    }
}
