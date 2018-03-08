package usyd.comp5216.dkthero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import usyd.comp5216.dkthero.adapters.CategoryAdapter;
import usyd.comp5216.dkthero.models.CategoryModel;


public class CategoryActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<CategoryModel> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Choose Category");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        initCategory();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        CategoryAdapter adapter = new CategoryAdapter(categoryList);
        recyclerView.setAdapter(adapter);

    }

    public void initCategory() {
        categoryList.add(new CategoryModel("ICAC and General 1"));
        categoryList.add(new CategoryModel("ICAC and General 2"));
        categoryList.add(new CategoryModel("Fatigue and Defensive"));
        categoryList.add(new CategoryModel("Intersections 1"));
        categoryList.add(new CategoryModel("Intersections 2"));
        categoryList.add(new CategoryModel("Negligent Driving"));
        categoryList.add(new CategoryModel("Pedestrians"));
        categoryList.add(new CategoryModel("Rider Safety"));
        categoryList.add(new CategoryModel("Speed Limits"));
        categoryList.add(new CategoryModel("Lights and Lanes"));
        categoryList.add(new CategoryModel("Alcohol and Drugs"));
        categoryList.add(new CategoryModel("Traffic Signs 1"));
        categoryList.add(new CategoryModel("Traffic Signs 2"));


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
