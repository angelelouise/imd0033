package com.example.angele.imd0033.bd.firestore;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.angele.imd0033.dominio.ComponenteCurricular;
import com.example.angele.imd0033.bd.CCurricularDAO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public class CCurricularDAOFirestore implements CCurricularDAO{
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Map<String,Object> popularDados(ComponenteCurricular componenteCurricular){
        Map<String, Object> ccurricular = new HashMap<>();
        ccurricular.put("carga_horaria_total",componenteCurricular.getCarga_horaria_total());
        ccurricular.put("co_requisitos", componenteCurricular.getCo_requisitos());
        ccurricular.put("codigo", componenteCurricular.getCodigo());
        ccurricular.put("componentesBloco", componenteCurricular.getComponentesBloco());
        ccurricular.put("departamento", componenteCurricular.getDepartamento());
        ccurricular.put("disciplina_obrigatoria", componenteCurricular.getDisciplina_obrigatoria());
        ccurricular.put("equivalentes", componenteCurricular.getEquivalentes());
        ccurricular.put("id_componente", componenteCurricular.getId_componente());
        ccurricular.put("id_matriz_curricular", componenteCurricular.getId_matriz_curricular());
        ccurricular.put("nome", componenteCurricular.getNome());
        ccurricular.put("pre_requisitos", componenteCurricular.getPre_requisitos());
        ccurricular.put("semestre_oferta", componenteCurricular.getSemestre_oferta());
        return ccurricular;
    }
    public void inserir(ComponenteCurricular componenteCurricular){
        // Create a new user with a first and last name
        Map<String, Object> ccurricular = new HashMap<>();
        ccurricular = popularDados(componenteCurricular);

// Add a new document with a generated ID
        db.collection("componente_curricular")
                .add(ccurricular)
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
    public void atualizar(ComponenteCurricular cc) {

    }

    @Override
    public void deletar(ComponenteCurricular cc) {

    }

    @Override
    public ComponenteCurricular findById(int id) {
        return null;
    }

    @Override
    public List<ComponenteCurricular> buscarTodas() {
        final List<ComponenteCurricular> componentes = new ArrayList<>();
        db.collection("componente_curricular")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                ComponenteCurricular c = new ComponenteCurricular(
                                        document.getLong("carga_horaria_total").intValue(),
                                        document.getString("co_requisitos"),
                                        document.getString("codigo"),
                                        document.getString("componentesBloco"),
                                        document.getString("departamento"),
                                        document.getString("disciplina_obrigatoria"),
                                        document.getString("equivalentes"),
                                        document.getLong("id_componente").intValue(),
                                        document.getLong("id_matriz_curricular").intValue(),
                                        document.getString("nome"),
                                        document.getString("pre_requisitos"),
                                        document.getLong("semestre__oferta").intValue());
                                componentes.add(c);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        return componentes;
    }
}
