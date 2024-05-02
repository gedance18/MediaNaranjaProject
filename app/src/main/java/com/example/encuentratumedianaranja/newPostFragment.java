package com.example.encuentratumedianaranja;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

public class newPostFragment extends Fragment {

    Button publishButton;
    EditText postConentEditText;

    NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        publishButton = view.findViewById(R.id.publishButton);
        postConentEditText = view.findViewById(R.id.postContentEditText);
        navController = Navigation.findNavController(view);

        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publicar();
            }
        });

    }

    public class Post {
        public String uid;
        public String author;
        public String authorPhotoUrl;
        public String content;

        // Constructor vacio requerido por Firestore
        public Post() {}

        public Post(String uid, String author, String authorPhotoUrl, String content) {
            this.uid = uid;
            this.author = author;
            this.authorPhotoUrl = authorPhotoUrl;
            this.content = content;
        }
    }

    private void publicar() {
        String postContent = postConentEditText.getText().toString();

        if(TextUtils.isEmpty(postContent)){
            postConentEditText.setError("Required");
            return;
        }

        publishButton.setEnabled(false);

        guardarEnFirestore(postContent);
    }

    private void guardarEnFirestore(String postContent) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Post post = new Post(user.getUid(), user.getDisplayName(), user.getPhotoUrl().toString(), postContent);

        FirebaseFirestore.getInstance().collection("posts")
                .add(post)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        navController.popBackStack();
                    }
                });
    }
}