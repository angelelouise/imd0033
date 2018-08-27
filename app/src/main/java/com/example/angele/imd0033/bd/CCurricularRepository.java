package com.example.angele.imd0033.bd;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.example.angele.imd0033.bd.firestore.CCurricularDAOFirestore;
import com.example.angele.imd0033.dominio.ComponenteCurricular;

import java.util.List;

public class CCurricularRepository {
    private LiveData<List<ComponenteCurricular>> listaCC;
    private LiveData<ComponenteCurricular> curricular;
    private CCurricularDAO curricularDAO;
    private CCurricularDAOFirestore curricularDAOFirestore;
    private ConnectivityManager cm;

    public  CCurricularRepository(Application app){
        curricularDAO = CCurricularBD.getInstance(app).curricularDAO();
        curricularDAOFirestore = new CCurricularDAOFirestore();
        cm =(ConnectivityManager)app
                .getSystemService(app.CONNECTIVITY_SERVICE);
    }

    public void inserir (ComponenteCurricular componenteCurricular){
        new InsertASync(curricularDAO).execute(componenteCurricular);
        curricularDAOFirestore.inserir(componenteCurricular);
    }
    public LiveData<List<ComponenteCurricular>> buscarTodas() {
        if(netOn()){
            listaCC = curricularDAOFirestore.buscarTodas();
        }else{
            listaCC = curricularDAO.buscarTodas();
        }
        return listaCC;
    }
    private class InsertASync extends AsyncTask<ComponenteCurricular, Void, Void> {
        private CCurricularDAO curricularDAO;

        public InsertASync (CCurricularDAO curricularDAO){
            this.curricularDAO= curricularDAO;
        }
        @Override
        protected Void doInBackground(ComponenteCurricular... componentes){
            try{
                curricularDAO.inserir(componentes[0]);
            }catch (Exception e){
                Log.e(PostagemRepository.class.getName(), "ERRO", e);
            }

            return null;
            //Postagem p = postagens[0];
            //if(p.getId_postagem() == 0)
            //postagemDAO.inserir(p);
            //else
            //postagemDAO.atualizar(p);
            //return null;
        }
    }
    private boolean netOn(){
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
