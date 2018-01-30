package com.pdm.p_62_bd_1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText email;
    private EditText tfno;
    private EditText fecha;
    private int identificador;
    private SQLiteDatabase db;
    private AdminSQLite admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        identificador = getIntent().getExtras().getInt("ident");
        admin = AdminSQLite.getInstance (this, getString(R.string.nombreBD), null, 1);
        db = admin.getWritableDatabase();
        final Cursor fila =db.rawQuery("select nombre,tfno,email, fecha from agenda where _id="+ identificador , null);
        if (fila.moveToFirst()) {
            String nombreContacto=fila.getString(0);
            if (getSupportActionBar()!=null) {
                getSupportActionBar().setTitle(String.format(getString(R.string.titulo_activity), nombreContacto));
            }
            nombre= findViewById(R.id.editText);
            nombre.setText(nombreContacto);
            email= findViewById(R.id.editText2);
            email.setText(fila.getString(2));
            tfno= findViewById(R.id.editText3);
            tfno.setText(fila.getString(1));
            fecha= findViewById(R.id.editText4);
            fecha.setText(fila.getString(3));
            fila.close();
            db.close();
            Button button=findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (nombre.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(),"Nombre no válido",Toast.LENGTH_SHORT).show();
                        nombre.setFocusable(true);
                    }
                    else{
                        admin = AdminSQLite.getInstance (getApplicationContext(), getString(R.string.nombreBD), null, 1);
                        db = admin.getWritableDatabase();
                        ContentValues registro=new ContentValues();
                        registro.put("nombre", nombre.getText().toString());
                        registro.put("email", email.getText().toString());
                        registro.put("tfno", tfno.getText().toString());
                        registro.put("fecha",fecha.getText().toString());
                        int cant = db.update("agenda", registro, "_id="+identificador, null);
                        if (cant==1)
                            Toast.makeText(getApplicationContext(), "Datos modificados", Toast.LENGTH_LONG).show();
                        db.close();
                        finish();
                    }
                }
            });
        }
    }
}
