package com.example.angele.imd0033;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.angele.imd0033.dominio.Postagem;
import com.example.angele.imd0033.dominio.Usuario;
import com.example.angele.imd0033.view.PostagemAdapter;
import com.example.angele.imd0033.view.PostagemViewModel;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private PostagemViewModel postagemViewModel;
    List<Postagem> postagens;
    PostagemAdapter postagemAdapter;
    RecyclerView recList;
    private Usuario usuario = new Usuario();
    public static int NOVA_POSTAGEM = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(MainActivity.this, CadastroPostagemActivity.class);
                startActivityForResult(intent, NOVA_POSTAGEM);
            }
        });

        //receber o usuário do login
        Intent receberUsuarioLogin = getIntent();
        usuario = (Usuario) receberUsuarioLogin.getExtras().get(usuario.USUARIO);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Firebase FMC
        FirebaseMessaging.getInstance().subscribeToTopic("postagens");

        recList = findViewById(R.id.recycler_postagens);
        postagens = new ArrayList<Postagem>();
        postagemAdapter = new PostagemAdapter(MainActivity.this, postagens);
        recList.setAdapter(postagemAdapter);
        recList.setLayoutManager(new LinearLayoutManager(this));

        postagemViewModel = ViewModelProviders.of(this).get(PostagemViewModel.class);
        postagemViewModel.getListaPostagem().observe(this, new Observer<List<Postagem>>() {
            @Override
            public void onChanged(@Nullable List<Postagem> post) {
                //postagemAdapter.setPalavras(post);
                Log.d("post","post" +post);
                postagens.clear();
                postagens.addAll(post);
                postagemAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode  == RESULT_OK && requestCode == NOVA_POSTAGEM){

            Postagem p = new Postagem();
            p = (Postagem) data.getExtras().getSerializable(CadastroPostagemActivity.INFO_EXTRA);
            //p.setId_postagem((long) 0);
            p.setId_usuario((long) usuario.getId_usuario());
            p.setUsuario(usuario.getNome_pessoa());
            postagemViewModel.inserir(p);
        }else{
            Toast.makeText(getApplicationContext(), "Deu ruim!",Toast.LENGTH_SHORT );
        }
    }
}
