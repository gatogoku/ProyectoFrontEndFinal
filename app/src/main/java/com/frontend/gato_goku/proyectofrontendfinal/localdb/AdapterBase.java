package com.frontend.gato_goku.proyectofrontendfinal.localdb;

import android.app.Activity;
import android.content.Context;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.frontend.gato_goku.proyectofrontendfinal.R;
import com.frontend.gato_goku.proyectofrontendfinal.models.Delegacion;

import java.util.ArrayList;


public class AdapterBase extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Delegacion> clases;

    public AdapterBase(Activity activity, ArrayList<Delegacion> clases) {
        super();
        this.activity = activity;
        this.clases = clases;


    }

    @Override
    public int getCount() {
        return clases.size();
    }

    public void clear() {
        clases.clear();
    }

    public void addAll(ArrayList<Delegacion> persons2Add) {
        for (int i = 0; i < persons2Add.size(); i++) {
            clases.add(persons2Add.get(i));
        }
    }

    @Override
    public Object getItem(int i) {
        return clases.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        int icons[] = {};
        if (view == null) {
           LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, null);
            Delegacion clase = clases.get(i);
            TextView name = (TextView) v.findViewById(R.id.textViewNombre);
          name.setText(clase.getNombre());
            TextView details = (TextView) v.findViewById(R.id.textViewDescripcion);
            details.setText(clase.getDescription());
           // ImageView picture = (ImageView) v.findViewById(R.id.imageViewIcon);
           //picture.setImageResource(clase.getImagen());
        }
        return v;
    }



  /*  public void rellenarListView() {
        // Hacemos la consulta y obtenemos el cursor
        DbAdapter db = new DbAdapter(activity);
        Cursor cursor = db.obtenerTareas();
        // que apunta a los datos

        // Decimos qué campos queremos sacar
        String[] campos = new String[] {"_id","nombre","descripcion","direccion"};

        // Decimos dónde cargaremos los datos en cada item de la lista
        int[] dondeMostrarCampos = new int[] {R.id.textViewId, R.id.textViewNombre,R.id.textViewDescripcion };

        // Crea un adaptador para poder mostrar los datos en el ListView.
        SimpleAdapter tareas = new SimpleAdapter(activity,null,R.id.textViewId,campos,dondeMostrarCampos);

        // Asigna el adaptador al ListView.
        ListView listaDatos = (ListView) activity.findViewById(R.id.lista);
        listaDatos.setAdapter(tareas);

        // Le asociamos un listener para saber cuál clickamos
        listaDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // 	Sacamos el registro de la posición que han seleccionado (arg2)
                Cursor elementoSeleccionado = (Cursor) arg0.getItemAtPosition(arg2);
            }
        });


    }*/


  public   void rellenarListView() {
        // Hacemos la consulta y obtenemos el cursor
        // que apunta a los datos
        DbAdapter db = new DbAdapter(activity);
      db.open();
      db.insertarTarea(new Delegacion(3,"NOMRE","DESCRIPTION","DIRECION"));
        Cursor cursor = db.obtenerTareas();
        // que apunta a los datos



        // Prepara el cursor para su uso.
       activity.startManagingCursor(cursor);

        // Decimos qué campos queremos sacar
        String[] campos = new String[] {"_id","nombre","descripcion"};



        int[] dondeMostrarCampos = new int[] {R.id.textViewId, R.id.textViewNombre,R.id.textViewDescripcion };

        // Crea un adaptador para poder mostrar los datos en el ListView.
        //SimpleAdapter tareas = new SimpleAdapter(activity,null,R.id.textViewId,campos,dondeMostrarCampos);

      ListView listaDatos = (ListView) activity.findViewById(R.id.lista);

        // Crea un adaptador para poder mostrar los datos en el ListView.
        SimpleCursorAdapter tareas = new SimpleCursorAdapter(activity,R.layout.list_item, cursor, campos,dondeMostrarCampos);


        // Asigna el adaptador al ListView.
        listaDatos.setAdapter(tareas);

        // Le asociamos un listener para saber cuál clickamos
        listaDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // 	Sacamos el registro de la posición que han seleccionado (arg2)
                Cursor elementoSeleccionado = (Cursor) arg0.getItemAtPosition(arg2);


            }
        });

        Log.d("DEBUG","Lista cargada desde BD");
    }















}