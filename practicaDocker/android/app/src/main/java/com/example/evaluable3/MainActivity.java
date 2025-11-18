package com.example.evaluable3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String BASE = "http://10.0.2.2:8080/";
    private static final String URL_LISTAR = BASE + "listar.php";
    private static final String URL_INSERTAR = BASE + "insertar.php";

    private EditText name;
    private EditText psp;
    private EditText ad;
    private EditText ciber;
    private EditText ingles;
    private EditText interfaces;
    private Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name =findViewById(R.id.nombre);
        psp = findViewById(R.id.psp);
        ad = findViewById(R.id.ad);
        ciber = findViewById(R.id.ciber);
        ingles = findViewById(R.id.ingles);
        interfaces = findViewById(R.id.interfaces);
        guardar = findViewById(R.id.guardar);



        private void nuevoAlumno(){
            String nombre = name.getText().toString().trim();
            String proceso = psp.getText().toString().trim();
            String acceso = ad.getText().toString().trim();
            String ciberseguridad = ciber.getText().toString().trim();
            String english = ingles.getText().toString().trim();
            String interf = interfaces.getText().toString().trim();
        }

    }
}