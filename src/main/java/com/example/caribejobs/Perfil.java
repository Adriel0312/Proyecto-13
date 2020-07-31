package com.example.caribejobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caribejobs.Modelos.API;
import com.example.caribejobs.Modelos.Busqueda;
import com.example.caribejobs.Modelos.Persona;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Perfil extends AppCompatActivity {
    TextView textNombre;
    TextView textCorreo;
    TextView textTelefono;
    TextView textAlternativo;
    TextView textFechaNacimiento;
    TextView textProvincia;
    TextView textCanton;
    TextView textDistrito;

    String correoSesion;
    String idHabilidad;

    ImageView imagenPerfil;
    BottomNavigationView mBottonNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        correoSesion = getIntent().getStringExtra("parametro");
        idHabilidad = getIntent().getStringExtra("parametro2");
        textNombre = (TextView)findViewById(R.id.nombre);
        textCorreo = (TextView)findViewById(R.id.correoE);
        textTelefono = (TextView)findViewById(R.id.telefono);
        textAlternativo = (TextView)findViewById(R.id.alternativo);
        textFechaNacimiento = (TextView)findViewById(R.id.fechaNacimiento);
        textProvincia = (TextView)findViewById(R.id.provincia);
        textCanton = (TextView)findViewById(R.id.canton);
        textDistrito = (TextView)findViewById(R.id.distrito);
        imagenPerfil = (ImageView) findViewById(R.id.imagenPerfil);
        cargarPerfil();

        mBottonNavigation = (BottomNavigationView) findViewById(R.id.botonNavegacion);

        mBottonNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.menu_buscar){
                    Intent intent = new Intent(Perfil.this, FiltrosBusqueda2.class);
                    intent.putExtra("parametro", correoSesion.toString());
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.menu_perfil){
                    Intent intent = new Intent(Perfil.this, Perfil.class);
                    intent.putExtra("parametro", correoSesion.toString());
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.menu_registro){
                    Intent intent = new Intent(Perfil.this, Registro.class);
                    intent.putExtra("parametro", correoSesion.toString());
                    startActivity(intent);
                }

                return true;
            }
        });
    }


    public void cargarPerfil(){
        Persona persona = new Persona();
    API consulta = new API();
    JSONArray res = consulta.getPerfil(correoSesion);

        for(int i=0; i<res.length();i++){
        try {
            JSONObject json = res.getJSONObject(i);
            persona.setNombre(json.getString("nombre"));
            persona.setCorreo(json.getString("correo"));
            persona.setTelefono1(json.getString("telefono1"));
            persona.setTelefono2(json.getString("telefono2"));
            persona.setFechaNacimiento(json.getString("fechanacimiento"));
            persona.setProvincia(json.getString("provincia"));
            persona.setCanton(json.getString("canton"));
            persona.setDistrito(json.getString("distrito"));
            persona.setImagen(json.getString("imagen"));
            Log.d("Imagen: ",json.getString("imagen"));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Error",e.toString());
        }
    }

    mostrarInformacionPerfil(persona);
}

    public void mostrarInformacionPerfil(Persona persona){
        textNombre.setText("Nombre: "+persona.getNombre());
        textCorreo.setText("Correo: "+persona.getCorreo());
        textTelefono.setText("Teléfono: "+persona.getTelefono1());
        textAlternativo.setText("Alternativo: "+persona.getTelefono2());
        textFechaNacimiento.setText("Fecha Nacimiento: "+persona.getFechaNacimiento());
        textProvincia.setText("Provincia: "+persona.getProvincia());
        textCanton.setText("Cantón: "+persona.getCanton());
        textDistrito.setText("Distrito: "+persona.getDistrito());
        if(persona.getDato() != null){
            imagenPerfil.setImageBitmap(persona.getDato());
        }else{
            imagenPerfil.setImageResource(R.drawable.ic_profile);
        }

    }
}