package com.example.angele.imd0033.bd.firestore;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.angele.imd0033.dominio.Postagem;
import com.example.angele.imd0033.bd.PostagemDAO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public class PostagemDAOFirestore implements PostagemDAO{
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void inserir(Postagem postagem){
        // Create a new user with a first and last name
        Map<String, Object> post = new HashMap<>();
        post.put("first", "Ada");
        post.put("last", "Lovelace");
        post.put("born", 1815);

// Add a new document with a generated ID
        db.collection("users")
                .add(post)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    @Override
    public LiveData<List<Postagem>> buscarTodas() {
        return new FirebaseQueryLiveData(db.collection("palavra").whereGreaterThanOrEqualTo("id", 0));
    }
}
