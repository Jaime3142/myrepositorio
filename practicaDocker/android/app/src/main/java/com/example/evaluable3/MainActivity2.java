package com.example.evaluable3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private TextView tvNombre, tvPsp, tvAd, tvCiber, tvIngles, tvInterfaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvNombre = findViewById(R.id.username);
        tvPsp = findViewById(R.id.nPSP);
        tvAd = findViewById(R.id.nAD);
        tvCiber = findViewById(R.id.nC);
        tvIngles = findViewById(R.id.nI);
        tvInterfaces = findViewById(R.id.nInter);

        // Recogemos los datos del intent
        String nombre = getIntent().getStringExtra("nombre");
        int psp = getIntent().getIntExtra("psp", 0);
        int ad = getIntent().getIntExtra("ad", 0);
        int ciber = getIntent().getIntExtra("ciber", 0);
        int ingles = getIntent().getIntExtra("ingles", 0);
        int interfaces = getIntent().getIntExtra("interfaces", 0);

        // Seteamos el texto en cada TextView
        tvNombre.setText(nombre);
        tvPsp.setText(String.valueOf(psp));
        tvAd.setText(String.valueOf(ad));
        tvCiber.setText(String.valueOf(ciber));
        tvIngles.setText(String.valueOf(ingles));
        tvInterfaces.setText(String.valueOf(interfaces));
    }
}
