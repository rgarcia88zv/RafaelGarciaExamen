package com.example.rafaelgarciaexamen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

public class UpdateActivity extends AppCompatActivity {

   TextInputLayout etNombre, etApellido, etFecha, etTelefono, etCalle, etNum;
    Button btGuardar, btBorrar;
    Context context;

    private AgendaDao dao;
    private AgendaDB database;
    Agenda agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        agenda = (Agenda) getIntent().getSerializableExtra("elegido");

        init();
    }

    private void init() {

        database = AgendaDB.getDb(this);
        dao = database.getAgendaDao();

        etNombre = findViewById(R.id.etNombreED);
        etApellido = findViewById(R.id.etApellidosED);
        etFecha = findViewById(R.id.etFechaNacED);
        etTelefono = findViewById(R.id.etTelfED);
        etCalle = findViewById(R.id.etCalleED);
        etNum = findViewById(R.id.etNumED);

        etNombre.getEditText().setText(agenda.getNombre());
        etApellido.getEditText().setText(agenda.getApellidos());
        etFecha.getEditText().setText(agenda.getFecNac());
        etTelefono.getEditText().setText(String.valueOf(agenda.getTelefono()));
        etCalle.getEditText().setText(agenda.getCalle());
        etNum.getEditText().setText(String.valueOf(agenda.getNumero()));

        btGuardar = findViewById(R.id.btSaveED);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    String nombre = etNombre.getEditText().getText().toString();
                    String apellido = etApellido.getEditText().getText().toString();
                    String fecha = etFecha.getEditText().getText().toString();
                    int telf = Integer.parseInt(etTelefono.getEditText().getText().toString());
                    String calle = etCalle.getEditText().getText().toString();
                    int num = Integer.parseInt(etNum.getEditText().getText().toString());

                    Agenda a = new Agenda(nombre, apellido, telf, fecha, calle, num);
                    a.setId(agenda.getId());


                    Thread hebra = new Thread() {

                        @Override
                        public void run() {


                            for (int i = 0; i < dao.getAll().size(); i++) {

                                if (a.getId() == dao.getAll().get(i).getId()) {
                                    dao.update(a);
                                    break;
                                }

                            }
                        }
                    };

                    hebra.start();

                    Toast.makeText(getBaseContext(), "Contacto actualizado correctamente", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);

                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Los campos numéricos deben estar correctamente rellenos",Toast.LENGTH_SHORT).show();

                }

            }
        });

        btBorrar= findViewById(R.id.btEliminar);

        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Thread hebra = new Thread(){
                            @Override
                            public void run() {

                                for (int i = 0; i <  dao.getAll().size(); i++) {
                                    if(dao.getAll().get(i).getId()==agenda.getId()){
                                        dao.delete(dao.getAll().get(i));
                                        break;
                                    }
                                }
                            }
                        };
                        hebra.start();
                        Toast.makeText(getBaseContext(),"Contacto Borrado Correctamente",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getBaseContext(),MainActivity.class);
                        startActivity(i);

                    }
                });

            }


    }

