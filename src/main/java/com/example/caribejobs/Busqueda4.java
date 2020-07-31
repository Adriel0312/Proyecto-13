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
import org.w3c.dom.Text;

public class Busqueda4 extends AppCompatActivity {

    TextView lunes;
    TextView martes;
    TextView miercoles;
    TextView jueves;
    TextView viernes;
    TextView sabado;
    TextView domingo;

    ImageButton regresar;

    String correoSesion;
    String idHabilidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda4);

        regresar = (ImageButton) findViewById(R.id.botonRegresar);
        correoSesion = getIntent().getStringExtra("parametro");
        idHabilidad = getIntent().getStringExtra("parametro2");

        lunes = (TextView) findViewById(R.id.lunes);
        martes = (TextView) findViewById(R.id.martes);
        miercoles = (TextView) findViewById(R.id.miercoles);
        jueves = (TextView) findViewById(R.id.jueves);
        viernes = (TextView) findViewById(R.id.viernes);
        sabado = (TextView) findViewById(R.id.sabado);
        domingo = (TextView) findViewById(R.id.domingo);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Busqueda4.this, Busqueda1.class);
                intent.putExtra("parametro", correoSesion);
                intent.putExtra("parametro2", idHabilidad);
                startActivity(intent);
            }
        });
        cargarHorario();
    }

    public void cargarHorario(){
        Persona persona = new Persona();
        API consulta = new API();
        JSONArray res = consulta.getHorarioHabilidad(idHabilidad);

        for(int i=0; i<res.length();i++){
            try {
                JSONObject json = res.getJSONObject(i);
                lunes.setText(json.getString("lunes"));
                martes.setText(json.getString("martes"));
                miercoles.setText(json.getString("miercoles"));
                jueves.setText(json.getString("jueves"));
                viernes.setText(json.getString("viernes"));
                sabado.setText(json.getString("sabado"));
                domingo.setText(json.getString("domingo"));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error",e.toString());
            }
        }

    }
}