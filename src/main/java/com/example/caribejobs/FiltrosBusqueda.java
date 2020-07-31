package com.example.caribejobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.caribejobs.Modelos.API;
import com.example.caribejobs.Modelos.Busqueda;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FiltrosBusqueda extends AppCompatActivity {

    Spinner comboProfesiones;
    ArrayList<String> listaProfesiones;

    String correoSesion;
    ImageButton regresar;
    Button buscar;

    Spinner comboProvincias;

    private ArrayList<String> listaProvincias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros_busqueda);

        buscar = (Button)findViewById(R.id.buscar);
        comboProfesiones = (Spinner) findViewById(R.id.spinnerProfesiones);
        regresar = (ImageButton) findViewById(R.id.botonRegresar);
        comboProvincias = (Spinner) findViewById(R.id.provincia);

        correoSesion = getIntent().getStringExtra("parametro");
        API consulta = new API();
        JSONArray res = consulta.getProfesiones();
        listaProfesiones = new ArrayList<String>();
        listaProfesiones.add("Seleccione la profesión a buscar");
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
         API consulta = new API();
        JSONArray res = consulta.getProvincias();
        listaProvincias = new ArrayList<String>();
        listaProvincias.add("Seleccione una provincia");
        for(int i=0; i<res.length();i++){
            try {
                JSONObject json = res.getJSONObject(i);
                listaProvincias.add(json.getString("provincia"));
                Log.d("Provincia",listaProvincias.toString());

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error",e.toString());
            }
        }
        cargarProfesiones();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, R.layout.spinner_item,listaProfesiones);
        comboProfesiones.setAdapter(adaptador);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FiltrosBusqueda.this, Perfil.class);
                if(correoSesion != null){
                    intent.putExtra("parametro", correoSesion);
                }
                 buscar = (Button)findViewById(R.id.buscar);
                comboProfesiones = (Spinner) findViewById(R.id.spinnerProfesiones);
                regresar = (ImageButton) findViewById(R.id.botonRegresar);
                comboProvincias = (Spinner) findViewById(R.id.provincia);

                correoSesion = getIntent().getStringExtra("parametro");
                startActivity(intent);
            }
        });

        cargarProvincias();
        ArrayAdapter<CharSequence> adaptador2 = new ArrayAdapter(this, R.layout.spinner_item,listaProvincias);
        comboProvincias.setAdapter(adaptador2);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FiltrosBusqueda.this, Busqueda.class);
                if(correoSesion != null){
                    intent.putExtra("parametro", correoSesion);
                    intent.putExtra("parametro3", comboProfesiones.getSelectedItem().toString());
                    intent.putExtra("parametro4", comboProvincias.getSelectedItem().toString());
                }else{
                    intent.putExtra("parametro3", comboProfesiones.getSelectedItem().toString());
                    intent.putExtra("parametro4", comboProvincias.getSelectedItem().toString());
                }
                 buscar = (Button)findViewById(R.id.buscar);
                comboProfesiones = (Spinner) findViewById(R.id.spinnerProfesiones);
                regresar = (ImageButton) findViewById(R.id.botonRegresar);
                comboProvincias = (Spinner) findViewById(R.id.provincia);

                correoSesion = getIntent().getStringExtra("parametro");
                startActivity(intent);
            }
        });
    }




}
