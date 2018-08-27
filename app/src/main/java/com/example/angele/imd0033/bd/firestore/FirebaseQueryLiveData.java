package com.example.angele.imd0033.bd.firestore;

import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.angele.imd0033.dominio.Postagem;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseQueryLiveData extends LiveData<List<Postagem>> {
    public static final String LOG_TAG = "FirebaseQueryLiveData";

    private final Query query;
    private final MyValueEventListener listener = new MyValueEventListener();

    private ListenerRegistration registration;

    public FirebaseQueryLiveData(Query query) {
        this.query = query;
    }

    public FirebaseQueryLiveData(CollectionReference ref) {
        this.query = ref;
    }

    @Override
    protected void onActive() {
        Log.d(LOG_TAG, "onActive");
        registration = query.addSnapshotListener(listener);
    }

    @Override
    protected void onInactive() {
        Log.d(LOG_TAG, "onInactive");
        registration.remove();
    }

    private class MyValueEventListener implements EventListener<QuerySnapshot> {
        @Override
        public void onEvent(@Nullable QuerySnapshot value,
                            @Nullable FirebaseFirestoreException e) {

            if (e != null) {
                Log.w(LOG_TAG, "Listen failed.", e);
                return;
            }

            List<Postagem> postagens = new ArrayList<>();

            for(DocumentSnapshot doc : value.getDocuments()){
                Postagem p = new Postagem();
                        p.setId_postagem(doc.getLong("id_postagem"));
                        p.setId_usuario(doc.getLong("id_usuario"));
                        p.setId_componente_curricular(doc.getLong("id_componente_curricular"));
                        p.setUsuario(doc.getString("usuario"));
                        p.setDescricao(doc.getString("descricao"));
                        p.setTitulo(doc.getString("titulo"));

                postagens.add(p);
            }

            setValue(postagens);
            Log.d(LOG_TAG, "Postagem retornadas");
        }
    }
}
