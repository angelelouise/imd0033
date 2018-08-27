package com.example.angele.imd0033;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.angele.imd0033.bd.UsuarioRepository;
import com.example.angele.imd0033.dominio.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {
    private ViewHolder mViewHolder = new ViewHolder();
    private UsuarioRepository usuarioRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        mViewHolder.mCadastroView = findViewById(R.id.buttonCadastarUser);
        mViewHolder.mCadastroView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarUsuario();
                finish();
            }
        });
        usuarioRepository=  new UsuarioRepository(getApplication());
    }
    private static class ViewHolder{

        private EditText mLoginView;
        private EditText mPasswordView;
        private EditText mEmailView;
        private EditText mCPFView;
        private EditText mNomeView;
        private Button mCadastroView;
    }
    private boolean cadastrarUsuario(){
        boolean cadastrou = false;

        mViewHolder.mLoginView = findViewById(R.id.inputLogin);
        mViewHolder.mPasswordView = findViewById(R.id.inputSenha);
        mViewHolder.mEmailView = findViewById(R.id.inputEmail);
        mViewHolder.mCPFView = findViewById(R.id.inputCPF);
        mViewHolder.mNomeView = findViewById(R.id.inputNomeUsuario);

        Usuario user = new Usuario();
        user.setAtivo(true);
        user.setLogin(mViewHolder.mLoginView.getText().toString());
        user.setSenha(mViewHolder.mPasswordView.getText().toString());
        user.setCpf_cnpj(Integer.parseInt(mViewHolder.mCPFView.getText().toString()));
        user.setEmail(mViewHolder.mEmailView.getText().toString());
        user.setNome_pessoa(mViewHolder.mNomeView.getText().toString());

        usuarioRepository.inserir(user);
        cadastrou = true;
        return cadastrou;
    }
}
