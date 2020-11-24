package com.example.rafaelgarciaexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class AddActivity extends AppCompatActivity {

    private TextInputLayout etNombre;
    private TextInputLayout etApellidos;
    private TextInputLayout etFechaNac;
    private TextInputLayout etTelf;
    private TextInputLayout etCalle;
    private TextInputLayout etNum;

    Button btAgregar;

    private AgendaDao dao;
    private AgendaDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        init();
    }

    private void init() {

        database = AgendaDB.getDb(this);
        dao = database.getAgendaDao();

        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etFechaNac = findViewById(R.id.etFechaNac);
        etTelf = findViewById(R.id.etTelf);
        etCalle = findViewById(R.id.etCalle);
        etNum = findViewById(R.id.etNum);

        btAgregar = findViewById(R.id.btAdd);

        btAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Agenda agenda = new Agenda();
                    agenda.setNombre(etNombre.getEditText().getText().toString());
                    agenda.setApellidos(etApellidos.getEditText().getText().toString());
                    agenda.setFecNac(etFechaNac.getEditText().getText().toString());
                    agenda.setTelefono(Integer.parseInt(etTelf.getEditText().getText().toString()));
                    agenda.setCalle(etCalle.getEditText().getText().toString());
                    agenda.setNumero(Integer.parseInt(etNum.getEditText().getText().toString()));

                    Thread hebra = new Thread() {
                        @Override
                        public void run() {
                            dao.insert(agenda);
                            Log.v("list", dao.getAll().toString());

                        }
                    };
                    Toast.makeText(getBaseContext(), "Contacto agregado correctamente", Toast.LENGTH_SHORT).show();
                    hebra.start();
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);

                }catch(NumberFormatException e){
                        Toast.makeText(getApplicationContext(),"Los campos numéricos deben estar correctamente rellenos",Toast.LENGTH_SHORT).show();

                }
            }
        });



    }
}