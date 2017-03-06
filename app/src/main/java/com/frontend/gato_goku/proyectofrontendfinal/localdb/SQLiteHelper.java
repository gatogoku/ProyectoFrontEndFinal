package com.frontend.gato_goku.proyectofrontendfinal.localdb;

/**
 * Created by PELLO_ALTADILL on 13/12/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * SqLiteHelper
 * La clase se encarga de inicializar la BBDD -si no existe-, de actualizarla
 *
 * @author pello xabier altadill
 *
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String NOMBRE_BD = "DELEGACIONES.db";
    public static final int VERSION_BD = 4;
    public static final String SQLCREAR = "create table DELEGACION "+
            " (_id integer primary key autoincrement, " +
            " nombre text not null," + " descripcion text not null," +  " direccion text, " + " id_backend integer, " + "is_read integer );"+
            "insert into DELEGACION (nombre,descripcion,direccion,id_backend,is_read) values('NOMBRE','DESCRIPCION-SQL','direccion',0,0);";


    /**
     * Constructor
     * llama al constructor padre
     * Le pasa el contexto, que se refiere al activity actual
     * @param contexto
     */
    public SQLiteHelper(Context contexto) {
        super(contexto, NOMBRE_BD, null, VERSION_BD);
    }

    /**
     * onCreate
     * Se ejecuta en caso de que la BD no exista
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Se ejecuta la sentencia de creación de la tabla notas.
        db.execSQL("DROP TABLE IF EXISTS DELEGACION");
        db.execSQL(SQLCREAR);
        Log.d("DEBUG","Ok, BD creada");
    }

    /**
     * onUpgrade
     * Se ejecuta de forma automáticamente en caso de que
     * estemos en una nueva versión.
     *
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Sacamos un log para contar lo que ha pasado
        Log.w("SqLiteHelper", "Actualizando desde versión " + oldVersion
                + " a " + newVersion + ", los datos será eliminados");

        // En este caso en el upgrade realmente
        // lo que hacemos es cargarnos lo que hay...
        db.execSQL("DROP TABLE IF EXISTS DELEGACION");

        // ... y lo volvemos a generar
        onCreate(db);
        Log.d("DEBUG","Ok, BD regenerada");
    }

}
