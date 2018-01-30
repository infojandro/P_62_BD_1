package com.pdm.p_62_bd_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AnadirFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public AnadirFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_anadir, container, false);
        final EditText nombre=view.findViewById(R.id.editText);
        final EditText email= view.findViewById(R.id.editText2);
        final EditText tfno= view.findViewById(R.id.editText3);
        final EditText fecha= view.findViewById(R.id.editText4);
        fecha.setText(getDateTime());
        Button button= view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreTecleado=nombre.getText().toString();
                if (nombreTecleado.isEmpty()){
                    Toast.makeText(getContext(),"Nombre no válido",Toast.LENGTH_SHORT).show();
                    nombre.setFocusable(true);
                }
                else {
                    AdminSQLite admin = AdminSQLite.getInstance(getContext(), getString(R.string.nombreBD), null, 1);
                    SQLiteDatabase bd = admin.getWritableDatabase();
                    ContentValues registro = new ContentValues();
                    registro.put("nombre", nombreTecleado);
                    registro.put("email", email.getText().toString());
                    registro.put("tfno", tfno.getText().toString());
                    registro.put("fecha", getDateTime());
                    bd.insert("agenda", null, registro);
                    bd.close();
                    nombre.setText("");
                    nombre.setFocusable(true);
                    email.setText("");
                    tfno.setText("");
                    fecha.setText(getDateTime());
                    mListener.onRegistroNuevo();
                }
            }
        });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onRegistroNuevo();
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
