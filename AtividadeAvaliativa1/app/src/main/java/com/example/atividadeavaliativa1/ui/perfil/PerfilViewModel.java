package com.example.atividadeavaliativa1.ui.perfil;

import android.content.Intent;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.atividadeavaliativa1.MainActivity;
import com.example.atividadeavaliativa1.R;

public class PerfilViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PerfilViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("This is perfil fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}