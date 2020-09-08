package com.example.gadsleaderboard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.gadsleaderboard.dialogs.ConfirmationDialog;

public class ProjectSubmissionActivity extends AppCompatActivity {

    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL_ADDRESS = "email_address";
    public static final String GITHUB_LINK = "github_link";
    private EditText mFirstName, mLastName, mEmailAddress, mLinkToProject;
    private ProjectSubmissionActivityViewModel mViewModel;
    private ConstraintLayout mSubmitButton;
    private ConstraintLayout mMainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);

        mFirstName = findViewById(R.id.first_name);
        mLastName = findViewById(R.id.surname);
        mEmailAddress = findViewById(R.id.email);
        mLinkToProject = findViewById(R.id.github_link);
        ImageView backArrow = findViewById(R.id.back_arrow);
        mSubmitButton = findViewById(R.id.submit_button);
        mMainContainer = findViewById(R.id.main_container_layout);

        ViewModelProvider viewModelProvider = new ViewModelProvider(getViewModelStore(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));

        mViewModel = viewModelProvider.get(ProjectSubmissionActivityViewModel.class);

        if (mViewModel.mIsNewlyCreated && savedInstanceState != null) {
            mViewModel.restoreState(savedInstanceState);
        }

        mViewModel.mIsNewlyCreated = false;

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                postToForm();
            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProjectSubmissionActivity.this, MainActivity.class));
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (outState != null) {
            mViewModel.saveState(outState);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void postToForm() {
        mViewModel.mSFirstName = mFirstName.getText().toString().trim();
        mViewModel.mSLastName = mLastName.getText().toString().trim();
        mViewModel.mSEmailAddress = mEmailAddress.getText().toString().trim();
        mViewModel.mSLinkToProject = mLinkToProject.getText().toString().trim();

        if (!TextUtils.isEmpty(mViewModel.mSFirstName) && !TextUtils.isEmpty(mViewModel.mSLastName)
                && !TextUtils.isEmpty(mViewModel.mSEmailAddress) && !TextUtils.isEmpty(mViewModel.mSLinkToProject)) {
//            sendPost(sFirstName, sLastName, sEmailAddress, sLinkToProject);
            sendBundle(mViewModel.mSFirstName, mViewModel.mSLastName, mViewModel.mSEmailAddress, mViewModel.mSLinkToProject);
        } else {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void sendBundle(String sFirstName, String sLastName, String sEmailAddress, String sLinkToProject) {
        ConfirmationDialog dialog = new ConfirmationDialog();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString(FIRST_NAME, sFirstName);
        bundle.putString(LAST_NAME, sLastName);
        bundle.putString(EMAIL_ADDRESS, sEmailAddress);
        bundle.putString(GITHUB_LINK, sLinkToProject);
        dialog.setArguments(bundle);

        mMainContainer.setAlpha(0.8f);
//        dialog.show(getSupportFragmentManager(), "dialog");
        transaction.replace(R.id.main_container, dialog, "dialog");
        transaction.commit();

        mSubmitButton.setVisibility(View.INVISIBLE);
        mFirstName.setVisibility(View.INVISIBLE);
        mLastName.setVisibility(View.INVISIBLE);
        mEmailAddress.setVisibility(View.INVISIBLE);
        mLinkToProject.setVisibility(View.INVISIBLE);

    }

}