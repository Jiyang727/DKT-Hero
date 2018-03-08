package usyd.comp5216.dkthero.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import usyd.comp5216.dkthero.BeginTestActivity;
import usyd.comp5216.dkthero.R;
import usyd.comp5216.dkthero.database.DatabaseHandler;
import usyd.comp5216.dkthero.models.CategoryModel;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<CategoryModel> items;
    private DatabaseHandler databaseHandler;

    public CategoryAdapter(List<CategoryModel> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_category, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.cat_name.setText(items.get(i).getName());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView cat_name;

        ViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardView);
            cat_name = (TextView) itemView.findViewById(R.id.cat_name);
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), BeginTestActivity.class);
                    Bundle b = new Bundle();
                    databaseHandler = new DatabaseHandler(itemView.getContext());
                    b.putString("category", cat_name.getText().toString());
                    if (databaseHandler.getScoreForCategory(cat_name.getText().toString()) != null) {
                        b.putString("best_score", databaseHandler.getScoreForCategory(cat_name.getText().toString()).getScore());
                    } else {
                        b.putString("best_score", "0");
                    }
                    intent.putExtras(b);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}