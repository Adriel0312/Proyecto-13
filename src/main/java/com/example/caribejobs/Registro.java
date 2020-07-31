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
import android.widget.ListView;

import com.example.caribejobs.Modelos.API;
import com.example.caribejobs.Modelos.Busqueda;
import com.example.caribejobs.Modelos.Persona;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Registro extends AppCompatActivity {

    private BottomNavigationView mBottonNavigation;
    private Button nuevaProfesion;
    String correoSesion;

    ListView listProfesiones;
    List<String> mLista = new ArrayList<>();
    ArrayAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nuevaProfesion = (Button) findViewById(R.id.nuevaProfesion);
        correoSesion = getIntent().getStringExtra("parametro");
        listProfesiones = (ListView)findViewById(R.id.listProfesiones);
        //correoSesion = getIntent().getStringExtra("parametro");
        Log.d("Parametro",correoSesion);

        cargarProfesiones();

        nuevaProfesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Flag","Flag4");
                Intent intent = new Intent(Registro.this, Profesion1.class);
                intent.putExtra("parametro", correoSesion);
                startActivity(intent);
            }
        });

        mBottonNavigation = (BottomNavigationView) findViewById(R.id.botonNavegacion);

        mBottonNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.menu_buscar){
                    Intent intent = new Intent(Registro.this, FiltrosBusqueda2.class);
                    intent.putExtra("parametro", correoSesion.toString());
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.menu_perfil){
                    Intent intent = new Intent(Registro.this, Perfil.class);
                    intent.putExtra("parametro", correoSesion.toString());
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.menu_registro){
                    Intent intent = new Intent(Registro.this, Registro.class);
                    intent.putExtra("parametro", correoSesion.toString());
                    startActivity(intent);
                }
                return true;
            }
        });
    }

    public void cargarProfesiones(){
        Persona persona = new Persona();
        API consulta = new API();
        JSONArray res = consulta.getProfesion(correoSesion);

        for(int i=0; i<res.length();i++){
            try {
                JSONObject json = res.getJSONObject(i);
                mLista.add(json.getString("profesion"));
                mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mLista);
                listProfesiones.setAdapter(mAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error",e.toString());
            }
        }
    }
}