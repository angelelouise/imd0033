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

    private Map<String,Object> popularDados(Postagem postagem){
        Map<String, Object> post = new HashMap<>();
        post.put("descricao",postagem.getDescricao());
        post.put("titulo",postagem.getTitulo());
        post.put("id_componente_curricular",postagem.getId_componente_curricular());
        post.put("id_postagem",postagem.getId_postagem());
        post.put("id_usuario",postagem.getId_usuario());
        post.put("usuario",postagem.getUsuario());

        return post;
    }
    public void inserir(Postagem postagem){
        Map<String, Object> post = new HashMap<>();
        post = popularDados(postagem);

    // Add a new document with a generated ID
        db.collection("postagem")
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
    public void atualizar(Postagem postagem) {

    }

    @Override
    public void deletar(Postagem postagem) {

    }

    @Override
    public LiveData<Postagem> findById(Long id) {
        return null;
    }

    @Override
    public LiveData<List<Postagem>> findByUsuario(Long id_usuario) {
        return null;
    }

    @Override
    public LiveData<List<Postagem>> findByAutor(String nome) {
        return null;
    }

    @Override
    public LiveData<List<Postagem>> findByComponente(Long id_componente) {
        return null;
    }

    @Override
    public LiveData<List<Postagem>> buscarTodas() {
        return new FirebaseQueryLiveData(db.collection("postagem").whereGreaterThanOrEqualTo("id", 0));
    }
}
