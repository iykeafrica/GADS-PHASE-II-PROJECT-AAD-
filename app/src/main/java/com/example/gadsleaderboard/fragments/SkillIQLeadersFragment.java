package com.example.gadsleaderboard.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboard.R;
import com.example.gadsleaderboard.adapters.SkillIQLeadersAdapter;
import com.example.gadsleaderboard.interfaces.ApiSkillIQ;
import com.example.gadsleaderboard.models.LearnerSkillIQ;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkillIQLeadersFragment extends Fragment {

    View mView;
    private SkillIQLeadersAdapter mAdapter;
    private RecyclerView mRecyclerView;
    public List<LearnerSkillIQ> mLearnerSkillIQList;

    public SkillIQLeadersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_skill_i_q_leaders, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //String[] arrayList = getResources().getStringArray(R.array.skill_iq_learners_name);
        mRecyclerView = mView.findViewById(R.id.skill_iq_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        getSkillIQDetails();

    }

    private void getSkillIQDetails() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiSkillIQ.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiSkillIQ api = retrofit.create(ApiSkillIQ.class);

        Call<List<LearnerSkillIQ>> call = api.getSkillIqInfo();

        call.enqueue(new Callback<List<LearnerSkillIQ>>() {
            @Override
            public void onResponse(Call<List<LearnerSkillIQ>> call, Response<List<LearnerSkillIQ>> response) {
                mLearnerSkillIQList = response.body();

                mAdapter = new SkillIQLeadersAdapter(getActivity(), mLearnerSkillIQList);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<List<LearnerSkillIQ>> call, Throwable t) {

                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}