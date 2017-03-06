package com.frontend.gato_goku.proyectofrontendfinal.localdb;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.frontend.gato_goku.proyectofrontendfinal.models.Delegacion;

public class DatabaseContentProvider extends ContentProvider {

    // We set uriMatcher to get params passed to URIs.
    // So we can give different values depending on those params
    private  UriMatcher uriMatcher;
    // Our data:
    private MatrixCursor mCursor;
    private com.frontend.gato_goku.proyectofrontendfinal.localdb.DbAdapter dbAdapter;
    Delegacion delegacion = new Delegacion();
    private SQLiteHelper database;
    // used for the UriMacher
    private static final int TODOS = 1;
    private static final int TODO_ID = 20;

    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);
    public DatabaseContentProvider() {}
    /**
     * called when provider is started, so we use it to initialize data.
     */
    @Override
    public boolean onCreate() {
        dbAdapter = new com.frontend.gato_goku.proyectofrontendfinal.localdb.DbAdapter(this.getContext());
        dbAdapter.open();
        initUris();
        return true;
    }
    /**
     * init content provider Uris
     * we set some kind of uri patterns to route them to different queries
     */
    private void initUris() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // This will match: content://io.pello.android.androidsyncadapter.sqlprovider.Todo/tasks
        uriMatcher.addURI("com.frontend.gato_goku.proyectofrontendfinal.sqlprovider", "DELEGACIONES/", 1);
        // This will match: content://io.pello.android.androidsyncadapter.sqlprovider.Todo/task/2
        uriMatcher.addURI("com.frontend.gato_goku.proyectofrontendfinal.sqlprovider", "DELEGACION/", 2);
        // the last one from the backend// This will match: content://io.pello.android.androidsyncadapter.sqlprovider.Todo/tasks/last/backend
        uriMatcher.addURI("com.frontend.gato_goku.proyectofrontendfinal.sqlprovider", "DELEGACION/last/backend", 3);
        // This will match: content://io.pello.android.androidsyncadapter.sqlprovider.Todo/tasks/last/local
        uriMatcher.addURI("com.frontend.gato_goku.proyectofrontendfinal.sqlprovider", "DELEGACION/last/local", 4);

    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
        Log.d("PELLODEBUG","CP> query " + uri+ " match:" + uriMatcher.match(uri));
        switch (uriMatcher.match(uri)) {
            case 1:
                Log.d("PELLODEBUG","query to 1. ");
                //mCursor = dbAdapter.obtenerDelegaciones();
                return dbAdapter.obtenerDelegaciones();
            case 2:
                Log.d("PELLODEBUG","query to 2. " + uri.getLastPathSegment());
                return dbAdapter.obtenerDelegacion(Long.parseLong(selectionArgs[0]));
            case 3:
                Log.d("PELLODEBUG","query to 3. " + uri.getLastPathSegment());
                return dbAdapter.obtenerUltimaTareaBackend();
            case 4:
                Log.d("PELLODEBUG","query to 4. " + uri.getLastPathSegment());
                return dbAdapter.obtenerDelegacionLocal();
            default:	break;
        }

       // return dbAdapter.obtenerDelegaciones();
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case TODOS:
                rowsDeleted = sqlDB.delete(String.valueOf(delegacion), selection,
                        selectionArgs);
                break;
            case TODO_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(
                            String.valueOf(delegacion.get_id()),
                            delegacion.get_id() + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(
                            String.valueOf(delegacion.get_id()),
                           delegacion.get_id()+ "=" + id
                                    + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;

    }

    @Override
    public String getType(Uri uri) {
        Log.d("PELLODEBUG","CP> " + uri);
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Delegacion delegacion = new Delegacion(
                // values.getAsLong("_id"),
                0,
                values.getAsString("nombre"),
                values.getAsString("descripcion"),
                values.getAsString("direccion")
      );

        dbAdapter.insertarDelegacion(delegacion);
        Uri resultUri = Uri.parse("com.frontend.gato_goku.proyectofrontendfinal.sqlprovider");
        return resultUri;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d("PELLODEBUG","CP> " + uri);
        return dbAdapter.marcarComoEnviadasABackend();

    }

}

