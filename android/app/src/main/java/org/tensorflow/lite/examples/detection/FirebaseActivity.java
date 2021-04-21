package org.tensorflow.lite.examples.detection;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.tensorflow.lite.examples.detection.model.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FirebaseActivity extends AppCompatActivity {
    private List<Persona> listPerson = new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapterPersona;

    EditText nomCompletoP,correoP,fechaInfeccionP,nombreUbicacionP,parasitosEncontradosP,latitudP,longitudP;
    ListView listV_personas;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Persona personaSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        nomCompletoP = findViewById(R.id.txt_nombrePersona);
        correoP = findViewById(R.id.txt_correoPersona);
        fechaInfeccionP = findViewById(R.id.txt_fechaInfeccionPersona);

        parasitosEncontradosP=findViewById(R.id.txt_parasitosPersona);

        nombreUbicacionP=findViewById(R.id.txt_nombreUbicacionPersona);
        latitudP=findViewById(R.id.txt_latitudPersona);
        longitudP=findViewById(R.id.txt_longitudPersona);

        listV_personas = findViewById(R.id.lv_datosPersonas);
        inicializarFirebase();
        listarDatos();

        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaSelected = (Persona) parent.getItemAtPosition(position);
                nomCompletoP.setText(personaSelected.getNombreCompleto());
                correoP.setText(personaSelected.getCorreo());
                fechaInfeccionP.setText(personaSelected.getFecha_infeccion());


                String stringParasitosEncontrados=Double.toString(personaSelected.getParasitosEncontrados());
                parasitosEncontradosP.setText(stringParasitosEncontrados);

                nombreUbicacionP.setText(personaSelected.getNombre_ubicacion());

                String stringLatitud=Double.toString(personaSelected.getLatitud());
                latitudP.setText(stringLatitud);

                String stringLongitud=Double.toString(personaSelected.getLongitud());
                longitudP.setText(stringLongitud);
            }
        });
    }

    private void listarDatos() {
        databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Persona p = objSnaptshot.getValue(Persona.class);
                    listPerson.add(p);

                    arrayAdapterPersona = new ArrayAdapter<Persona>(FirebaseActivity.this, android.R.layout.simple_list_item_1, listPerson);
                    listV_personas.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        String nombreCompleto=nomCompletoP.getText().toString();
        String correo = correoP.getText().toString();
        String fechaInfeccion = fechaInfeccionP.getText().toString();


        double parasitosEncontrados=Double.parseDouble(parasitosEncontradosP.getText().toString());

        String nombreUbicacion=nombreUbicacionP.getText().toString();
        double latitud=Double.parseDouble(latitudP.getText().toString());
        double longitud=Double.parseDouble(longitudP.getText().toString());
        switch(item.getItemId()){
            case R.id.icon_add:{
                if (nombreCompleto.equals("")||correo.equals("")||fechaInfeccion.equals("")
                        ||parasitosEncontrados==0 ||nombreUbicacion.equals("")||latitud==0||longitud==0){
                    validacion();
                }
                else {
                    Persona p=new Persona();
                    p.setUid(UUID.randomUUID().toString());
                    p.setNombreCompleto(nombreCompleto);
                    p.setCorreo(correo);
                    p.setFecha_infeccion(fechaInfeccion);

                    p.setParasitosEncontrados(parasitosEncontrados);

                    p.setNombre_ubicacion(nombreUbicacion);
                    p.setLatitud(latitud);
                    p.setLongitud(longitud);
                    databaseReference.child("Persona").child(p.getUid()).setValue(p);
                    Toast.makeText(this,"Agregado",Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
                break;
            }
            case R.id.icon_save:{
                Persona p = new Persona();
                p.setUid(personaSelected.getUid());
                p.setNombreCompleto(nomCompletoP.getText().toString().trim());
                p.setCorreo(correoP.getText().toString().trim());
                p.setFecha_infeccion(fechaInfeccionP.getText().toString().trim());


                p.setParasitosEncontrados(Double.parseDouble(parasitosEncontradosP.getText().toString().trim()));

                p.setNombre_ubicacion(nombreUbicacionP.getText().toString().trim());
                p.setLatitud(Double.parseDouble(latitudP.getText().toString().trim()));
                p.setLongitud(Double.parseDouble(longitudP.getText().toString().trim()));
                databaseReference.child("Persona").child(p.getUid()).setValue(p);
                Toast.makeText(this,"Guardado",Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
            case R.id.icon_delete:{
                Persona p = new Persona();
                p.setUid(personaSelected.getUid());
                databaseReference.child("Persona").child(p.getUid()).removeValue();
                Toast.makeText(this,"Eliminado",Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
            case R.id.icon_map:{
                Intent iniciar_maps= new Intent(FirebaseActivity.this, MapsActivity.class);
                startActivity(iniciar_maps);
            }
            default:break;

        }
        return true;
    }

    private void limpiarCajas() {
        nomCompletoP.setText("");
        correoP.setText("");
        fechaInfeccionP.setText("");

        parasitosEncontradosP.setText("");

        nombreUbicacionP.setText("");
        latitudP.setText("");
        longitudP.setText("");
    }

    private void validacion() {
        String nombreCompleto = nomCompletoP.getText().toString();
        String correo = correoP.getText().toString();
        String fechaInfeccion = fechaInfeccionP.getText().toString();

        double parasitosEncontrados= Integer.parseInt(parasitosEncontradosP.getText().toString());

        String nombreUbicacion=nombreUbicacionP.getText().toString();
        double latitud=Double.parseDouble(latitudP.getText().toString());
        double longitud=Double.parseDouble(longitudP.getText().toString());
        if (nombreCompleto.equals("")){
            nomCompletoP.setError("Required");
        }
        else if (correo.equals("")){
            correoP.setError("Required");
        }
        else if (fechaInfeccion.equals("")){
            fechaInfeccionP.setError("Required");
        }
        else if (nombreUbicacion.equals("")) {
            nombreUbicacionP.setError("Required");
        }
    }
}
