package com.example.caribejobs.Modelos;

import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Entity;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.xml.transform.Result;

public class API {

    Connection conexion;
    String linkServidor = "https://f2cd3fb1e7e1.ngrok.io";

    public API () {
    }

    public String ConexionSQL (String sql){
        conexion = null;
        JSONObject jsonObject = null;
        String json = "";
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = null;
            HttpURLConnection conn;
            try{
                url = new URL(sql);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                json = "";

                while((inputLine = in.readLine()) != null){
                    response.append(inputLine);
                }
                json = response.toString();
                return json;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Log.d("Error",e.toString());
        }
        return json;
    }

    public int Login(String usuario, String contrasenna){
        String sql  = linkServidor+"/persona/"+usuario+"/"+contrasenna;
        int res = 2;
        JSONObject jsonObject = null;
        try{
            String json = ConexionSQL(sql);
            JSONArray jsonArr = null;
            jsonArr = new JSONArray(json);

            for(int i = 0; i < jsonArr.length(); i++){
                jsonObject = jsonArr.getJSONObject(i);
            }
            if(Integer.parseInt(jsonObject.optString("loginusuario")) == 0){
                res = 1;
            } else {
                res = 5;
            }
            return res;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }


    public JSONArray getPerfil(String usuario){
        String sql  = linkServidor+"/persona/"+usuario;
        //JSONObject res = null;
        JSONArray jsonArr=null;
        JSONObject jsonObject = null;
        try{
            String json = ConexionSQL(sql);
            jsonArr = new JSONArray(json);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArr;
    }

    public JSONArray getPersonas(){
        String sql  = linkServidor+"/personas";
        //JSONObject res = null;
        JSONArray jsonArr=null;
        JSONObject jsonObject = null;
        try{
            String json = ConexionSQL(sql);
            jsonArr = new JSONArray(json);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Salida2",e.toString());
        }
        return jsonArr;
    }


    public JSONArray getProvincias(){
        String sql  = linkServidor+"/provincias";
        JSONArray jsonArr=null;
        JSONObject jsonObject = null;
        try{
            String json = ConexionSQL(sql);
            jsonArr = new JSONArray(json);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Salida2",e.toString());
        }
        return jsonArr;
    }

    public String ConexionSQLPost (List<NameValuePair> values){
        Log.d("Flag","Flag1");
        String sql  = linkServidor+"/registro";
        conexion = null;
        JSONObject jsonObject = null;
        String respuesta="";
        String json = "";
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = null;
            HttpURLConnection conn;
            try{
                final HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(linkServidor+"/registro");
                post.setEntity(new UrlEncodedFormEntity(values));
                HttpResponse httpResponse=client.execute(post);
                respuesta = EntityUtils.toString(httpResponse.getEntity());
                Log.d("Post",respuesta);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Log.d("Error",e.toString());
        }
        return respuesta;
    }




    public String PostHabilidad (List<NameValuePair> values){
        Log.d("Flag","Flag1");
        String sql  = linkServidor+"/habilidad";
        conexion = null;
        JSONObject jsonObject = null;
        String respuesta="";
        String json = "";
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = null;
            HttpURLConnection conn;
            try{
                final HttpClient client = new DefaultHttpClient();

                HttpPost post = new HttpPost(linkServidor+"/habilidad");
                post.setEntity(new UrlEncodedFormEntity(values));
                HttpResponse httpResponse=client.execute(post);
                respuesta = EntityUtils.toString(httpResponse.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Log.d("Error",e.toString());
        }
        return respuesta;
    }



    public JSONArray getProfesiones(){
        String sql  = linkServidor+"/profesion";
        //JSONObject res = null;
        JSONArray jsonArr=null;
        JSONObject jsonObject = null;
        try{
            String json = ConexionSQL(sql);
            jsonArr = new JSONArray(json);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Salida2",e.toString());
        }
        return jsonArr;

    }

    public JSONArray getProfesionEspecifica(String habilidad){
        String sql  = linkServidor+"/profesion/id/"+habilidad;
        //JSONObject res = null;
        JSONArray jsonArr=null;
        JSONObject jsonObject = null;
        try{
            String json = ConexionSQL(sql);
            jsonArr = new JSONArray(json);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArr;

    }

    public JSONArray getZonaEspecifica(String habilidad){
        String sql  = linkServidor+"/habilidad/zona/"+habilidad;
        //JSONObject res = null;
        JSONArray jsonArr=null;
        JSONObject jsonObject = null;
        try{
            String json = ConexionSQL(sql);
            jsonArr = new JSONArray(json);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArr;

    }

    public JSONArray getHorarioHabilidad(String habilidad){
        String sql  = linkServidor+"/habilidad/horario/"+habilidad;
        JSONArray jsonArr=null;
        JSONObject jsonObject = null;
        try{
            String json = ConexionSQL(sql);
            jsonArr = new JSONArray(json);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Salida2",e.toString());
        }
        return jsonArr;

    }


    public JSONArray getReferenciaTrabajo(String habilidad){
        String sql  = linkServidor+"/habilidad/referencia/"+habilidad;
        //JSONObject res = null;
        JSONArray jsonArr=null;
        JSONObject jsonObject = null;
        try{
            String json = ConexionSQL(sql);
            jsonArr = new JSONArray(json);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Salida2",e.toString());
        }
        return jsonArr;

    }


    public JSONArray getIdHabilidad(){
        String sql  = linkServidor+"/habilidad/id";
        //JSONObject res = null;
        JSONArray jsonArr=null;
        JSONObject jsonObject = null;
        try{
            String json = ConexionSQL(sql);
            jsonArr = new JSONArray(json);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Salida2",e.toString());
        }
        return jsonArr;

    }


    public String postZona(List<NameValuePair> values){
        Log.d("Flag","Flag1");
        String sql  = linkServidor+"/zona";
        conexion = null;
        JSONObject jsonObject = null;
        String respuesta="";
        String json = "";
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = null;
            HttpURLConnection conn;
            try{
                final HttpClient client = new DefaultHttpClient();

                HttpPost post = new HttpPost(linkServidor+"/zona");
                post.setEntity(new UrlEncodedFormEntity(values));
                HttpResponse httpResponse=client.execute(post);
                respuesta = EntityUtils.toString(httpResponse.getEntity());
                Log.d("Post->",respuesta);

            } catch (IOException e) {
                e.printStackTrace();
                Log.d("Bandera",e.toString());
            }
        } catch (Exception e) {
            Log.d("Error",e.toString());
        }
        return respuesta;
    }


    public JSONArray getProfesion(String usuario){
        String sql  = linkServidor+"/profesion/usuario/"+usuario;
        //JSONObject res = null;
        JSONArray jsonArr=null;
        JSONObject jsonObject = null;
        try{
            String json = ConexionSQL(sql);
            jsonArr = new JSONArray(json);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Salida2",e.toString());
        }
        return jsonArr;
    }

    public String postHorario(List<NameValuePair> values){
        Log.d("Flag","Flag1");
        String sql  = linkServidor+"/horario";
        conexion = null;
        JSONObject jsonObject = null;
        String respuesta="";
        String json = "";
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = null;
            HttpURLConnection conn;
            try{
                final HttpClient client = new DefaultHttpClient();

                HttpPost post = new HttpPost(linkServidor+"/horario");
                post.setEntity(new UrlEncodedFormEntity(values));
                HttpResponse httpResponse=client.execute(post);
                respuesta = EntityUtils.toString(httpResponse.getEntity());
                Log.d("Post->",respuesta);
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("Bandera",e.toString());
            }
        } catch (Exception e) {
            Log.d("Error",e.toString());
        }
        return respuesta;
    }

    public String postReferencia(List<NameValuePair> values){
        String sql  = linkServidor+"/referencia";
        conexion = null;
        JSONObject jsonObject = null;
        String respuesta="";
        String json = "";
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = null;
            HttpURLConnection conn;
            try{
                final HttpClient client = new DefaultHttpClient();

                HttpPost post = new HttpPost(linkServidor+"/referencia");
                post.setEntity(new UrlEncodedFormEntity(values));
                HttpResponse httpResponse=client.execute(post);
                respuesta = EntityUtils.toString(httpResponse.getEntity());
                Log.d("Post->",respuesta);
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("Bandera",e.toString());
            }
        } catch (Exception e) {
            Log.d("Error",e.toString());
        }
        return respuesta;
    }

    public JSONArray getPersonasList(){
        String sql  = linkServidor+"/vista/";
        //JSONObject res = null;
        JSONArray jsonArr=null;
        JSONObject jsonObject = null;
        try{
            String json = ConexionSQL(sql);
            jsonArr = new JSONArray(json);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Salida2",e.toString());
        }
        return jsonArr;
    }

    public JSONArray getHabilidadesFiltro(String profesion,String provincia){
        String sql  = linkServidor+"/vista/filtro/"+profesion+"/"+provincia;
        //JSONObject res = null;
        JSONArray jsonArr=null;
        JSONObject jsonObject = null;
        try{
            String json = ConexionSQL(sql);
            jsonArr = new JSONArray(json);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Salida2",e.toString());
        }
        return jsonArr;
    }

}
