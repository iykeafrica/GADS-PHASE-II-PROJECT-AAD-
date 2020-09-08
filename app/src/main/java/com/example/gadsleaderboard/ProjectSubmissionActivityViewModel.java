package com.example.gadsleaderboard;

import android.os.Bundle;

import androidx.lifecycle.ViewModel;

public class ProjectSubmissionActivityViewModel extends ViewModel {
    public static final String FIRST_NAME_VIEW_MODEL = "com.example.gadsleaderboard.FIRST_NAME_VIEW_MODEL";
    public static final String LAST_NAME_VIEW_MODEL = "com.example.gadsleaderboard.LAST_NAME_VIEW_MODEL";
    public static final String EMAIL_ADDRESS_VIEW_MODEL = "com.example.gadsleaderboard.EMAIL_ADDRESS_VIEW_MODEL";
    public static final String LINK_TO_PROJECT_VIEW_MODEL = "com.example.gadsleaderboard.LINK_TO_PROJECT_VIEW_MODEL";

    public String mSFirstName;
    public String mSLastName;
    public String mSEmailAddress;
    public String mSLinkToProject;
    public boolean mIsNewlyCreated = true;

    public void saveState(Bundle outState) {
        outState.putString(FIRST_NAME_VIEW_MODEL, mSFirstName);
        outState.putString(LAST_NAME_VIEW_MODEL, mSLastName);
        outState.putString(EMAIL_ADDRESS_VIEW_MODEL, mSEmailAddress);
        outState.putString(LINK_TO_PROJECT_VIEW_MODEL, mSLinkToProject);

    }

    public void restoreState(Bundle inState){
        mSFirstName = inState.getString(FIRST_NAME_VIEW_MODEL);
        mSLastName = inState.getString(LAST_NAME_VIEW_MODEL);
        mSEmailAddress = inState.getString(EMAIL_ADDRESS_VIEW_MODEL);
        mSLinkToProject = inState.getString(LINK_TO_PROJECT_VIEW_MODEL);
    }
}
