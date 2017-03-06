package com.frontend.gato_goku.proyectofrontendfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.frontend.gato_goku.proyectofrontendfinal.models.DelegacionAdapter;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {


    DelegacionAdapter deladapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        ArrayList<String> item =  intent.getStringArrayListExtra("vals");

    //    deladapter = new DelegacionAdapter(this,item);

        View view;
        int icons[] = {android.R.drawable.ic_dialog_info,
                android.R.drawable.ic_dialog_info,
                android.R.drawable.ic_dialog_alert,
                android.R.drawable.ic_dialog_email};

           // LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           // view = inflater.inflate(R.layout.activity_detail,null);

        ImageView imageViewIcon = (ImageView) findViewById(R.id.imageView2);
        //imageViewIcon.setImageResource(icons[1]);

        TextView textViewTitle = (TextView) findViewById(R.id.textViewDNombre);

         textViewTitle.setText( item.get(0));

        TextView textViewText = (TextView) findViewById(R.id.textViewDDescripcion);

         textViewText.setText(item.get(1));



    }


























    public void close(View v) {

        this.finish();

    }
}
