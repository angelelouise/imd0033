package com.example.angele.imd0033;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.angele.imd0033.bd.UsuarioDAO;
import com.example.angele.imd0033.bd.UsuarioRepository;
import com.example.angele.imd0033.bd.firestore.UsuarioDAOFirestore;
import com.example.angele.imd0033.dominio.Usuario;
import com.example.angele.imd0033.api.UsuarioService;
import com.example.angele.imd0033.view.UsuarioViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

public class LoginActivity extends AppCompatActivity {

    static String sBaseUrl = "https://api.info.ufrn.br";
    static String sAtentication = "https://autenticacao.info.ufrn.br";
    private static class ViewHolder{

        private TextView mLoginView;
        private EditText mPasswordView;
        private View mProgressView;
        private View mLoginFormView;
        private Button mCadastroView;
        Button mSignInButton;
    }
    private Usuario usuario_principal;
    private ViewHolder mViewHolder = new ViewHolder();
    private UsuarioViewModel usuarioViewModel;
    private UsuarioRepository usuarioRepository ;
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

        mViewHolder.mSignInButton = findViewById(R.id.sign_in_button);
        mViewHolder.mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                /*0 usuário não existe
                * 1 usuário existe mas senha está errada
                * 2 tudo certo*/
                if (tentativaLogin()==2){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra(usuario_principal.USUARIO, usuario_principal);
                    startActivity(intent);

                }

            }
        });
        mViewHolder.mCadastroView = findViewById(R.id.register_button);
        mViewHolder.mCadastroView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                startActivity(intent);

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
    private int tentativaLogin() {
        int flag =1;
        // Reset errors.
        mViewHolder.mLoginView.setError(null);
        mViewHolder.mPasswordView.setError(null);

        // Armazena valores.
        String login = mViewHolder.mLoginView.getText().toString();
        String password = mViewHolder.mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mViewHolder.mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mViewHolder.mPasswordView;
            cancel = true;

        }else if (TextUtils.isEmpty(login)) {
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
            if (UserLoginTask(login, password)){
                flag=2;
            }else {
                flag=0;
            }

        }
        return flag;
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


    private boolean UserLoginTask (String login, final String password){

        //usuarioViewModel = ViewModelProviders.of(this).get(UsuarioViewModel.class);
        usuarioRepository = new UsuarioRepository(getApplication());
        Boolean conectou =false;

        final String mLogin = login;
        final String mPassword = password;
        Usuario aux = new Usuario();
        aux = usuarioRepository.findByLogin(mLogin);
        //aux.setLogin(usuarioRepository.findByLogin(mLogin).getLogin());
        //aux.setSenha(usuarioRepository.findByLogin(mLogin).getSenha());
        /*aux = new Usuario(
                usuarioRepository.findByLogin(mLogin).isAtivo(),
                usuarioRepository.findByLogin(mLogin).getChave_foto(),
                usuarioRepository.findByLogin(mLogin).getCpf_cnpj(),
                usuarioRepository.findByLogin(mLogin).getEmail(),
                usuarioRepository.findByLogin(mLogin).getId_foto(),
                usuarioRepository.findByLogin(mLogin).getId_unidade(),
                usuarioRepository.findByLogin(mLogin).getId_usuario(),
                usuarioRepository.findByLogin(mLogin).getLogin(),
                usuarioRepository.findByLogin(mLogin).getSenha(),
                usuarioRepository.findByLogin(mLogin).getNome_pessoa(),
                usuarioRepository.findByLogin(mLogin).getUrl_foto());*/

        Log.d(TAG, "aux:" +aux);
        aux.setLogin("admin");
        aux.setSenha("admin");

        if(aux.getLogin()!=null){
            showProgress(false);
            if(aux.getSenha().equals(mPassword)){
                usuario_principal=aux;
                conectou=true;

            }else{
                mViewHolder.mPasswordView.setError(getString(R.string.error_incorrect_password));
                mViewHolder.mPasswordView.requestFocus();
            }
        }else{
            showProgress(false);
            mViewHolder.mPasswordView.setError(getString(R.string.error_invalid_user));
            mViewHolder.mPasswordView.requestFocus();
        }
        return conectou;
    }
    private boolean netOn(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}

