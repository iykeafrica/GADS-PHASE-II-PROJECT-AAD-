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
import com.example.gadsleaderboard.models.LearnerSkillIQ;

import java.util.List;

public class SkillIQLeadersAdapter extends RecyclerView.Adapter<SkillIQLeadersAdapter.SkillIQLeadersViewHolder> {

    private Context mContext;
    private List<LearnerSkillIQ> mList;

    public SkillIQLeadersAdapter(Context context, List<LearnerSkillIQ> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public SkillIQLeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.skill_iq_template, parent, false);
        return new SkillIQLeadersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SkillIQLeadersViewHolder holder, final int position) {

        LearnerSkillIQ learnerSkillIQ = mList.get(position);
        holder.bind(learnerSkillIQ);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class SkillIQLeadersViewHolder extends RecyclerView.ViewHolder{

        public TextView learnerSkillIqName, learnerSkillIqScore, learnerSkillIqCountry;
        public ImageView badgeImageSkillIq;

        public SkillIQLeadersViewHolder(@NonNull View itemView) {
            super(itemView);

            learnerSkillIqName = itemView.findViewById(R.id.skill_iq_learner_name);
            learnerSkillIqScore = itemView.findViewById(R.id.skill_iq_learner_score);
            learnerSkillIqCountry = itemView.findViewById(R.id.skill_iq_learner_country);
            badgeImageSkillIq = itemView.findViewById(R.id.badgeImageSkillIq);
        }

        public void bind (LearnerSkillIQ learnerSkillIQ) {

            learnerSkillIqName.setText(learnerSkillIQ.getName());
            learnerSkillIqScore.setText(learnerSkillIQ.getScore());
            learnerSkillIqCountry.setText(learnerSkillIQ.getCountry());
            showImage(learnerSkillIQ.getBadgeUrl());
        }

        public void showImage(String url){
            if (url != null){
                //Picasso.get().load(url).into(badgeImageSkillIq);
                Glide.with(mContext).load(url).into(badgeImageSkillIq);
            }
        }
    }
}
