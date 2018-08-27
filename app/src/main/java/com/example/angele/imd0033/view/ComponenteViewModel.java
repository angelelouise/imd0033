package com.example.angele.imd0033.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.angele.imd0033.bd.CCurricularRepository;
import com.example.angele.imd0033.dominio.ComponenteCurricular;

import java.util.List;

public class ComponenteViewModel extends AndroidViewModel{
    private CCurricularRepository curricularRepository;
    private LiveData<List<ComponenteCurricular>> listaComponentes;
    private LiveData<ComponenteCurricular> componente;

    public ComponenteViewModel (Application application){
        super(application);
        curricularRepository  = new CCurricularRepository(application);
        listaComponentes = curricularRepository.buscarTodas();
    }

    public void inserir (ComponenteCurricular componenteCurricular){
        curricularRepository.inserir(componenteCurricular);
    }

    public LiveData<List<ComponenteCurricular>> getListaComponentes() {
        return listaComponentes;
    }

    public LiveData<ComponenteCurricular> getComponente() {
        return componente;
    }
}
