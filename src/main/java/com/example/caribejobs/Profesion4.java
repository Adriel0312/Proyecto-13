package com.example.caribejobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.caribejobs.Modelos.API;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Profesion4 extends AppCompatActivity {
    Spinner comboProfesiones;
    ArrayList<String> listaProfesiones;

    String idHabilidad;
    ProgressBar bar;
    String correoSesion;

    ImageButton regresar;
    Button siguiente;

    EditText nombreJefe;
    EditText apellidosJefe;
    EditText telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesion4);

        correoSesion = getIntent().getStringExtra("parametro");
        idHabilidad = getIntent().getStringExtra("parametro2");

        nombreJefe = (EditText) findViewById(R.id.nombreJefe);
        apellidosJefe = (EditText) findViewById(R.id.apellidos);
        telefono = (EditText) findViewById(R.id.telefono);

        siguiente = (Button)findViewById(R.id.siguiente);
        comboProfesiones = (Spinner) findViewById(R.id.spinnerProfesiones);
        regresar = (ImageButton) findViewById(R.id.botonRegresar);
        bar=(ProgressBar) findViewById(R.id.progressBar);
        bar.setProgress(90);

        cargarProfesiones();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, R.layout.spinner_item,listaProfesiones);
        comboProfesiones.setAdapter(adaptador);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarReferencia();
                Toast.makeText(Profesion4.this, "¡Profesión registrada con éxito!", Toast.LENGTH_SHORT).show();
                //idHabilidad = getIdHabilidad();
                Intent intent = new Intent(Profesion4.this, Registro.class);
                intent.putExtra("parametro", correoSesion);
                //intent.putExtra("parametro2", idHabilidad);
                startActivity(intent);
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profesion4.this, Registro.class);
                intent.putExtra("parametro", correoSesion);
                startActivity(intent);
            }
        });
    }


    private void cargarProfesiones() {
        API consulta = new API();
        JSONArray res = consulta.getProfesiones();
        listaProfesiones = new ArrayList<String>();
        listaProfesiones.add("Seleccione su trabajo anterior");
        for(int i=0; i<res.length();i++){
            try {
                JSONObject json = res.getJSONObject(i);
                listaProfesiones.add(json.getString("nombre"));
                //Log.d("Profesion",listaProfesiones.toString());

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error",e.toString());
            }
        }
    }


    public void registrarReferencia (){
        try{
            List<NameValuePair> values = new ArrayList<>();
            values.add(new BasicNameValuePair("habilidad",idHabilidad));
            values.add(new BasicNameValuePair("profesion",comboProfesiones.getSelectedItem().toString()));
            values.add(new BasicNameValuePair("nombreJefe",nombreJefe.getText().toString()));
            values.add(new BasicNameValuePair("apellido1",apellidosJefe.getText().toString()));
            values.add(new BasicNameValuePair("telefono",telefono.getText().toString()));

            API consulta = new API();
            String res = consulta.postReferencia(values);
            Log.d("Respuesta",res);
            Log.d("Respuesta","Referencia regitrada exitosamejnte!");
            //Toast.makeText(Profesion1.this, "Usuario registrado exitosamente!", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(Profesion4.this, "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
            Log.d("Error:",e.toString());
        }
    }
}