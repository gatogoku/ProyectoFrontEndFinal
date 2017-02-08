package com.frontend.gato_goku.proyectofrontendfinal;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.frontend.gato_goku.proyectofrontendfinal.localdb.DbAdapter;
import com.frontend.gato_goku.proyectofrontendfinal.models.Delegacion;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void insertar(View v){

        EditText nombre = (EditText) findViewById(R.id.editTextNombre);
        EditText descripcion = (EditText) findViewById(R.id.editTextDescripcion);
        EditText direccion = (EditText) findViewById(R.id.editTextDireccion);

        String nom = String.valueOf(nombre.getText());
        String des = String.valueOf(descripcion.getText());
        String dir = String.valueOf(direccion.getText());


        DbAdapter db = new DbAdapter(this);
        db.open();
        db.insertarTarea(new Delegacion(0,nom,des,dir));
        Cursor cursor = db.obtenerTareas();

    }

}
