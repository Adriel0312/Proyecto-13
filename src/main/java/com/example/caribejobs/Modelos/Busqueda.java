package com.example.caribejobs.Modelos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.caribejobs.Busqueda1;
import com.example.caribejobs.MainActivity;
import com.example.caribejobs.Perfil;
import com.example.caribejobs.R;
import com.example.caribejobs.Registrarse;
import com.example.caribejobs.Registro;
import com.example.caribejobs.adapter.UsuariosAdapter;
import com.example.caribejobs.fragments.BusquedaFragment;
import com.example.caribejobs.fragments.PerfilFragment;
import com.example.caribejobs.fragments.RegistroFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Busqueda extends AppCompatActivity {

    RecyclerView recyclerUsuarios;
    ArrayList<Persona> listaUsuarios;

    String correoSesion;
    String filtroProfesion;
    String filtroProvincia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        correoSesion = getIntent().getStringExtra("parametro");
        filtroProfesion = getIntent().getStringExtra("parametro3");
        filtroProvincia = getIntent().getStringExtra("parametro4");
        listaUsuarios = new ArrayList<>();

        recyclerUsuarios = (RecyclerView) findViewById(R.id.idRecycler);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(this));
        recyclerUsuarios.setHasFixedSize(true);

        cargarUsuarios();
    }


    public void cargarUsuarios(){
        API consulta = new API();
        JSONArray res = null;
        res = consulta.getHabilidadesFiltro(filtroProfesion,filtroProvincia);

        try {
            for (int i = 0; i < res.length(); i++) {
                try {
                    Persona persona = new Persona();
                    JSONObject json = res.getJSONObject(i);
                    persona.setNombre(json.getString("nombre"));
                    persona.setCorreo(json.getString("correo"));
                    persona.setTelefono1(json.getString("telefono1"));
                    persona.setTelefono2(json.getString("telefono2"));
                    persona.setFechaNacimiento(json.getString("edad"));
                    persona.setProvincia(json.getString("provincia"));
                    persona.setCanton(json.getString("canton"));
                    persona.setDistrito(json.getString("distrito"));
                    persona.setProfesion(json.getString("profesion"));
                    persona.setIdHabilidad(json.getString("idhabilidad"));

                    listaUsuarios.add(persona);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("Error", e.toString());
                }
            }
        }catch(Exception e){
            Toast.makeText(Busqueda.this, "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
        }
        UsuariosAdapter adapter = new UsuariosAdapter(listaUsuarios);
        recyclerUsuarios.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Busqueda.this, Busqueda1.class);
                intent.putExtra("parametro", listaUsuarios.get(recyclerUsuarios.getChildAdapterPosition(view)).getCorreo());
                intent.putExtra("parametro2",listaUsuarios.get(recyclerUsuarios.getChildAdapterPosition(view)).getIdHabilidad());
                startActivity(intent);
                //Toast.makeText(Busqueda.this, "Selección: "+listaUsuarios.get(recyclerUsuarios.getChildAdapterPosition(view)).getIdHabilidad(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}