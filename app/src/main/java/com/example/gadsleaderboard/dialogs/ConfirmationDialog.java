package com.example.gadsleaderboard.dialogs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gadsleaderboard.ProjectSubmissionActivity;
import com.example.gadsleaderboard.R;
import com.example.gadsleaderboard.interfaces.ApiSubmit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.gadsleaderboard.ProjectSubmissionActivity.EMAIL_ADDRESS;
import static com.example.gadsleaderboard.ProjectSubmissionActivity.FIRST_NAME;
import static com.example.gadsleaderboard.ProjectSubmissionActivity.GITHUB_LINK;
import static com.example.gadsleaderboard.ProjectSubmissionActivity.LAST_NAME;

public class ConfirmationDialog extends Fragment {

    public static final String BRING_BACK_SUBMIT_BUTTON = "bring_back_submit_button";
    public static final String SUBMIT_BUTTON = "submit_button";
    private View mView;
    private String mFirstName;
    private String mLastName;
    private String mEmailAddress;
    private String mGithubLink;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mFirstName = bundle.getString(FIRST_NAME);
            mLastName = bundle.getString(LAST_NAME);
            mEmailAddress = bundle.getString(EMAIL_ADDRESS);
            mGithubLink = bundle.getString(GITHUB_LINK);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.dialog_confirmation, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageView closeButton = mView.findViewById(R.id.close_button);
        ConstraintLayout confirmButton = mView.findViewById(R.id.confirm_button);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //((ProjectSubmissionActivity)getActivity()).mSubmitButton.setVisibility(View.VISIBLE);
                getActivity().findViewById(R.id.first_name).setVisibility(View.VISIBLE);
                getActivity().findViewById(R.id.surname).setVisibility(View.VISIBLE);
                getActivity().findViewById(R.id.email).setVisibility(View.VISIBLE);
                getActivity().findViewById(R.id.github_link).setVisibility(View.VISIBLE);
                getActivity().findViewById(R.id.submit_button).setVisibility(View.VISIBLE);
                getActivity().findViewById(R.id.main_container_layout).setAlpha(1.0f);

//                getDialog().dismiss();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    transaction.remove(prev);
                }
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPost(mFirstName, mLastName, mEmailAddress, mGithubLink);
            }
        });
    }

    private void sendPost(String firstName, String lastName, String emailAddress, String githubLink) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiSubmit.BASE_URL)
                .build();

        ApiSubmit apiSubmit = retrofit.create(ApiSubmit.class);

        Call<Void> call = apiSubmit.savePost(firstName, lastName, emailAddress, githubLink);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {

                    Log.i("Response", "Checking:Successful " + response);

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                    if (prev != null) {
                        transaction.remove(prev);
                    }
                    transaction.addToBackStack(null);

                     //Create and show the dialog.
                    SuccessfulDialog dialog = new SuccessfulDialog();
//                    dialog.show(transaction, "key");
                    transaction.replace(R.id.main_container, dialog, "dialog");
                    transaction.commit();

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("Response", "Checking:Error " + t.getMessage());

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    transaction.remove(prev);
                }
                transaction.addToBackStack(null);

                // Create and show the dialog.
                ErrorDialog dialog = new ErrorDialog();
//                dialog.show(transaction, "key");
                transaction.replace(R.id.main_container, dialog, "dialog");
                transaction.commit();
            }
        });
    }

}