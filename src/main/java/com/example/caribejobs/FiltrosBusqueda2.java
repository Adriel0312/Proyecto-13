package com.example.caribejobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.caribejobs.Modelos.API;
import com.example.caribejobs.Modelos.Busqueda;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FiltrosBusqueda2 extends AppCompatActivity {
    BottomNavigationView mBottonNavigation;

    String correoSesion;

    Spinner comboProfesiones;
    ArrayList<String> listaProfesiones;

    Button buscar;

    Spinner comboProvincias;

    private ArrayList<String> listaProvincias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros_busqueda2);



        correoSesion = getIntent().getStringExtra("parametro");
        buscar = (Button)findViewById(R.id.buscar);
        comboProfesiones = (Spinner) findViewById(R.id.spinnerProfesiones);
        comboProvincias = (Spinner) findViewById(R.id.provincia);


        mBottonNavigation = (BottomNavigationView) findViewById(R.id.botonNavegacion);

        mBottonNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.menu_buscar){
                    Intent intent = new Intent(FiltrosBusqueda2.this, FiltrosBusqueda2.class);
                    intent.putExtra("parametro", correoSesion.toString());
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.menu_perfil){
                    Intent intent = new Intent(FiltrosBusqueda2.this, Perfil.class);
                    intent.putExtra("parametro", correoSesion.toString());
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.menu_registro){
                    //showSelectedFragment(new RegistroFragment());
                    Intent intent = new Intent(FiltrosBusqueda2.this, Registro.class);
                    intent.putExtra("parametro", correoSesion.toString());
                    startActivity(intent);
                }

                return true;
            }
        });

        cargarProfesiones();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, R.layout.spinner_item,listaProfesiones);
        comboProfesiones.setAdapter(adaptador);

        cargarProvincias();
        ArrayAdapter<CharSequence> adaptador2 = new ArrayAdapter(this, R.layout.spinner_item,listaProvincias);
        comboProvincias.setAdapter(adaptador2);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FiltrosBusqueda2.this, Busqueda.class);
                String filtroProfesion=comboProfesiones.getSelectedItem().toString();
                String filtroProvincia=comboProvincias.getSelectedItem().toString();
                if(filtroProfesion=="Seleccione la profesión a buscar"){
                    filtroProfesion = "";
                }
                if(filtroProvincia=="Seleccione una provincia"){
                    filtroProvincia="";
                }
                if(correoSesion != null){
                    intent.putExtra("parametro", correoSesion);
                    intent.putExtra("parametro3", filtroProfesion);
                    intent.putExtra("parametro4", filtroProvincia);
                }else{
                    intent.putExtra("parametro3", comboProfesiones.getSelectedItem().toString());
                    intent.putExtra("parametro4", comboProvincias.getSelectedItem().toString());
                }

                startActivity(intent);
            }
        });
    }


    private void cargarProfesiones() {
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
    }

    public void cargarProvincias(){
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

    }
}