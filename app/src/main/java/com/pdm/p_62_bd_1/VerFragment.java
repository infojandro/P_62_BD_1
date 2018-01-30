package com.pdm.p_62_bd_1;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class VerFragment extends Fragment {


    public VerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_ver, container, false);
        ArrayList<Item> datos = leerDatos();
        RecyclerView recView =  view.findViewById(R.id.recyclerView);
        recView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recView.setLayoutManager(layoutmanager);
        MiAdaptador miAdaptador=new MiAdaptador(datos,getContext());
        recView.setAdapter(miAdaptador);
        return view;
    }

    private ArrayList<Item> leerDatos() {
        ArrayList<Item> datos = new ArrayList<>();
        AdminSQLite admin = AdminSQLite.getInstance(getContext(), getString(R.string.nombreBD),null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila =bd.rawQuery("select _id,nombre from agenda", null);
        while (fila.moveToNext()){
            datos.add(new Item(fila.getString(1),Integer.valueOf(fila.getString(0))));
        }
        fila.close();
        bd.close();
        return datos;
    }

}
