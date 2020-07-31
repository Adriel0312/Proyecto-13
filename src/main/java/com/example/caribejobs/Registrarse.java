package com.example.caribejobs;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caribejobs.Modelos.API;
import com.example.caribejobs.Modelos.Busqueda;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Registrarse extends AppCompatActivity {

    private TextView fechaNacimiento;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    Spinner comboProvincia;
    EditText textNombre;
    EditText textApellido1;
    EditText textApellido2;
    EditText textCorreo;
    EditText textTelefono1;
    EditText textTelefono2;
    EditText textCanton;
    EditText textDistrito;
    EditText textContrasenna;

    Button registrar;

    ImageView imagen;

    ArrayList<String> listaProvincias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registrarse);
        registrar = (Button) findViewById(R.id.botonRegistrar);
        imagen = (ImageView) findViewById(R.id.imagenPerfil);
        comboProvincia = (Spinner) findViewById(R.id.provincia);
        textNombre = (EditText) findViewById(R.id.nombre);
        textApellido1 = (EditText) findViewById(R.id.apellido1);
        textApellido2 = (EditText) findViewById(R.id.apellido2);
        textCorreo = (EditText) findViewById(R.id.correo);
        textTelefono1 = (EditText) findViewById(R.id.telefono);
        textTelefono2 = (EditText) findViewById(R.id.alterntativo);
        textCanton = (EditText) findViewById(R.id.canton);
        textDistrito = (EditText) findViewById(R.id.distrito);
        textContrasenna = (EditText) findViewById(R.id.contrasenna);

        cargarProvincias();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, R.layout.spinner_item,listaProvincias);
        comboProvincia.setAdapter(adaptador);

        fechaNacimiento = findViewById(R.id.fechaNacimiento);

        fechaNacimiento.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Registrarse.this,
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String date = day + "/" + month + "/" + year;
                fechaNacimiento.setText(date);
            }
        };

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Flag","Flag4");
                registrarse();
                Intent intent = new Intent(Registrarse.this, MainActivity.class);
                //intent.putExtra("parametro", editcorreo.getText().toString());
                startActivity(intent);
            }
        });

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


    public void onClick(View view) {
        cargarImagen();
    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicación"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Uri path = data.getData();
            imagen.setImageURI(path);
        }
    }

    public void onClick2(View view)
    {
        Log.d("Flag","flag2");
        registrarse();
    }

    public void registrarse (){
        try{
            List<NameValuePair> values = new ArrayList<>();
            values.add(new BasicNameValuePair("nombre",textNombre.getText().toString()));
            values.add(new BasicNameValuePair("apellido1",textApellido1.getText().toString()));
            values.add(new BasicNameValuePair("apellido2",textApellido2.getText().toString()));
            values.add(new BasicNameValuePair("imagen",imagen.toString()));
            values.add(new BasicNameValuePair("correo",textCorreo.getText().toString()));
            values.add(new BasicNameValuePair("telefono1",textTelefono1.getText().toString()));
            values.add(new BasicNameValuePair("telefono2",textTelefono2.getText().toString()));
            values.add(new BasicNameValuePair("fechaNacimiento",fechaNacimiento.getText().toString()));
            values.add(new BasicNameValuePair("provincia",comboProvincia.getSelectedItem().toString()));
            values.add(new BasicNameValuePair("canton",textCanton.getText().toString()));
            values.add(new BasicNameValuePair("distrito",textDistrito.getText().toString()));
            values.add(new BasicNameValuePair("contrasenna",textContrasenna.getText().toString()));
            API consulta = new API();
            String res = consulta.ConexionSQLPost(values);
            Log.d("Respuesta",res);
            Toast.makeText(Registrarse.this, "Usuario registrado exitosamente!", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(Registrarse.this, "Erro: "+e.toString(), Toast.LENGTH_SHORT).show();
            Log.d("Error:",e.toString());
        }


    }
}