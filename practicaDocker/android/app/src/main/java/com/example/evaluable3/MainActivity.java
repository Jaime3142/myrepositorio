package com.example.evaluable3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

    public class MainActivity extends AppCompatActivity {

    private static final String BASE = "http://10.0.2.2:8080/";
    private static final String URL_INSERTAR = BASE + "insertar.php";

    private EditText name, psp, ad, ciber, ingles, interfaces;
    private Button save;
    private Button mostrar;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nombre);
        psp = findViewById(R.id.psp);
        ad = findViewById(R.id.ad);
        ciber = findViewById(R.id.ciber);
        ingles = findViewById(R.id.ingles);
        interfaces = findViewById(R.id.interfaces);
        save = findViewById(R.id.guardar);
        mostrar = findViewById(R.id.show);

        queue = Volley.newRequestQueue(this);

        save.setOnClickListener(v -> nuevoAlumno());
        mostrar.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, MainActivity3.class);
            startActivity(i);
        });
    }


    private void nuevoAlumno() {

        String nombre = name.getText().toString().trim();
        String notaPsp = psp.getText().toString().trim();
        String notaAd = ad.getText().toString().trim();
        String notaCiber = ciber.getText().toString().trim();
        String notaIngles = ingles.getText().toString().trim();
        String notaInterfaces = interfaces.getText().toString().trim();

        // Validaciones
        if (nombre.isEmpty() || notaPsp.isEmpty() || notaAd.isEmpty() ||
                notaCiber.isEmpty() || notaIngles.isEmpty() || notaInterfaces.isEmpty()) {

            toast("Rellena todos los campos");
            return;
        }

        StringRequest req = new StringRequest(
                Request.Method.POST,
                URL_INSERTAR,
                response -> {
                    toast("Insertado correctamente");
                    limpiarCampos();
                },
                error -> {
                    Log.e("VOLLEY", "Error insertar: " + error);
                    toast("Error al insertar");
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nombre", nombre);
                params.put("psp", notaPsp);
                params.put("ad", notaAd);
                params.put("ciber", notaCiber);
                params.put("ingles", notaIngles);
                params.put("interfaces", notaInterfaces);
                return params;
            }
        };

        queue.add(req);
    }

    private void limpiarCampos() {
        name.setText("");
        psp.setText("");
        ad.setText("");
        ciber.setText("");
        ingles.setText("");
        interfaces.setText("");
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}

