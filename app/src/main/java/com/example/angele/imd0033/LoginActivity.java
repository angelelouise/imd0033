package com.example.angele.imd0033;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angele.imd0033.Dominio.Usuario;
import com.example.angele.imd0033.api.UsuarioService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    static String sBaseUrl = "https://api.info.ufrn.br";
    static String sAtentication = "https://autenticacao.info.ufrn.br";
    private static class ViewHolder{

        private TextView mLoginView;
        private EditText mPasswordView;
        private View mProgressView;
        private View mLoginFormView;
    }
    private Usuario usuario_principal;
    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        mViewHolder.mLoginView = findViewById(R.id.login);

        mViewHolder.mPasswordView = findViewById(R.id.password);
        mViewHolder.mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    tentativaLogin();
                    return true;
                }
                return false;
            }
        });

        Button mSignInButton = findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tentativaLogin();
            }
        });

        mViewHolder.mLoginFormView = findViewById(R.id.login_form);
        mViewHolder.mProgressView = findViewById(R.id.login_progress);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void tentativaLogin() {

        // Reset errors.
        mViewHolder.mLoginView.setError(null);
        mViewHolder.mPasswordView.setError(null);

        // Armazena valores.
        String login = mViewHolder.mLoginView.getText().toString();
        String password = mViewHolder.mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mViewHolder.mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mViewHolder.mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(login)) {
            mViewHolder.mLoginView.setError(getString(R.string.error_field_required));
            focusView = mViewHolder.mLoginView;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            UserLoginTask(login, password);
        }
    }

    private boolean isPasswordValid(String password) {
        return password.equals(mViewHolder.mPasswordView.toString());
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mViewHolder.mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mViewHolder.mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mViewHolder.mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mViewHolder.mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mViewHolder.mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mViewHolder.mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    private Retrofit ConnectRetrofit (String url){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    private boolean AtenticarAPIUFRN(){
        boolean status = false;
        Retrofit retrofit = ConnectRetrofit(sAtentication);



        return status;
    }
    private void UserLoginTask (String login, final String password){

        String mLogin = login;
        final String mPassword = password;

        Retrofit retrofit = ConnectRetrofit(sBaseUrl);

        final UsuarioService service = retrofit.create(UsuarioService.class);

        final Call<Usuario> listarUsuario = service.UsuarioDTO(mLogin); //retorna o usuário do servico

        listarUsuario.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                //verifica se a senha foi digitada corretamente
                assert response.body() != null;
                if (isPasswordValid(response.body().getSenha())){
                    if (popular_dados(response.body())){
                        //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        //intent.putExtra(Usuario.USUARIO_INFO, usuario_principal);
                        //startActivity(intent);
                    }
                }else{
                    showProgress(false);
                    mViewHolder.mPasswordView.setError(getString(R.string.error_incorrect_password));
                    mViewHolder.mPasswordView.requestFocus();
                    /*Toast.makeText(LoginActivity.this,
                            "Senha ou usuário incorretos",
                            Toast.LENGTH_SHORT).show();*/
                }


            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                showProgress(false);
                mViewHolder.mPasswordView.setError(getString(R.string.error_invalid_user));
                mViewHolder.mPasswordView.requestFocus();
                /*Toast.makeText(LoginActivity.this,
                        "Usuario não existe",
                        Toast.LENGTH_SHORT).show();*/
            }
        });

    }
    private boolean popular_dados(Usuario user){
        usuario_principal= new Usuario(user.isAtivo(),user.getChave_foto(),user.getCpf_cnpj(),user.getEmail(),user.getId_foto(),
                user.getId_unidade(),user.getId_usuario(),user.getLogin(),user.getSenha(),user.getNome_pessoa(),user.getUrl_foto());

        return true;
    }
}

