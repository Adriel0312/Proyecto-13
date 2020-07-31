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


        protected void onCreate2() {
        
        
        
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
        Persona persona = new Persona();
        API consulta = new API();
        JSONArray res = consulta.getProfesion(correoSesion);

        for(int i=0; i<res.length();i++){
            try {
                super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nuevaProfesion = (Button) findViewById(R.id.nuevaProfesion);
        correoSesion = getIntent().getStringExtra("parametro");
        listProfesiones = (ListView)findViewById(R.id.listProfesiones);
        //correoSesion = getIntent().getStringExtra("parametro");
        Log.d("Parametro",correoSesion);
                JSONObject json = res.getJSONObject(i);
                mLista.add(json.getString("profesion"));
                mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mLista);
                listProfesiones.setAdapter(mAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error",e.toString());
            }
        }

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
     nuevaProfesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Flag","Flag4");
                Intent intent = new Intent(Registro.this, Profesion1.class);
                intent.putExtra("parametro", correoSesion);
                startActivity(intent);
            }
        });
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
        
        
        
        Persona persona = new Persona();
        API consulta = new API();
        JSONArray res = consulta.getProfesion(correoSesion);

        for(int i=0; i<res.length();i++){
            try {
                super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nuevaProfesion = (Button) findViewById(R.id.nuevaProfesion);
        correoSesion = getIntent().getStringExtra("parametro");
        listProfesiones = (ListView)findViewById(R.id.listProfesiones);
        //correoSesion = getIntent().getStringExtra("parametro");
        Log.d("Parametro",correoSesion);
                JSONObject json = res.getJSONObject(i);
                mLista.add(json.getString("profesion"));
                mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mLista);
                listProfesiones.setAdapter(mAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error",e.toString());
            }
        }

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
     nuevaProfesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Flag","Flag4");
                Intent intent = new Intent(Registro.this, Profesion1.class);
                intent.putExtra("parametro", correoSesion);
                startActivity(intent);
            }
        });
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
