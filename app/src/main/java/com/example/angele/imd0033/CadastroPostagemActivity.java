package com.example.angele.imd0033;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.angele.imd0033.dominio.ComponenteCurricular;
import com.example.angele.imd0033.dominio.Postagem;
import com.example.angele.imd0033.view.ComponenteViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CadastroPostagemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static String INFO_EXTRA = "nova_postagem";
    private ViewHolder mViewHolder = new ViewHolder();

    private ComponenteViewModel componenteViewModel;
    private List<ComponenteCurricular> componentes;
    Postagem  p = new Postagem();

    private static class ViewHolder{
        TextView mTituloView;
        TextView mDescricaoView;
        Spinner mCCView;
        ImageButton mCadastrarNovaPostagem;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_postagem);

        mViewHolder.mTituloView = findViewById(R.id.inputTitulo);
        mViewHolder.mDescricaoView = findViewById(R.id.inputDescricao);
        mViewHolder.mCCView = findViewById(R.id.spinnerCC);
        mViewHolder.mCadastrarNovaPostagem = findViewById(R.id.imageButtonSend);



        componentes = new ArrayList<ComponenteCurricular>();
        componenteViewModel = ViewModelProviders.of(this).get(ComponenteViewModel.class);
        componenteViewModel.getListaComponentes().observe(this, new Observer<List<ComponenteCurricular>>() {
            @Override
            public void onChanged(@Nullable List<ComponenteCurricular> post) {
                //postagemAdapter.setPalavras(post);
                Log.d("post","post" +post);
                componentes.clear();
                componentes.addAll(post);
            }
        });
        // Creating adapter for spinner
        ArrayAdapter<ComponenteCurricular> dataAdapter = new ArrayAdapter<ComponenteCurricular>(this, android.R.layout.simple_spinner_item, componentes);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mViewHolder.mCCView.setAdapter(dataAdapter);
        mViewHolder.mCadastrarNovaPostagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                p.setDescricao(mViewHolder.mDescricaoView.getText().toString());
                p.setTitulo(mViewHolder.mDescricaoView.getText().toString());
                //p.setId_componente_curricular((long) 0);
                intent.putExtra(INFO_EXTRA, (Serializable) p);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        ComponenteCurricular c = new ComponenteCurricular();
        c = (ComponenteCurricular) parent.getItemAtPosition(position);
        p.setId_componente_curricular((long) c.getId_componente());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
