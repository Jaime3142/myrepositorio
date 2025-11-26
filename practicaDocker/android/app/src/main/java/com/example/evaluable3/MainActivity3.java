package com.example.evaluable3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity3 extends AppCompatActivity {
    private EditText name;
    private Button buscar;
    private RequestQueue queue;

    private static final String BASE = "http://10.0.2.2:8080/";
    private static final String URL_OBTENER = BASE + "alumno.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        name = findViewById(R.id.nombre_buscar);
        buscar = findViewById(R.id.buscar);
        queue = Volley.newRequestQueue(this);

        buscar.setOnClickListener(v -> buscarAlumno());
    }

    private void buscarAlumno() {
        String nombre = name.getText().toString().trim();

        if (nombre.isEmpty()) {
            toast("Introduce un nombre");
            return;
        }

        // Construir URL con el parámetro 'nombre' en GET
        String urlConParametros = URL_OBTENER + "?nombre=" + nombre;

        StringRequest req = new StringRequest(
                Request.Method.GET,
                urlConParametros,
                response -> {
                    Log.d("RESP", response);

                    try {
                        JSONObject obj = new JSONObject(response);

                        if (obj.has("error")) {
                            toast("Alumno no encontrado");
                            return;
                        }

                        // PASAR DATOS A OTRA ACTIVITY
                        Intent i = new Intent(MainActivity3.this, MainActivity2.class);
                        i.putExtra("nombre", obj.getString("nombre"));
                        i.putExtra("psp", obj.getInt("psp"));
                        i.putExtra("ad", obj.getInt("ad"));
                        i.putExtra("ciber", obj.getInt("ciber"));
                        i.putExtra("ingles", obj.getInt("ingles"));
                        i.putExtra("interfaces", obj.getInt("interfaces"));

                        startActivity(i);

                    } catch (Exception e) {
                        toast("Error procesando datos");
                        Log.e("VOLLEY", "JSON error", e);
                    }
                },
                error -> {
                    Log.e("VOLLEY", "Error: " + error);
                    toast("Error al buscar");
                }
        );

        queue.add(req);
    }

    private void toast(String m) {
        Toast.makeText(this, m, Toast.LENGTH_SHORT).show();
    }
}
