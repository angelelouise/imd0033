package com.example.angele.imd0033;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.TextView;

public class CadastroCCurricularActivity extends AppCompatActivity {
    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_ccurricular);
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
