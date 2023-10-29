package com.example.ampamain.fragments.perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ampamain.modelos.User;

public class PerfilViewModel extends ViewModel {

    private final MutableLiveData<User> userProfile = new MutableLiveData<>();

    public PerfilViewModel() {

    }

    public LiveData<User> getUserProfile() {
        return userProfile;
    }

    // Método para actualizar el perfil del usuario
    public void updateUserProfile(User updatedProfile) {
        userProfile.setValue(updatedProfile);
    }
}
