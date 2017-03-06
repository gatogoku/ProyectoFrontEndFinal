package com.frontend.gato_goku.proyectofrontendfinal;

import android.accounts.Account;

import android.accounts.AccountManager;
import android.app.LoaderManager;

import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.frontend.gato_goku.proyectofrontendfinal.authentication.DummyAuthenticator;
import com.frontend.gato_goku.proyectofrontendfinal.syncadapter.SyncAdapter;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

   private TextView textView;
    private String contentUri = "content://com.frontend.gato_goku.proyectofrontendfinal.sqlprovider";
   private  String authority = "com.frontend.gato_goku.proyectofrontendfinal.sqlprovider";
    SimpleCursorAdapter mAdapter;
    private ListView listView;
    Cursor cursor =null;
    private ContentResolver contentResolver;
    private Account account;
    private SwipeRefreshLayout swipeRefreshLayout;
    SyncAdapter syncAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this,NewActivity.class);
startActivity(intent);
            }
        });

        //swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
      Cursor cursor = getContentResolver().query(Uri.parse(contentUri +"/DELEGACIONES"),null,null,null,null);
        listView = (ListView) findViewById(R.id.lista);

        mAdapter = new SimpleCursorAdapter(this, R.layout.list_item, cursor, new String[] {"_id","nombre","descripcion","direccion"},
                new int[] { R.id.textViewId,R.id.textViewNom,R.id.textViewDescripcion,R.id.textViewDireccion}, 0);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //Cursor cursor = getContentResolver().query(Uri.parse( contentUri +"/DELEGACION/id"),null,null,null,null);
               Cursor cursor = (Cursor) adapterView.getAdapter().getItem(position);
                String [] valores ={cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)};
                ArrayList<String> vals = new ArrayList(Arrays.asList(valores));

            // DelegacionAdapter deladapter = new DelegacionAdapter(MainActivity.this,null);
               // deladapter.getView(position,view,adapterView);



                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putStringArrayListExtra("vals",vals);
               startActivity(intent);
            }
        });



      /*  account = CreateSyncAccount(this);
// Simple option, will ahndle everything smartly
        contentResolver = getContentResolver();
        Bundle bundle = new Bundle();
        contentResolver.requestSync(account, authority, bundle);*/


        // Sync Adapter
        account = new Account(DummyAuthenticator.ACCOUNT_NAME, DummyAuthenticator.ACCOUNT_TYPE);
        //account = CreateSyncAccount(this);
        String authority = "com.frontend.gato_goku.proyectofrontendfinal.sqlprovider";

        // Simple option, will ahndle everything smartly
        contentResolver = getContentResolver();
        Bundle bundle = new Bundle();
        contentResolver.requestSync(account, authority, bundle);

        // With intervals
        long interval = 24*60*60; // 12 hours
        contentResolver.addPeriodicSync(account, authority, bundle, 5);
        this.syncNow(null);

        // Listener for the swipe down gesture
      /*  swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i("PELLODEBUG", "Update data");
                        Toast.makeText(MainActivity.this,"Refreshing...",Toast.LENGTH_LONG).show();
                        MainActivity.this.syncNow(null);
                    }
                }
        );*/

        //Intent intent = new Intent(this, SyncService.class);
        //startService(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




  /*  public void RellenarList(){
        ArrayList<Delegacion> mylist = new ArrayList<Delegacion>();
        mylist.add(new Delegacion(1,"nombre","descripcion","direccion"));
        ab = new AdapterBase(MainActivity.this,mylist);
        ab.rellenarListView();
    }*/




    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, Uri.parse(contentUri), new String[]{"_id", "nombre", "descripcion", "direccion"}, null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {}

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // mAdapter.swapCursor(null);
    }


    public static Account CreateSyncAccount(Context context) {
        // Create the account type and default account
        Account newAccount = new Account(
                DummyAuthenticator.ACCOUNT_NAME, DummyAuthenticator.ACCOUNT_TYPE);
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                        ACCOUNT_SERVICE);

        // * Add the account and account type, no password or user data
        // * If successful, return the Account object, otherwise report an error.

        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             *
        } else {
            /*
             * The account exists or some other error occurred. Log this, report it,
             * or handle it internally.
             */
            return newAccount;}
            return null;

    }
    public void syncNow(View view) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true); // Performing a sync no matter if it's off
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true); // Performing a sync no matter if it's off
        // Simple option, will ahndle everything smartly
        contentResolver = getContentResolver();
        contentResolver.requestSync(account, "content://com.frontend.gato_goku.proyectofrontendfinal.sqlprovider", bundle);
      // swipeRefreshLayout.setRefreshing(false);

        /*syncAdapter = new SyncAdapter(getApplicationContext(),true);
        List<Delegacion> delegaciones = syncAdapter.retornarDelegaciones();
        for (int c =0; c<delegaciones.size();c++){
           // getContentResolver().insert()*/


    }



}
