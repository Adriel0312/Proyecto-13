package com.example.caribejobs.Modelos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.ViewGroup;

public class Persona {
    public String nombre;
    public String apellido1;
    public String apellido2;
    public String imagen;
    public String correo;
    public String telefono1;
    public String telefono2;
    public String fechaNacimiento;
    public String provincia;
    public String canton;
    public String distrito;
    public String contrasenna;
    public Bitmap dato;
    public String profesion;
    public String idHabilidad;


    public Persona(){

    }

    public Persona(String nombre, String apellido1, String apellido2, String imagen, String correo, String telefono1, String telefono2, String fechaNacimiento, String provincia, String canton, String distrito, String contrasenna) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.imagen = imagen;
        this.correo = correo;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.fechaNacimiento = fechaNacimiento;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.contrasenna = contrasenna;
    }

    public String getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(String idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public Bitmap getDato() {
        return dato;
    }

    public void setDato(Bitmap dato) {
        this.dato = dato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre1) {
        nombre = nombre1;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido) {
        apellido1 = apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido) {
        apellido2 = apellido;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen1) {
        imagen = imagen1;
        try{
            Log.d("Imagen",imagen1);
            byte [] byteCode = Base64.decode(imagen,Base64.DEFAULT);
            this.dato = BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
        }catch(Exception e){
            Log.d("Error",e.toString());
        }

    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public void setCorreo(String correo1) {
        correo = correo1;
    }

    public String getCorreo(){ return correo;}

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono) {
        telefono1 = telefono;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono) {
        telefono2 = telefono;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento1) {
        fechaNacimiento = fechaNacimiento1;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia1) {
        provincia = provincia1;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton1) {
        canton = canton1;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito1) {
        distrito = distrito1;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna1) {
        contrasenna = contrasenna1;
    }

    public ViewGroup renderizar(){
        ViewGroup vg = null;
        return vg;
    }

}
