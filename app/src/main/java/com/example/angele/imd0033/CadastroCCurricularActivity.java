package com.example.angele.imd0033;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.angele.imd0033.dominio.ComponenteCurricular;

import java.io.Serializable;

public class CadastroCCurricularActivity extends AppCompatActivity {
    private ViewHolder mViewHolder = new ViewHolder();
    public static String INFO_EXTRA = "nova_componente";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_ccurricular);
        mViewHolder.mCCcadastrar = findViewById(R.id.imageButtonCC);
        mViewHolder.mCCPreq = findViewById(R.id.inputPRequisitos);
        mViewHolder.mCCCreq = findViewById(R.id.inputCORequisitos);
        mViewHolder.mCCCh =findViewById(R.id.inputCH);
        mViewHolder.mSemestre= findViewById(R.id.inputSemestre);
        mViewHolder.mDepartamento =findViewById(R.id.inputDepartamento);;
        mViewHolder.mCCNome=findViewById(R.id.inputNome);
        mViewHolder.mEquivalemte= findViewById(R.id.inputEquivalente);
        mViewHolder.mCCCodigo= findViewById(R.id.inputCodigo);

        mViewHolder.mCCcadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponenteCurricular p = new ComponenteCurricular();
                p.setCarga_horaria_total(Integer.parseInt(mViewHolder.mCCCh.getText().toString()));
                p.setPre_requisitos(mViewHolder.mCCPreq.getText().toString());
                p.setCo_requisitos(mViewHolder.mCCCreq.getText().toString());
                p.setSemestre_oferta(Integer.parseInt(mViewHolder.mSemestre.getText().toString()));
                p.setDepartamento(mViewHolder.mDepartamento.getText().toString());
                p.setNome(mViewHolder.mCCNome.getText().toString());
                p.setCodigo(mViewHolder.mEquivalemte.getText().toString());
                p.setCodigo(mViewHolder.mCCCodigo.getText().toString());
                intent.putExtra(INFO_EXTRA, (Serializable) p);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
    private static class ViewHolder{
        TextView mCCNome;
        CheckedTextView mObrigatoria;
        TextView mCCCodigo;
        TextView mCCCh;
        TextView mCCPreq;
        TextView mCCCreq;
        TextView mSemestre;
        TextView mDepartamento;
        TextView mEquivalemte;
        ImageButton mCCcadastrar;
    }
}
