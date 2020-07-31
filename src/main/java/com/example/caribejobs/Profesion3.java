package com.example.caribejobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.caribejobs.Modelos.API;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class Profesion3 extends AppCompatActivity {

    Spinner spin1,spin2,spin3,spin4,spin5,spin6,spin7;

    Button siguiente;
    ImageButton regresar;

    String correoSesion;
    String idHabilidad;

    ArrayList<String> listaHorario;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesion3);

        spin1 = (Spinner) findViewById(R.id.spinner1);
        spin2 = (Spinner) findViewById(R.id.spinner2);
        spin3 = (Spinner) findViewById(R.id.spinner3);
        spin4 = (Spinner) findViewById(R.id.spinner4);
        spin5 = (Spinner) findViewById(R.id.spinner5);
        spin6 = (Spinner) findViewById(R.id.spinner6);
        spin7 = (Spinner) findViewById(R.id.spinner7);

        bar = (ProgressBar) findViewById(R.id.progressBar);
        bar.setProgress(75);

        correoSesion = getIntent().getStringExtra("parametro");
        idHabilidad = getIntent().getStringExtra("parametro2");

        siguiente = (Button)findViewById(R.id.siguiente);
        regresar = (ImageButton)findViewById(R.id.botonRegresar);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarHorarios();
                Toast.makeText(Profesion3.this, "Horario registrado exitosamente!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Profesion3.this, Profesion4.class);
                intent.putExtra("parametro", correoSesion);
                intent.putExtra("parametro2", idHabilidad);
                startActivity(intent);
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profesion3.this, Registro.class);
                intent.putExtra("parametro", correoSesion);
                intent.putExtra("parametro2", idHabilidad);
                startActivity(intent);
            }
        });
        cargarSpinners();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, R.layout.spinner_item,listaHorario);
        spin1.setAdapter(adaptador);
        spin2.setAdapter(adaptador);
        spin3.setAdapter(adaptador);
        spin4.setAdapter(adaptador);
        spin5.setAdapter(adaptador);
        spin6.setAdapter(adaptador);
        spin7.setAdapter(adaptador);
    }

    private void cargarSpinners() {
        listaHorario = new ArrayList<String>();
        listaHorario.add("Jornada completa");
        listaHorario.add("Media jornada");
        listaHorario.add("No disponible");
    }


    public void agregarHorarios(){
        try{
            List<NameValuePair> values = new ArrayList<>();
            values.add(new BasicNameValuePair("habilidad",idHabilidad));
            values.add(new BasicNameValuePair("lunes",spin1.getSelectedItem().toString()));
            values.add(new BasicNameValuePair("martes",spin2.getSelectedItem().toString()));
            values.add(new BasicNameValuePair("miercoles",spin3.getSelectedItem().toString()));
            values.add(new BasicNameValuePair("jueves",spin4.getSelectedItem().toString()));
            values.add(new BasicNameValuePair("viernes",spin5.getSelectedItem().toString()));
            values.add(new BasicNameValuePair("sabado",spin6.getSelectedItem().toString()));
            values.add(new BasicNameValuePair("domingo",spin7.getSelectedItem().toString()));


            API consulta = new API();
            String res = consulta.postHorario(values);
            Log.d("Respuesta",res.toString());
        } catch (Exception e){
            Toast.makeText(Profesion3.this, "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
            Log.d("Error:",e.toString());
        }

    }
}