package bit.abduhs1.germannouns;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Manager manager;
    private Question currentQuestion;
    private String answer;
    private String germanAnswer;
    private TextView tvQuiz;
    private ImageView ivNoun;
    private TextView tvQuestion;
    private TextView tvAnswer;
    private int questionsUsed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new Manager();
        for (int i = 0; i < 100 ; i++) {
            manager.shuffleQuestions();
        }

        //manager.shuffleQuestions();
        currentQuestion = manager.getQuestion(questionsUsed);

        answer = "";
        germanAnswer = "";
        tvQuiz = (TextView)findViewById(R.id.tvQuiz);
        ivNoun = (ImageView)findViewById(R.id.ivNoun);
        tvQuestion = (TextView)findViewById(R.id.tvQuestion);
        tvAnswer = (TextView)findViewById(R.id.tvAnswer);

        setUpQuestion();

        Button der = (Button)findViewById(R.id.btMasculine);
        Button das = (Button)findViewById(R.id.btNeutral);
        Button die = (Button)findViewById(R.id.btFeminine);

        Button confirm = (Button)findViewById(R.id.btConfirm);

        der.setOnClickListener(new DerButtonClickHandler());
        das.setOnClickListener(new DasButtonClickHandler());
        die.setOnClickListener(new DieButtonClickHandler());

        confirm.setOnClickListener(new ConfirmButtonClickHandler());

    }

    public void setUpQuestion() {
        tvQuiz.setText("What is the associated gender of " + currentQuestion.getEnglish() + "?");
        ivNoun.setImageResource(currentQuestion.getImage());
        tvQuestion.setText(currentQuestion.getGerman());
        tvAnswer.setText("??? " + currentQuestion.getGerman());
    }

    public void nextQuestion(Manager manager) {
        this.manager = manager;
        answer = "";
        if (!(questionsUsed > 10)) {
            currentQuestion = this.manager.getQuestion(questionsUsed);
            setUpQuestion();
        } else {
            Intent changeActivityIntent = new Intent(MainActivity.this, LastActivity.class);
            changeActivityIntent.putExtra("score", manager.calculateScore());
            startActivity(changeActivityIntent);
        }
    }

    public class DerButtonClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            answer = "Masculine";
            germanAnswer = "Der";
            tvAnswer.setText(germanAnswer + " " + currentQuestion.getGerman());
        }
    }

    public class DasButtonClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            answer = "Neutral";
            germanAnswer = "Das";
            tvAnswer.setText(germanAnswer + " " + currentQuestion.getGerman());
        }
    }

    public class DieButtonClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            answer = "Feminine";
            germanAnswer = "Die";
            tvAnswer.setText(germanAnswer + " " + currentQuestion.getGerman());
        }
    }

    public class ConfirmButtonClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            if (!(answer.isEmpty())) {
                NextQuestionFragment nextQuestion = new NextQuestionFragment();
                nextQuestion.setAnswer(answer);
                nextQuestion.setGermanAnswer(germanAnswer);
                nextQuestion.setManager(manager);
                nextQuestion.setQuestionsUsed(questionsUsed);
                questionsUsed++;
                FragmentManager fm = getFragmentManager();
                nextQuestion.show(fm, "confirm");
            } else {
                Toast.makeText(MainActivity.this, "You have not selected one of the answers", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
