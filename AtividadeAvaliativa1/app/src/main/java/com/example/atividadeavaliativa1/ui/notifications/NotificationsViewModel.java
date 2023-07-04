package com.example.atividadeavaliativa1.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.atividadeavaliativa1.R;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<Integer> mText;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(R.string.january);
    }

    public LiveData<Integer> getText() {
        return mText;
    }
}