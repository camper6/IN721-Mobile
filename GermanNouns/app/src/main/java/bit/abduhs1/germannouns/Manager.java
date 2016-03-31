package bit.abduhs1.germannouns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Sakinah on 27/03/2016.
 */

public class Manager {

    private ArrayList<Question> quiz = new ArrayList<Question>();
    private int questionIndex;
    //private Boolean lastQuestion;
    private static final String masculine = "Masculine";
    private static final String neutral = "Neutral";
    private static final String feminine = "Feminine";
    private static final String der = "Der";
    private static final String das = "Das";
    private static final String die = "Die";

    public Manager() {
        questionIndex = 0;
        //lastQuestion = false;

        quiz.add(new Question("Apfel", "Apple", der, masculine, R.drawable.apple));
        quiz.add(new Question("Auto", "Car", das, neutral, R.drawable.car));
        quiz.add(new Question("Baum", "Tree", der ,masculine, R.drawable.tree));
        quiz.add(new Question("Ente", "Duck", die, feminine, R.drawable.duck));
        quiz.add(new Question("Haus", "House", das, neutral, R.drawable.house));
        quiz.add(new Question("Hexe", "Witch", die, feminine, R.drawable.witch));
        quiz.add(new Question("Kuh", "Cow", die, feminine, R.drawable.cow));
        quiz.add(new Question("Milch", "Milk", die, feminine, R.drawable.milk));
        quiz.add(new Question("Schaf", "Sheep", das, neutral, R.drawable.sheep));
        quiz.add(new Question("Strasse", "Street", die, feminine, R.drawable.street));
        quiz.add(new Question("Stuhl", "Chair", der, masculine, R.drawable.chair));

    }

    public Question getQuestion(int index) {
        questionIndex = index;
        return quiz.get(index);
    }

    public Question nextQuestion() {
        //Question currentQuestion = quiz.get(questionIndex);
        questionIndex++;
        //if (questionIndex < quiz.size()) {
        //    currentQuestion = quiz.get(questionIndex);
        //} else {
        //    lastQuestion = true;
        //}
        //return currentQuestion;
        return quiz.get(questionIndex);
    }

    public void shuffleQuestions() {
        Random rand = new Random();

        for(int i = 0; i < quiz.size() ; i++) {
            int j = i + (rand.nextInt(quiz.size() - i));
            Collections.swap(quiz, i, j);
        }
    }

    /*public Boolean isLastQuestion() {
        return lastQuestion;
    }*/

    /* public String onQuestion() {
        return questionIndex+1 + " / " + quiz.size();
    }*/

    public int calculateScore() {
        int score = 0;

        for (int i = 0; i < quiz.size(); i++) {
            if(quiz.get(i).getCorrect() == true) {
                score++;
            }
        }

        return score;
    }
}
