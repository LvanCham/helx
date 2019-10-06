package com.cham.helx.ui.dashboard;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();

        mText.setValue("诗句");
    }

    public LiveData<String> getText() {

        return mText;
    }


    public void setText(String s) {

        mText.postValue(s);
    }
}