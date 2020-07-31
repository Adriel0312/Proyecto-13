package com.example.caribejobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.caribejobs.Modelos.API;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Busqueda3 extends AppCompatActivity {
    ImageButton regresar;

    String correoSesion;
    String idHabilidad;

    List<String> mLista = new ArrayList<>();
    ArrayAdapter<String> mAdapter;
    ListView listaZonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda3);

        regresar = (ImageButton) findViewById(R.id.botonRegresar);
        correoSesion = getIntent().getStringExtra("parametro");
        idHabilidad = getIntent().getStringExtra("parametro2");
        listaZonas = (ListView) findViewById(R.id.listZonas);



        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Busqueda3.this, Busqueda1.class);
                intent.putExtra("parametro", correoSesion);
                intent.putExtra("parametro2", idHabilidad);
                startActivity(intent);
            }
        });
        cargarZonas();
    }


    public void cargarZonas(){

        API consulta = new API();
        JSONArray res = consulta.getZonaEspecifica(idHabilidad);
        String provincia="";
        String canton="";
        for(int i=0; i<res.length();i++){
            try {
                JSONObject json = res.getJSONObject(i);

                mLista.add(json.getString("provincia")+", "+json.getString("canton"));
                mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mLista);
                listaZonas.setAdapter(mAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error",e.toString());
            }
        }
    }


}