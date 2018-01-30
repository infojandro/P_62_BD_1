package com.pdm.p_62_bd_1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SegundaActivity extends AppCompatActivity {

    private int identificador;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        if (getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        identificador=getIntent().getExtras().getInt("enviado");
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdminSQLite admin = AdminSQLite.getInstance (this, getString(R.string.nombreBD), null, 1);
        db = admin.getWritableDatabase();
        Cursor fila =db.rawQuery("select nombre,tfno,email from agenda where _id="+ identificador , null);
        if (fila.moveToFirst()) {
            String nombre=fila.getString(0);
            if (getSupportActionBar()!=null) {
                getSupportActionBar().setTitle(String.format(getString(R.string.titulo_activity), nombre));
            }
            final String tfno="tel:"+fila.getString(1);
            final String email=fila.getString(2);

            FloatingActionButton fab_llamar =  findViewById(R.id.fab2);
            fab_llamar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(tfno));
                    startActivity(intent);
                }
            });

            FloatingActionButton fab_email =  findViewById(R.id.fab);
            fab_email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    intent.putExtra(Intent.EXTRA_EMAIL,new String[] { email });
                    intent.putExtra(Intent.EXTRA_SUBJECT,"Email Subject");
                    intent.putExtra(Intent.EXTRA_TEXT,"Email Body");
                    startActivity(intent);
                }
            });
        }
        fila.close();
        db.close();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    db.close();
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Borrar) {
            DialogFragment nuevoFragmento = MiDialogo.newInstance(identificador);
            nuevoFragmento.show(getSupportFragmentManager(), "dialogo");
        }else{
            Intent intent=new Intent(getApplicationContext(),TerceraActivity.class);
            intent.putExtra("ident",identificador);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
