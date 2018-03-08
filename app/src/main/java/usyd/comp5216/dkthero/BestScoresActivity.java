package usyd.comp5216.dkthero;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import usyd.comp5216.dkthero.adapters.BestScoresAdapter;
import usyd.comp5216.dkthero.database.DatabaseHandler;
import usyd.comp5216.dkthero.models.BestScoresModel;


public class BestScoresActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<BestScoresModel> bestScoresList = new ArrayList<>();
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_scores);
        db = new DatabaseHandler(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Best Scores");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        initBestScores();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        BestScoresAdapter adapter = new BestScoresAdapter(bestScoresList);
        recyclerView.setAdapter(adapter);

    }

    public void initBestScores() {
        for (BestScoresModel bsm : db.getAllScores()) {
            if (Integer.parseInt(bsm.getScore())!=0) {
                bestScoresList.add(new BestScoresModel(bsm.getId(), bsm.getCategory(), bsm.getScore()));
            }
        }

    }
}
