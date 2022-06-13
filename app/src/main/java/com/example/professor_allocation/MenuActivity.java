package com.example.professor_allocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button btCurso;
    private Button btDepartamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btCurso = findViewById(R.id.btCurso);
        btDepartamento = findViewById(R.id.btDepartamento);

        btCurso.setOnClickListener(view ->{
            Intent mensageiro = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mensageiro);

        });

        btDepartamento.setOnClickListener(view ->{
            Intent mensageiroDep = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mensageiroDep);

        });
    }
}