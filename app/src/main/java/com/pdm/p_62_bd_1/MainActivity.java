package com.pdm.p_62_bd_1;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AnadirFragment.OnFragmentInteractionListener {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdminSQLite admin = AdminSQLite.getInstance(this, getString(R.string.nombreBD), null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        if (db != null) {
            db.close();
            viewPager =  findViewById(R.id.viewpager);
            TabLayout tabLayout = findViewById(R.id.tabs);
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            MiFragmentPagerAdapter miFragmentPagerAdapter = new MiFragmentPagerAdapter(getSupportFragmentManager(), getApplicationContext());
            viewPager.setAdapter(miFragmentPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }
        else {
            Toast.makeText(this, "Problemas con Agenda", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRegistroNuevo() {
        viewPager.setAdapter(new MiFragmentPagerAdapter(getSupportFragmentManager(), getApplicationContext()));
        viewPager.setCurrentItem(0);
    }
}
