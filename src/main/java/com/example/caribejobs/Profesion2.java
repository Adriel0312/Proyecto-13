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
import android.widget.ListView;
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

public class Profesion2 extends AppCompatActivity {

    String correoSesion;
    ListView listaZonas;
    String idHabilidad;

    Button siguiente;
    Button agregarZona;
    ImageButton regresar;
    ProgressBar bar;

    Spinner comboProvincias;
    EditText textCanton;

    private ArrayList<String> listaProvincias;
    private List<String> mLista = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesion2);

        correoSesion = getIntent().getStringExtra("parametro");
        idHabilidad = getIntent().getStringExtra("parametro2");
        listaZonas = (ListView) findViewById(R.id.listZonas);
        siguiente = (Button) findViewById(R.id.siguiente);
        agregarZona = (Button) findViewById(R.id.agregarZona);
        regresar = (ImageButton) findViewById(R.id.botonRegresar);

        comboProvincias = (Spinner) findViewById(R.id.provincia);
        textCanton = (EditText) findViewById(R.id.canton);

        bar=(ProgressBar) findViewById(R.id.progressBar);
        bar.setProgress(50);



        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //registrarHabilidad();
                Intent intent = new Intent(Profesion2.this, Profesion3.class);
                intent.putExtra("parametro", correoSesion);
                intent.putExtra("parametro2", idHabilidad);
                startActivity(intent);
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profesion2.this, Registro.class);
                intent.putExtra("parametro", correoSesion);
                intent.putExtra("parametro2", idHabilidad);
                startActivity(intent);
            }
        });
        agregarZona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarZona();
                Toast.makeText(Profesion2.this, "Zona agregada correctamente!", Toast.LENGTH_SHORT).show();
            }
        });
        cargarProvincias();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, R.layout.spinner_item,listaProvincias);
        comboProvincias.setAdapter(adaptador);

    }

    public void registrarZona(){

        try{
            List<NameValuePair> values = new ArrayList<>();
            values.add(new BasicNameValuePair("habilidad",idHabilidad));
            values.add(new BasicNameValuePair("provincia",comboProvincias.getSelectedItem().toString()));
            values.add(new BasicNameValuePair("canton",textCanton.getText().toString()));

            API consulta = new API();
            String res = consulta.postZona(values);
            Log.d("Respuesta",res.toString());
            mLista.add(comboProvincias.getSelectedItem().toString()+", "+textCanton.getText().toString());
            mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mLista);
            listaZonas.setAdapter(mAdapter);
            textCanton.getText().clear();
        } catch (Exception e){
            Toast.makeText(Profesion2.this, "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
            Log.d("Error:",e.toString());
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