package com.example.caribejobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.caribejobs.Modelos.API;
import com.example.caribejobs.Modelos.Persona;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Busqueda2 extends AppCompatActivity {

    ImageButton regresar;

    String correoSesion;
    String idHabilidad;
    
    TextView profesion;
    TextView annos;
    TextView costo;
    TextView detalle; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda2);
        regresar = (ImageButton) findViewById(R.id.botonRegresar);
        correoSesion = getIntent().getStringExtra("parametro");
        idHabilidad = getIntent().getStringExtra("parametro2");
        
        profesion = (TextView) findViewById(R.id.profesion);
        annos = (TextView) findViewById(R.id.annos);
        costo = (TextView) findViewById(R.id.costo);
        detalle = (TextView) findViewById(R.id.detalle);

        cargarProfesion();
    
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Busqueda2.this, Busqueda1.class);
                intent.putExtra("parametro", correoSesion);
                intent.putExtra("parametro2", idHabilidad);
                startActivity(intent);
            }
        });
    }


    public void cargarProfesion(){
        Persona persona = new Persona();
        API consulta = new API();
        JSONArray res = consulta.getProfesionEspecifica(idHabilidad);

        for(int i=0; i<res.length();i++){
            try {
                JSONObject json = res.getJSONObject(i);
                profesion.setText(json.getString("profesion"));
                annos.setText(json.getString("annos"));
                detalle.setText(json.getString("detalle"));
                costo.setText("₡ "+json.getString("costo"));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error",e.toString());
            }
        }

    }

}