package com.example.angele.imd0033;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.angele.imd0033.dominio.ComponenteCurricular;
import com.example.angele.imd0033.view.CCAdapter;
import com.example.angele.imd0033.view.ComponenteViewModel;

import java.util.ArrayList;
import java.util.List;

public class SeguirCCActivity extends AppCompatActivity {
    public static int ACAO_ADD = 1;
    private List<ComponenteCurricular> componentes;
    private ComponenteViewModel componenteViewModel;
    public static int NOVA_CC = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguir_cc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),CadastroCCurricularActivity.class);
                startActivityForResult(intent,ACAO_ADD);

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_cc);
        componentes = new ArrayList<ComponenteCurricular>();

        final CCAdapter adapter = new CCAdapter(componentes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        componenteViewModel = ViewModelProviders.of(this).get(ComponenteViewModel.class);
        componenteViewModel.getListaComponentes().observe(this, new Observer<List<ComponenteCurricular>>() {
            @Override
            public void onChanged(@Nullable List<ComponenteCurricular> post) {
                //postagemAdapter.setPalavras(post);
                Log.d("post","post" +post);
                componentes.clear();
                componentes.addAll(post);
                adapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode  == RESULT_OK && requestCode == NOVA_CC){

            ComponenteCurricular p = new ComponenteCurricular();
            p = (ComponenteCurricular) data.getExtras().getSerializable(CadastroCCurricularActivity.INFO_EXTRA);
            //p.setId_postagem((long) 0);
            //p.setId_usuario((long) usuario.getId_usuario());
            //p.setUsuario(usuario.getNome_pessoa());
            componenteViewModel.inserir(p);
        }else{
            Toast.makeText(getApplicationContext(), "Deu ruim!",Toast.LENGTH_SHORT );
        }
    }


}
