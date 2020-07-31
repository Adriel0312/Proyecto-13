package com.example.caribejobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.caribejobs.Modelos.API;
import com.example.caribejobs.Modelos.Persona;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Busqueda5 extends AppCompatActivity {


    TextView empleo;
    TextView jefe;
    TextView telefono;

    ImageButton regresar;

    String correoSesion;
    String idHabilidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda5);

        regresar = (ImageButton) findViewById(R.id.botonRegresar);
        correoSesion = getIntent().getStringExtra("parametro");
        idHabilidad = getIntent().getStringExtra("parametro2");

        empleo = (TextView)findViewById(R.id.trabajo);
        jefe = (TextView)findViewById(R.id.jefe);
        telefono = (TextView) findViewById(R.id.telefono);



        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Busqueda5.this, Busqueda1.class);
                intent.putExtra("parametro", correoSesion);
                intent.putExtra("parametro2", idHabilidad);
                startActivity(intent);
            }
        });
        cargarReferencias();
    }


    public void cargarReferencias(){
        Persona persona = new Persona();
        API consulta = new API();
        JSONArray res = consulta.getReferenciaTrabajo(idHabilidad);

        for(int i=0; i<res.length();i++){
            try {
                JSONObject json = res.getJSONObject(i);
                empleo.setText(json.getString("profesion"));
                jefe.setText(json.getString("nombrejefe")+" "+json.getString("apellido1"));
                telefono.setText(json.getString("telefono"));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error",e.toString());
            }
        }

    }
}