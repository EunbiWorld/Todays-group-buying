package com.example.groupbuying.fragment;

import android.net.Uri;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class FirebaseImageUploader {
    private FirebaseStorage storage;

    public FirebaseImageUploader() {
        this.storage = FirebaseStorage.getInstance();
    }

    public void uploadImage(Uri file, OnSuccessListener<Uri> onSuccessListener) {
        StorageReference storageRef = storage.getReference();
        StorageReference imageRef = storageRef.child("images/" + file.getLastPathSegment());
        UploadTask uploadTask = imageRef.putFile(file);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageRef.getDownloadUrl().addOnSuccessListener(onSuccessListener);
            }
        });
    }
}
