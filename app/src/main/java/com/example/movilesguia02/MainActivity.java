package com.example.movilesguia02;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener{
    EditText edtCorreo;
    EditText edtCredencial;
    Button btnIniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // inicializar las variables
        edtCorreo=findViewById(R.id.edtCorreo);
        edtCredencial=findViewById(R.id.edtCredencial);
        btnIniciar=findViewById(R.id.btnIniciar);

        btnIniciar.setOnLongClickListener(this);
    }
    private boolean verificar(){
        boolean entrar =false;
        if(!edtCorreo.getText().toString().isEmpty() && !edtCredencial.getText().toString().isEmpty()){
            entrar=true;
        }
        return  entrar;
    }
    public void Iniciar_Sesion(){
        if(verificar()){
            Intent nueva=new Intent(this,segunda.class);
            startActivity(nueva);
        }else {
            Toast.makeText(this, "Por favor llene todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.btnIniciar:
                Iniciar_Sesion();
                break;
        }
        return false;
    }
    public void clickMisDatos(View v){
        Intent misDatos=new Intent(this,misDatos.class);
        startActivity(misDatos);
    }
}
