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

    private Map<String,Object> popularDados(Usuario usuario){
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
        return user;
    }

    public void inserir(Usuario usuario){
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user = popularDados(usuario);

        // Add a new document with a generated ID
        db.collection("usuario")
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
    public void atualizar(Usuario usuario) {
        Map<String, Object> user = new HashMap<>();
        user = popularDados(usuario);

    }

    @Override
    public void deletar(Usuario usuario) {

    }

    @Override
    public Usuario findByLogin(String login) {
        final Usuario[] usuario = new Usuario[1];
        db.collection("usuario").whereEqualTo("login",login)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        if(document!=null){
                            usuario[0] =  new Usuario(
                                    document.getBoolean("ativo"),
                                    document.getString("chave_foto"),
                                    document.getLong("cpf_cnpj").intValue(),
                                    document.getString("email"),
                                    document.getLong("id_foto").intValue(),
                                    document.getLong("id_unidade").intValue(),
                                    document.getLong("id_usuario").intValue(),
                                    document.getString("login"),
                                    document.getString("senha"),
                                    document.getString("nome_pessoa"),
                                    document.getString("url_foto"));
                        }
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

        return usuario[0];
    }

    @Override
    public List<Usuario> buscarTodos() {
        final List<Usuario> usuarios = new ArrayList<>();
        db.collection("usuario")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Usuario u = new Usuario(
                                        document.getBoolean("ativo"),
                                        document.getString("chave_foto"),
                                        document.getLong("cpf_cpnj").intValue(),
                                        document.getString("email"),
                                        document.getLong("id_foto").intValue(),
                                        document.getLong("id_unidade").intValue(),
                                        document.getLong("id_usuario").intValue(),
                                        document.getString("login"),
                                        document.getString("senha"),
                                        document.getString("nome_pessoa"),
                                        document.getString("url_foto"));
                                usuarios.add(u);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        return usuarios;
    }
}
