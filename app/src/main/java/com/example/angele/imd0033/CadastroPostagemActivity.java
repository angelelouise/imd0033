package com.example.angele.imd0033;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.angele.imd0033.dominio.Postagem;

import java.io.Serializable;

public class CadastroPostagemActivity extends AppCompatActivity{
    public static String INFO_EXTRA = "nova_postagem";
    private ViewHolder mViewHolder = new ViewHolder();



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

        mViewHolder.mCadastrarNovaPostagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Postagem  p = new Postagem();
                p.setDescricao(mViewHolder.mDescricaoView.getText().toString());
                p.setTitulo(mViewHolder.mDescricaoView.getText().toString());
                p.setId_componente_curricular((long) 0);
                intent.putExtra(INFO_EXTRA, (Serializable) p);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }



}
