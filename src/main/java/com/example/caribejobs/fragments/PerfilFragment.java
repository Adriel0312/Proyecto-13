package com.example.caribejobs.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.caribejobs.Modelos.API;
import com.example.caribejobs.Modelos.Persona;
import com.example.caribejobs.R;

import org.json.JSONObject;


public class PerfilFragment extends Fragment {

    private String correo;
    private TextView textNombre;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_perfil);
        Bundle args = getArguments();
        correo = args.getString("correoSesion","josephca99@gmail.com");
       // if (getArguments() != null) {
         //   correo = getArguments().getString("param1");
            //mParam2 = getArguments().getString(ARG_PARAM2);
        //}
        //textNombre = (TextView)findViewById(R.id.nombre);
        //textNombre=(TextView)getView().findViewById(R.id.nombre);
        //Log.d("Mensaje",correo);
        //correo = getArguments().getString("correoSesion");
        cargarInformacion();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
        //ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_perfil, null);
        //textNombre = (TextView) root.findViewById(R.id.nombre);
        //correo = getArguments().getString("param1");
        //return root;
        //return inflater.inflate(R.layout.fragment_perfil, container, false);
    }


    public void cargarInformacion() {
        //textNombre = (TextView)findViewById(R.id.nombre);
        Persona persona = new Persona();
        API consulta = new API();
        //Log.d("Mensaje",correo);
        //JSONObject res = consulta.getPerfil(correo);
        //textNombre.setText(res.optString("nombre"));

        }
}