package usyd.comp5216.dkthero;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import usyd.comp5216.dkthero.database.DatabaseHandler;
import usyd.comp5216.dkthero.models.QuizModel;
import usyd.comp5216.dkthero.questions.GenerateQuestions;


public class BeginTestActivity extends AppCompatActivity {

    private TextView textViewCorrectValue;
    private TextView textViewWrongValue;
    private TextView textViewBestScoreValue;
    private TextView textViewQuizImage;
    private TextView textViewQuizText;
    private TextView textViewNavigation;
    private Button buttonAnswer1;
    private Button buttonAnswer2;
    private Button buttonAnswer3;
    private Button buttonRestart;
    private Button buttonNext;
    private String selectedCategory;
    private String bestScore;
    private List<QuizModel> listQuiz = new ArrayList<>();
    private int quizIndex = 0;
    private int correctAnswer = 0;
    private int wrongAnswer = 0;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_test);

        Bundle b = getIntent().getExtras();
        selectedCategory = b.getString("category");
        bestScore = b.getString("best_score");
        textViewBestScoreValue = (TextView) findViewById(R.id.textViewBestScoreValue);
        textViewBestScoreValue.setText(bestScore);
        listQuiz = new GenerateQuestions().listQuiz(selectedCategory);
        Collections.shuffle(listQuiz);
        for (QuizModel q : listQuiz) {
            q.shuffle();
        }

        initComponents();
        buttonNext.setVisibility(View.GONE);

        buttonNextAction();
        buttonAnswer1Action();
        buttonAnswer2Action();
        buttonAnswer3Action();

        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BeginTestActivity.class);
                Bundle b = new Bundle();
                b.putString("category", selectedCategory);
                b.putString("best_score", bestScore);
                intent.putExtras(b);
                v.getContext().startActivity(intent);
            }
        });
    }

    public void buttonNextAction() {
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quizIndex < (listQuiz.size() - 1)) {
                    quizIndex++;
                    initComponents();
                } else if (quizIndex == (listQuiz.size() - 1)) {
                    Intent intent = new Intent(v.getContext(), EndTestActivity.class);
                    Bundle b = new Bundle();
                    databaseHandler = new DatabaseHandler(v.getContext());
                    if (Integer.parseInt(bestScore) < Integer.parseInt(textViewCorrectValue.getText().toString()) && !bestScore.equals("0")) {
                        databaseHandler.updateScore(selectedCategory, textViewCorrectValue.getText().toString());
                        b.putString("bestScore", textViewCorrectValue.getText().toString());
                    } else if (bestScore.equals("0")) {
                        databaseHandler.insertScore(selectedCategory, textViewCorrectValue.getText().toString());
                        b.putString("bestScore", textViewCorrectValue.getText().toString());
                    } else {
                        b.putString("bestScore", bestScore);
                    }
                    b.putString("category", selectedCategory);
                    b.putString("quizCount", "" + listQuiz.size());
                    b.putString("correctAnswer", "" + correctAnswer);
                    b.putString("wrongAnswer", "" + wrongAnswer);

                    intent.putExtras(b);
                    v.getContext().startActivity(intent);
                }
                clearButtonColors();
                enableButtons();
            }
        });
    }

    public void buttonAnswer1Action() {
        buttonAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableButtons();
                if (buttonAnswer1.getText().equals(listQuiz.get(quizIndex).getQuizAnswers()[listQuiz.get(quizIndex).getTrueAnswerIndex()])) {
                    correctAnswer++;
                    buttonAnswer1.setBackgroundColor(getResources().getColor(R.color.correct_answer));
                } else {
                    wrongAnswer++;
                    buttonAnswer1.setBackgroundColor(getResources().getColor(R.color.wrong_answer));
                    trueAnswerButton();
                }
            }
        });
    }

    public void buttonAnswer2Action() {
        buttonAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableButtons();
                if (buttonAnswer2.getText().equals(listQuiz.get(quizIndex).getQuizAnswers()[listQuiz.get(quizIndex).getTrueAnswerIndex()])) {
                    correctAnswer++;
                    buttonAnswer2.setBackgroundColor(getResources().getColor(R.color.correct_answer));
                } else {
                    wrongAnswer++;
                    buttonAnswer2.setBackgroundColor(getResources().getColor(R.color.wrong_answer));
                    trueAnswerButton();
                }
            }
        });
    }

    public void buttonAnswer3Action() {
        buttonAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableButtons();
                if (buttonAnswer3.getText().equals(listQuiz.get(quizIndex).getQuizAnswers()[listQuiz.get(quizIndex).getTrueAnswerIndex()])) {
                    correctAnswer++;
                    buttonAnswer3.setBackgroundColor(getResources().getColor(R.color.correct_answer));
                } else {
                    wrongAnswer++;
                    buttonAnswer3.setBackgroundColor(getResources().getColor(R.color.wrong_answer));
                    trueAnswerButton();
                }
            }
        });
    }


    public void initComponents() {
        textViewCorrectValue = (TextView) findViewById(R.id.textViewCorrectValue);
        textViewCorrectValue.setText("" + correctAnswer);
        textViewWrongValue = (TextView) findViewById(R.id.textViewWrongValue);
        textViewWrongValue.setText("" + wrongAnswer);
        QuizModel qm = listQuiz.get(quizIndex);
        textViewQuizImage = (TextView) findViewById(R.id.textViewQuizImage);
        if (qm.getQuizImage() > 0) {
            textViewQuizImage.setVisibility(View.VISIBLE);
            textViewQuizImage.setBackgroundResource(qm.getQuizImage());
        } else {
            textViewQuizImage.setVisibility(View.GONE);
        }
        textViewQuizText = (TextView) findViewById(R.id.textViewQuizText);
        textViewQuizText.setText(qm.getQuizText());
        textViewNavigation = (TextView) findViewById(R.id.textViewNavigation);
        textViewNavigation.setText("" + (quizIndex + 1) + "/" + listQuiz.size());
        buttonAnswer1 = (Button) findViewById(R.id.buttonAnswer1);
        buttonAnswer1.setText(qm.getQuizAnswers()[0]);
        buttonAnswer2 = (Button) findViewById(R.id.buttonAnswer2);
        buttonAnswer2.setText(qm.getQuizAnswers()[1]);
        buttonAnswer3 = (Button) findViewById(R.id.buttonAnswer3);
        buttonAnswer3.setText(qm.getQuizAnswers()[2]);
        buttonRestart = (Button) findViewById(R.id.buttonRestart);
        buttonNext = (Button) findViewById(R.id.buttonNext);
    }

    public void clearButtonColors() {
        buttonAnswer1.setBackgroundColor(getResources().getColor(R.color.primary));
        buttonAnswer2.setBackgroundColor(getResources().getColor(R.color.primary));
        buttonAnswer3.setBackgroundColor(getResources().getColor(R.color.primary));
    }

    public void disableButtons() {
        buttonAnswer1.setClickable(false);
        buttonAnswer2.setClickable(false);
        buttonAnswer3.setClickable(false);
        buttonNext.setVisibility(View.VISIBLE);
    }

    public void enableButtons() {
        buttonAnswer1.setClickable(true);
        buttonAnswer2.setClickable(true);
        buttonAnswer3.setClickable(true);
        buttonNext.setVisibility(View.GONE);
    }

    public void trueAnswerButton() {
        String trueAnswer = listQuiz.get(quizIndex).getQuizAnswers()[listQuiz.get(quizIndex).getTrueAnswerIndex()];
        if (buttonAnswer1.getText().equals(trueAnswer)) {
            buttonAnswer1.setBackgroundColor(getResources().getColor(R.color.correct_answer));
        } else if (buttonAnswer2.getText().equals(trueAnswer)) {
            buttonAnswer2.setBackgroundColor(getResources().getColor(R.color.correct_answer));
        } else if (buttonAnswer3.getText().equals(trueAnswer)) {
            buttonAnswer3.setBackgroundColor(getResources().getColor(R.color.correct_answer));
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }

}
