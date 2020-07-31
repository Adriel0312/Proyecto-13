package com.example.caribejobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.caribejobs.Modelos.API;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Profesion1 extends AppCompatActivity {

    Spinner comboProfesiones;
    ArrayList<String> listaProfesiones;

    String correoSesion;

    ImageButton regresar;
    Button siguiente;
    EditText annos;
    EditText costoHora;
    EditText detalle;
    ProgressBar bar;
    String idHabilidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesion1);
        correoSesion = getIntent().getStringExtra("parametro");
        idHabilidad = getIntent().getStringExtra("parametro2");


        siguiente = (Button)findViewById(R.id.siguiente);
        comboProfesiones = (Spinner) findViewById(R.id.spinnerProfesiones);
        regresar = (ImageButton) findViewById(R.id.botonRegresar);
        annos = (EditText) findViewById(R.id.annosExpr);
        costoHora = (EditText) findViewById(R.id.costoHora);
        detalle = (EditText) findViewById(R.id.detalle);
        bar=(ProgressBar) findViewById(R.id.progressBar);
        bar.setProgress(25);

        cargarProfesiones();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, R.layout.spinner_item,listaProfesiones);
        comboProfesiones.setAdapter(adaptador);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarHabilidad();
                idHabilidad = getIdHabilidad();
                Intent intent = new Intent(Profesion1.this, Profesion2.class);
                intent.putExtra("parametro", correoSesion);
                intent.putExtra("parametro2", idHabilidad);
                startActivity(intent);
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profesion1.this, Registro.class);
                intent.putExtra("parametro", correoSesion);
                startActivity(intent);
            }
        });
    }

    private void cargarProfesiones() {
        API consulta = new API();
        JSONArray res = consulta.getProfesiones();
        listaProfesiones = new ArrayList<String>();
        listaProfesiones.add("Seleccione una profesión");
        for(int i=0; i<res.length();i++){
            try {
                JSONObject json = res.getJSONObject(i);
                listaProfesiones.add(json.getString("nombre"));
                Log.d("Profesion",listaProfesiones.toString());

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error",e.toString());
            }
        }
    }



    public void registrarHabilidad (){
        try{
            List<NameValuePair> values = new ArrayList<>();
            values.add(new BasicNameValuePair("persona",correoSesion));
            values.add(new BasicNameValuePair("profesion",comboProfesiones.getSelectedItem().toString()));
            values.add(new BasicNameValuePair("annos",annos.getText().toString()));
            values.add(new BasicNameValuePair("detalle",detalle.getText().toString()));
            values.add(new BasicNameValuePair("costo",costoHora.getText().toString()));

            API consulta = new API();
            String res = consulta.PostHabilidad(values);
            Log.d("Respuesta",res);
            Log.d("Respuesta","Habilidad regitrada exitosamejnte!");
            //Toast.makeText(Profesion1.this, "Usuario registrado exitosamente!", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(Profesion1.this, "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
            Log.d("Error:",e.toString());
        }
    }

    public String getIdHabilidad(){
        API consulta = new API();
        String idHabilidad2="j";
        JSONArray res = consulta.getIdHabilidad();
        for(int i=0; i<res.length();i++){
            try {
                JSONObject json = res.getJSONObject(i);
                idHabilidad2 =json.getString("idhabilidad");
                //Log.d("Profesion",listaProfesiones.toString());

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error",e.toString());
            }
        }
        Log.d("Id",idHabilidad2);
        return idHabilidad2;
    }

}