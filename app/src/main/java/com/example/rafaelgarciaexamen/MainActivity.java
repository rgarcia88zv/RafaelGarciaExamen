package com.example.rafaelgarciaexamen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Agenda> listaContactos;
    Button btaAñadir;
    Context context;

    private AgendaDao dao;
    private AgendaDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaContactos = new ArrayList<>();
        init();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            listarElementos();
        }
        initRecyclerView();
    }

    private void init() {
        btaAñadir = findViewById(R.id.btAñadir);

        btaAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddActivity.class);
              startActivity(i);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void listarElementos() {

        database = AgendaDB.getDb(this);
        dao = database.getAgendaDao();

        Thread thread = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < dao.getAll().size() ; i++) {

                    listaContactos.add(dao.getAll().get(i));

                }

            }
        };
         thread.start();





    }

    private void initRecyclerView() {
        RecyclerView miRecycler = findViewById(R.id.mi_recycler);
        RecyclerView.Adapter adapter = new RecyclerViewAdapter(this,listaContactos);
        miRecycler.setAdapter(adapter);
        miRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

}