package com.example.caribejobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caribejobs.Modelos.API;
import com.example.caribejobs.Modelos.Busqueda;
import com.example.caribejobs.Modelos.Persona;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Busqueda1 extends AppCompatActivity {

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
    ImageButton regresar;

    Button infoProfesion;
    Button infoZonaTrabajo;
    Button infoHorarioTrabajo;
    Button infoReferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda1);

        infoProfesion=(Button)findViewById(R.id.botonEmpleo);
        infoZonaTrabajo=(Button)findViewById(R.id.botonZonaTrabajo);
        infoHorarioTrabajo=(Button)findViewById(R.id.botonHorario);
        infoReferencias=(Button)findViewById(R.id.botonReferencias);

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
        regresar = (ImageButton)findViewById(R.id.botonRegresar);
        cargarPerfil();

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Busqueda1.this, Busqueda.class);
                intent.putExtra("parametro", correoSesion);
                intent.putExtra("parametro2", idHabilidad);
                startActivity(intent);
            }
        });

        infoProfesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Busqueda1.this, Busqueda2.class);
                intent.putExtra("parametro", correoSesion);
                intent.putExtra("parametro2", idHabilidad);
                startActivity(intent);
            }
        });
        infoZonaTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Busqueda1.this, Busqueda3.class);
                intent.putExtra("parametro", correoSesion);
                intent.putExtra("parametro2", idHabilidad);
                startActivity(intent);
            }
        });
        infoHorarioTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Busqueda1.this, Busqueda4.class);
                intent.putExtra("parametro", correoSesion);
                intent.putExtra("parametro2", idHabilidad);
                startActivity(intent);
            }
        });

        infoReferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Busqueda1.this, Busqueda5.class);
                intent.putExtra("parametro", correoSesion);
                intent.putExtra("parametro2", idHabilidad);
                startActivity(intent);
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