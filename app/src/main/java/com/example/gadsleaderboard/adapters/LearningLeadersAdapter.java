package com.example.gadsleaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gadsleaderboard.R;
import com.example.gadsleaderboard.models.LearnerHour;

import java.util.List;

public class LearningLeadersAdapter extends RecyclerView.Adapter<LearningLeadersAdapter.LearningLeadersViewHolder> {

    private Context mContext;
    private List<LearnerHour> mList;

    public LearningLeadersAdapter(Context context, List<LearnerHour> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public LearningLeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.learning_leaders_template, parent, false);
        return new LearningLeadersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LearningLeadersViewHolder holder, final int position) {
        //holder.bind(mList[position]);
        LearnerHour learnerHour = mList.get(position);
        holder.bind(learnerHour);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    
    public class LearningLeadersViewHolder extends RecyclerView.ViewHolder{

        TextView learnerName, learnerHours, leanerCountry;
        ImageView badgeImageHours;
        public LearningLeadersViewHolder(@NonNull View itemView) {
            super(itemView);
            
            learnerName = itemView.findViewById(R.id.learner_name);
            learnerHours = itemView.findViewById(R.id.learner_hours);
            leanerCountry = itemView.findViewById(R.id.learner_country);
            badgeImageHours = itemView.findViewById(R.id.badge_image_hours);
        }

        public void bind(LearnerHour learnerHour){
            learnerName.setText(learnerHour.getName());
            learnerHours.setText(learnerHour.getHours());
            leanerCountry.setText(learnerHour.getCountry());
            showImage(learnerHour.getBadgeUrl());
        }
        public void showImage(String url){
            Glide.with(mContext).load(url).into(badgeImageHours);
        }
    } 
}
