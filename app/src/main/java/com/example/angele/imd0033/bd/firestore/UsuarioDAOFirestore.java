package com.example.angele.imd0033.bd.firestore;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.angele.imd0033.dominio.Usuario;
import com.example.angele.imd0033.bd.UsuarioDAO;
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

public class UsuarioDAOFirestore implements UsuarioDAO {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private void inserir(Usuario usuario){
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("ativo",usuario.isAtivo() );
        user.put("id_usuario", usuario.getId_usuario());
        user.put("id_unidade", usuario.getId_unidade());
        user.put("id_foto", usuario.getId_foto());
        user.put("cpf_cnpj", usuario.getCpf_cnpj());
        user.put("email", usuario.getEmail());
        user.put("login", usuario.getLogin());
        user.put("senha", usuario.getSenha());
        user.put("nome_pessoa", usuario.getNome_pessoa());
        user.put("chave_foto", usuario.getChave_foto());
        user.put("url_foto", usuario.getUrl_foto());

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
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
    public List<Usuario> buscarTodas() {
        final List<Usuario> componentes = new ArrayList<>();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Usuario u = new Usuario(
                                        document.getBoolean("carga_horaria_total"),
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
                                componentes.add(u);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        return componentes;
    }
}
