package usyd.comp5216.dkthero.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import usyd.comp5216.dkthero.R;
import usyd.comp5216.dkthero.models.BestScoresModel;
import usyd.comp5216.dkthero.questions.GenerateQuestions;


public class BestScoresAdapter extends RecyclerView.Adapter<BestScoresAdapter.ViewHolder> {

    List<BestScoresModel> items;

    public BestScoresAdapter(List<BestScoresModel> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_best_scores, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.score.setText(items.get(i).getCategory());
        viewHolder.scoreNum.setText(items.get(i).getScore() + "/" + new GenerateQuestions().getSize(items.get(i).getCategory()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView score;
        TextView scoreNum;

        ViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            score = (TextView) itemView.findViewById(R.id.score);
            scoreNum = (TextView) itemView.findViewById(R.id.scorenum);

        }
    }
}
