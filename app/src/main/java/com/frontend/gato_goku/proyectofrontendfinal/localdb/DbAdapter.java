package com.frontend.gato_goku.proyectofrontendfinal.localdb;

/**
 * Created by PELLO_ALTADILL on 13/12/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.frontend.gato_goku.proyectofrontendfinal.models.Delegacion;

/**
 * DBAdapter
 * Esta es una clase intermediaria entre nuestro Activity y
 * la BBDD. Aquí meteremos todas las operaciones CRUD sobre
 * los datos
 * @author Pello Altadill
 *
 */
public class DbAdapter {

    // Este objeto nos permite meterle mano a SQLite
    private SQLiteDatabase db;

    // Aquí tenemos nuestro SqliteHelper que se encarga de crear y actualizar
    private SQLiteHelper dbHelper;

    // El contexto nos servirá para referirnos a la Activity en la que estamos
    private final Context contexto;

    /**
     * DbAdapter
     * Constructor de la clase
     * @param contexto Será la activity que usa esta clase
     */
    public DbAdapter(Context contexto) {
        this.contexto = contexto;
    }


    /**
     * open
     * Usa el SqLiteHelper para encargase de abrir la conexión.
     * El SqLiteHelper lo primero que hará es crear la BD si no existía.
     * @return Devuelve un objeto de clase SQLiteDatabase para gestionar la BD
     * @throws SQLException
     */
    public SQLiteDatabase open() throws SQLException {
        // Crea un objeto asistente de base de datos de clase SqLiteHelper.
        dbHelper = new SQLiteHelper(contexto);

        // Abre la base de datos en modo escritura (lectura permitida).
        db = dbHelper.getWritableDatabase();

        Log.d("DEBUG","BD obtenida: " + db.toString());

        // Devuelve el objeto de tipo SQLiteDatabase.
        return db;
    }

    /**
     * close
     * Cierra la base de datos mediante el dbHelper
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * insertarTarea
     * Inserta un registro con los campos nombre y cuerpo en la base de datos.
     *
     * @param
     * @return Devuelve el número de registro insertado 0 -1 en caso de error
     */
    public long insertarTarea(Delegacion clase) {
        // Creamos un registro
        ContentValues registro = new ContentValues();

        // Agrega los datos.
        registro.put("nombre", clase.getNombre());
        registro.put("descripcion", clase.getDescription());
       // registro.put("imagen", clase.getImagen());


        // Inserta el registro y devuelve el resultado.
        return db.insert("DELEGACION", null, registro);
    }

    /**
     * borrarTarea
     * Borra el registro que tiene el id especificado.
     *
     * @param idRegistro id del registro a borrar
     * @return Devuelve el nº de registros afectados.
     */
    public int borrarTarea(long idRegistro) {
        return db.delete("DELEGACION",  "_id = "
                + idRegistro, null);
    }

    /**
     * obtenerTareas
     * Obtiene todos los registros de la tabla todo.
     *
     * @return Cursor Devuelve un cursor con los registros obtenidos.
     */
    public Cursor obtenerTareas() {
      return db.query("DELEGACION", new String[] {"_id","nombre","descripcion","direccion"}, null, null, null, null, null);
     //  return db.rawQuery("select _id,nombre,descripcion,direccion from DELEGACION",null);
    }

    /**
     * obtenerTarea
     * Obtiene el registro que tiene el id especificado.
     *
     * @param idRegistro id del registro que se quiere obtener.
     * @return Cursor un cursor con el registro obtenido.
     * @throws SQLException
     */
    public Cursor obtenerTarea (long idRegistro) throws SQLException {
        Cursor registro = db.query(true, "DELEGACION",new String[] {"_id","nombre","descripcion","direccion"},
                "_id =" + idRegistro, null, null, null, null, null);

        // Si lo ha encontrado, apunta al inicio del cursor.
        if (registro != null) {
            registro.moveToFirst();
        }
        return registro;
    }

    /**
     * actualizarTarea
     * Hace un UPDATE de los valores del registro cuyo id es idRegistro.
     *
     * @param  idRegistro id del registro que se quiere modificar.
     * @param
     * @return int cantidad registros han sido afectados.
     */
    public int actualizarTarea(long idRegistro, Delegacion clase) {
        // Creamos un registro
        ContentValues registro = new ContentValues();

        // Agrega los datos.
        registro.put("nombre", clase.getNombre());
        registro.put("descripcion", clase.getDescription());
      //  registro.put("imagen", clase.getImagen());
        // Inserta el registro y devuelve el resultado.
        return db.update("to", registro,
                "_id=" + idRegistro, null);
    }

}
