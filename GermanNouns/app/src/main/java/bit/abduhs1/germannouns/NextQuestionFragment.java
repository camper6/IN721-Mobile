package bit.abduhs1.germannouns;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Sakinah on 29/03/2016.
 */
public class NextQuestionFragment extends DialogFragment {
    private String answer;
    private String germanAnswer;
    private Manager manager;
    private int questionsUsed;

    public NextQuestionFragment() {

    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setGermanAnswer(String germanAnswer) {
        this.germanAnswer = germanAnswer;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setQuestionsUsed(int questionsUsed) { this.questionsUsed = questionsUsed; }

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (manager.getQuestion(questionsUsed).getGender() == answer) {
            builder.setTitle("Correct! The associative gender of " + manager.getQuestion(questionsUsed).getEnglish()
                    + " is " + answer + ". " + germanAnswer + " " + manager.getQuestion(questionsUsed).getGerman());
            manager.correct(questionsUsed);
        } else {
            builder.setTitle("Incorrect. The associative gender of " + manager.getQuestion(questionsUsed).getEnglish()
                    + " is " + manager.getQuestion(questionsUsed).getGender() + ". " + manager.getQuestion(questionsUsed).getGermanGender()
                    + " " + manager.getQuestion(questionsUsed).getGerman());
        }
        builder.setPositiveButton("OK", new OKButtonHandler());

        Dialog customDialog = builder.create();

        return customDialog;
    }

    public class OKButtonHandler implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            MainActivity myActivity = (MainActivity) getActivity();
            myActivity.nextQuestion(manager);
        }
    }
}
