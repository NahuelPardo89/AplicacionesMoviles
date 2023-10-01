package com.example.ampamain.ui.perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ampamain.UserProfile;

public class PerfilViewModel extends ViewModel {

    private final MutableLiveData<UserProfile> userProfile = new MutableLiveData<>();

    public PerfilViewModel() {
        // Simulación: Obtención de datos del perfil del usuario
        UserProfile mockUserProfile = new UserProfile(12345678, "Juan Pablo", "Zosso", "juan.zosso@example.com", true, "https://cdn.domestika.org/c_limit,dpr_auto,f_auto,q_auto,w_820/v1576497340/content-items/003/518/329/_MG_5442-original.jpg");
        userProfile.setValue(mockUserProfile);
    }

    public LiveData<UserProfile> getUserProfile() {
        return userProfile;
    }

    // Método para actualizar el perfil del usuario
    public void updateUserProfile(UserProfile updatedProfile) {
        userProfile.setValue(updatedProfile);
    }
}
