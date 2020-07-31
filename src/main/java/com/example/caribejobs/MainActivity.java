package com.example.caribejobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caribejobs.Modelos.API;
import com.example.caribejobs.Modelos.Busqueda;

public class MainActivity extends AppCompatActivity {
    EditText editcorreo, editcontrasenna;
    Button botonIngresar,botonRegistrarse,botonIngresarSinRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editcorreo = (EditText)findViewById(R.id.correo);
        editcontrasenna = (EditText)findViewById(R.id.contrasenna);
        botonIngresar = (Button)findViewById(R.id.botonIngresar);
        botonRegistrarse = (Button)findViewById(R.id.registrarse);
        botonIngresarSinRegistro = (Button)findViewById(R.id.ingresarSinRegistro);

        botonIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                    API conection = new API();
                    int res = conection.Login(editcorreo.getText().toString(),editcontrasenna.getText().toString());
                    if(res == 1) {
                        Toast.makeText(MainActivity.this, "Correo o contraseña inválida", Toast.LENGTH_SHORT).show();
                    } else if(res == 2){
                        Toast.makeText(MainActivity.this, "Ha ocurrido un error en el servidor!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent = new Intent(MainActivity.this, Perfil.class);
                        intent.putExtra("parametro", editcorreo.getText().toString());
                        startActivity(intent);
                    }
            }
        });
        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Registrarse.class));
            }
        });
        botonIngresarSinRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FiltrosBusqueda.class));
            }
        });
    }
}