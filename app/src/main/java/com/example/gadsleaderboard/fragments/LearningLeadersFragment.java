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
import com.example.gadsleaderboard.adapters.LearningLeadersAdapter;
import com.example.gadsleaderboard.interfaces.ApiHours;
import com.example.gadsleaderboard.models.LearnerHour;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LearningLeadersFragment extends Fragment {

    private View mView;
    private LearningLeadersAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public LearningLeadersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_learning_leaders, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //String[] arrayList = getResources().getStringArray(R.array.leading_learners_name);

        mRecyclerView = mView.findViewById(R.id.learner_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        getLearnerHoursDetails();
    }

    private void getLearnerHoursDetails() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiHours.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiHours api = retrofit.create(ApiHours.class);

        Call<List<LearnerHour>> call = api.getHourInfo();

        call.enqueue(new Callback<List<LearnerHour>>() {
            @Override
            public void onResponse(Call<List<LearnerHour>> call, Response<List<LearnerHour>> response) {
                List<LearnerHour> learnerHourList = response.body();

                mAdapter = new LearningLeadersAdapter(getActivity(), learnerHourList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<LearnerHour>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}