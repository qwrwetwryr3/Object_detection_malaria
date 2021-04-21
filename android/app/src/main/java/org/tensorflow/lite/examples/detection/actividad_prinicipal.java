package org.tensorflow.lite.examples.detection;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class actividad_prinicipal extends AppCompatActivity {

    Button iniciar_camara;
    Button iniciar_maps;
    Button iniciar_ingreso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_prinicipal);

        iniciar_camara=(Button)findViewById(R.id.button);
        iniciar_camara.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent iniciar_camara= new Intent(actividad_prinicipal.this, DetectorActivity.class);
                startActivity(iniciar_camara);
            }
        });

        iniciar_ingreso=(Button)findViewById(R.id.button3);
        iniciar_ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iniciar_ingreso=new Intent(actividad_prinicipal.this, FirebaseActivity.class);
                startActivity(iniciar_ingreso);
            }
        });

    }







}
