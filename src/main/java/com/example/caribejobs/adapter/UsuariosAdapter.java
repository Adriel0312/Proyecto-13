package com.example.caribejobs.adapter;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caribejobs.Modelos.Persona;
import com.example.caribejobs.R;

import java.util.List;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.UsuariosHolder>
    implements View.OnClickListener{
    List <Persona> listaUsuarios;
    private View.OnClickListener listener;

    public UsuariosAdapter(List<Persona> listaUsuarios){
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public UsuariosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.usuarios_list,parent,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        vista.setOnClickListener(this);//Escucha el evento de selección

        return new UsuariosHolder(vista);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull UsuariosHolder holder, int position) {
        try{
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaNac = LocalDate.parse(listaUsuarios.get(position).getFechaNacimiento(), fmt);
            LocalDate ahora = LocalDate.now();
            Period periodo = Period.between(fechaNac, ahora);
            String edad1 = String.valueOf(periodo.getYears());


            holder.txtNombre.setText(listaUsuarios.get(position).getNombre().toString());
            holder.txtEdad.setText(edad1+" años");
            holder.txtCanton.setText(listaUsuarios.get(position).getCanton());
            holder.txtTelefono.setText(listaUsuarios.get(position).getTelefono1());

        }catch(Exception e){
            Log.d("Error",e.toString());
        }


    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }


    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }

    }

    public class UsuariosHolder extends RecyclerView.ViewHolder{
        TextView txtNombre,txtEdad,txtCanton,txtTelefono;

        public UsuariosHolder (View itemView){
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.txtNombre);
            txtEdad = (TextView) itemView.findViewById(R.id.txtEdad);
            txtCanton = (TextView) itemView.findViewById(R.id.txtCanton);
            txtTelefono = (TextView) itemView.findViewById(R.id.txtTelefono);
        }
    }
}
