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
    private Usuario usuario = new Usuario();

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

        db.collection("usuario").whereEqualTo("login",login)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                            Usuario u =  new Usuario();
                                    u.setAtivo(document.getBoolean("ativo"));
                                    u.setChave_foto(document.getString("chave_foto"));
                                    u.setCpf_cnpj(document.getLong("cpf_cnpj").intValue());
                                    u.setEmail(document.getString("email"));
                                    u.setId_foto(document.getLong("id_foto").intValue());
                                    u.setId_unidade(document.getLong("id_unidade").intValue());
                                    u.setId_usuario(document.getLong("id_usuario").intValue());
                                    u.setLogin(document.getString("login"));
                                    u.setSenha(document.getString("senha"));
                                    u.setNome_pessoa(document.getString("nome_pessoa"));
                                    u.setUrl_foto(document.getString("url_foto"));
                            usuario =u;
                            //usuario = document.toObject(Usuario.class);
                            Log.d(TAG, "user:" + usuario);
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
        Log.d(TAG, "user2:" +usuario);
        return usuario;
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
                                Usuario u = new Usuario();
                                    u.setAtivo(document.getBoolean("ativo"));
                                    u.setChave_foto(document.getString("chave_foto"));
                                    u.setCpf_cnpj(document.getLong("cpf_cnpj").intValue());
                                    u.setEmail(document.getString("email"));
                                    u.setId_foto(document.getLong("id_foto").intValue());
                                    u.setId_unidade(document.getLong("id_unidade").intValue());
                                    u.setId_usuario(document.getLong("id_usuario").intValue());
                                    u.setLogin(document.getString("login"));
                                    u.setSenha(document.getString("senha"));
                                    u.setNome_pessoa(document.getString("nome_pessoa"));
                                    u.setUrl_foto(document.getString("url_foto"));
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
