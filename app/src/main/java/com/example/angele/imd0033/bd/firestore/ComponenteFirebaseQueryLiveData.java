package com.example.angele.imd0033.bd.firestore;

import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.angele.imd0033.dominio.ComponenteCurricular;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;
import static com.example.angele.imd0033.bd.firestore.FirebaseQueryLiveData.LOG_TAG;

public class ComponenteFirebaseQueryLiveData extends LiveData<List<ComponenteCurricular>> {
    private final Query query;
    private final ComponenteFirebaseQueryLiveData.MyValueEventListener listener = new ComponenteFirebaseQueryLiveData.MyValueEventListener();

    private ListenerRegistration registration;

    public ComponenteFirebaseQueryLiveData(Query query) {
        this.query = query;
    }

    public ComponenteFirebaseQueryLiveData(CollectionReference ref) {
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
            List<ComponenteCurricular> componentes = new ArrayList<>();
            for (DocumentSnapshot document : value.getDocuments()) {
                Log.d(TAG, document.getId() + " => " + document.getData());
                ComponenteCurricular c = new ComponenteCurricular();

                c.setCarga_horaria_total(document.getLong("carga_horaria_total").intValue());
                c.setCo_requisitos(document.getString("co_requisitos"));
                c.setCodigo(document.getString("codigo"));
                c.setComponentesBloco(document.getString("componentesBloco"));
                c.setDepartamento(document.getString("departamento"));
                c.setDisciplina_obrigatoria(document.getString("disciplina_obrigatoria"));
                c.setEquivalentes(document.getString("equivalentes"));
                c.setId_componente(document.getLong("id_componente").intValue());
                c.setId_matriz_curricular(document.getLong("id_matriz_curricular").intValue());
                c.setNome(document.getString("nome"));
                c.setPre_requisitos(document.getString("pre_requisitos"));
                c.setSemestre_oferta(document.getLong("semestre__oferta").intValue());
                componentes.add(c);
            }
           setValue(componentes);
            Log.d(LOG_TAG, "Postagem retornadas");
        }
    }
}
