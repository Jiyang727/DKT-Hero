package usyd.comp5216.dkthero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class EndTestActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private Button buttonRestartTest;
    private Button buttonListTest;
    private TextView textViewCategory;
    private TextView textViewQuizCount;
    private TextView textViewCorrectAnswer;
    private TextView textViewWrongAnswer;
    private TextView textViewBestScore;

    private String category;
    private String quizCount;
    private String correctAnswer;
    private String wrongAnswer;
    private String bestScore;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_test);

        mInterstitialAd = new InterstitialAd(this);
        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        AdRequest adRequest = new AdRequest.Builder().build();
        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });

        Bundle b = getIntent().getExtras();
        category = b.getString("category");
        quizCount = b.getString("quizCount");
        correctAnswer = b.getString("correctAnswer");
        wrongAnswer = b.getString("wrongAnswer");
        bestScore = b.getString("bestScore");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Test End");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        initComponents();

        buttonRestartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BeginTestActivity.class);
                Bundle b = new Bundle();
                b.putString("category", category);
                b.putString("best_score", bestScore);
                intent.putExtras(b);
                v.getContext().startActivity(intent);
            }
        });
        buttonListTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CategoryActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }

    public void initComponents() {
        buttonRestartTest = (Button) findViewById(R.id.buttonRestartTest);
        buttonListTest = (Button) findViewById(R.id.buttonListTest);
        textViewCategory = (TextView) findViewById(R.id.textViewCategory);
        textViewCategory.setText("Category : " + category);
        textViewQuizCount = (TextView) findViewById(R.id.textViewQuizCount);
        textViewQuizCount.setText("Question count : " + quizCount);
        textViewCorrectAnswer = (TextView) findViewById(R.id.textViewCorrectAnswer);
        textViewCorrectAnswer.setText("Correct answer : " + correctAnswer);
        textViewWrongAnswer = (TextView) findViewById(R.id.textViewWrongAnswer);
        textViewWrongAnswer.setText("Wrong answer : " + wrongAnswer);
        textViewBestScore = (TextView) findViewById(R.id.textViewBestScore);
        textViewBestScore.setText("Best Score : " + bestScore);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

}
