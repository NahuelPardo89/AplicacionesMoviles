package com.example.ampamain.fragments.perfil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.ampamain.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.io.IOException;

public class EditProfileFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView profileImageView, profileImageBtn;
    private EditText nameEditText, emailEditText;
    private Button updateButton;

    private FirebaseAuth mAuth;
    private FirebaseStorage mStorage;

    private Uri mImageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        profileImageView = view.findViewById(R.id.profileImageView_edit);
        profileImageBtn = view.findViewById(R.id.changeProfileImageButton);
        nameEditText = view.findViewById(R.id.nameEditText_edit);
        emailEditText = view.findViewById(R.id.emailEditText_edit);
        updateButton = view.findViewById(R.id.updateButton_edit);

        mAuth = FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance();

        profileImageBtn.setOnClickListener(v -> openFileChooser());

        updateButton.setOnClickListener(v -> updateProfile());

        loadUserData();

        return view;
    }

    private void loadUserData() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            nameEditText.setText(user.getDisplayName());
            emailEditText.setText(user.getEmail());
            if (user.getPhotoUrl() != null) {

                Glide.with(this).load(user.getPhotoUrl()).into(profileImageView);
            }
        }
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), mImageUri);
                profileImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateProfile() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            if (mImageUri != null) {
                // Upload the image to Firebase Storage
                StorageReference profileImageRef = mStorage.getReference("profileImages/" + user.getUid() + ".jpg");
                profileImageRef.putFile(mImageUri).addOnSuccessListener(taskSnapshot -> profileImageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    updateFirebaseProfile(uri, name, email);
                })).addOnFailureListener(e -> Toast.makeText(getContext(), "Error al subir imagen: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            } else {
                updateFirebaseProfile(user.getPhotoUrl(), name, email);
            }
        }
    }

    private void updateFirebaseProfile(Uri photoUri, String name, String email) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .setPhotoUri(photoUri)
                    .build();

            user.updateProfile(profileUpdates).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    user.updateEmail(email).addOnCompleteListener(emailUpdateTask -> {
                        if (emailUpdateTask.isSuccessful()) {
                            Toast.makeText(getContext(), "Perfil actualizado", Toast.LENGTH_SHORT).show();
                            NavHostFragment.findNavController(this).navigate(R.id.nav_perfil);
                        } else {
                            Toast.makeText(getContext(), "Error al actualizar email", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "Error al actualizar perfil", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
