package com.pdm.p_62_bd_1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class MiDialogo extends DialogFragment {

    public static DialogFragment newInstance(int identificador) {
        MiDialogo newInstance = new MiDialogo();
        Bundle args = new Bundle();
        args.putInt("ident", identificador);
        newInstance.setArguments(args);
        return newInstance;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final int identificador = getArguments().getInt("ident");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dial_titulo)
                .setMessage(R.string.dial_mensaje)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                AdminSQLite admin = AdminSQLite.getInstance(getContext(), getString(R.string.nombreBD), null, 1);
                                SQLiteDatabase db = admin.getWritableDatabase();
                                int cant = db.delete("agenda", "_id=" + identificador, null);
                                db.close();
                                if (cant > 0)
                                    Toast.makeText(getActivity(), "Contacto borrado", Toast.LENGTH_LONG).show();
                                getActivity().finish();
                            }
                        })
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getActivity(), R.string.dial_no, Toast.LENGTH_LONG).show();
                            }
                        });
        return builder.create();
    }


}
