package com.cham.helx.ui.notifications;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private MutableLiveData<View> mView;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
        mView= new MutableLiveData<>();
    }





    public LiveData<String> getText() {
        return mText;
    }
}