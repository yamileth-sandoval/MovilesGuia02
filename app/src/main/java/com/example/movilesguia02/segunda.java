package com.example.movilesguia02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class segunda extends AppCompatActivity{
    AutoCompleteTextView actMateria,actComida,actDeporte;
    ProgressBar pgbCargar;
    String [] materia={"Matematica","Programación","Fisica","Psicología"};
    String [] comida={"Pupusas","Tacos","Sopa de gallina"};
    String [] deporte={"Fútbol","Baloncesto","Fútbol Americano"};
    String mensaje="FAVORITOS: \n";
    private int mProgressStatus=0;
    private Handler mHandler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        actMateria=findViewById(R.id.actMateria);
        actComida=findViewById(R.id.actComida);
        actDeporte=findViewById(R.id.actDeporte);
        pgbCargar=findViewById(R.id.pgbCargar);

        ArrayAdapter<String> adapterMateria=new ArrayAdapter<>(this,android.R.layout.select_dialog_item,materia);
        ArrayAdapter<String> adapterComida=new ArrayAdapter<>(this,android.R.layout.select_dialog_item,comida);
        ArrayAdapter<String> adapterDeporte=new ArrayAdapter<>(this,android.R.layout.select_dialog_item,deporte);

        actMateria.setThreshold(1);//para definir despues de cuantos caracteres empezara a sugerir
        actMateria.setAdapter(adapterMateria);
        actComida.setThreshold(1);
        actComida.setAdapter(adapterComida);
        actDeporte.setThreshold(1);
        actDeporte.setAdapter(adapterDeporte);
    }

    private void setProgressValue(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatus<100){
                    mProgressStatus+=5;
                    android.os.SystemClock.sleep(500);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            pgbCargar.setProgress(mProgressStatus);
                        }
                    });
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mensaje+="Materia: "+actMateria.getText().toString()+",\n";
                        mensaje+="Comida: "+actComida.getText().toString()+",\n";
                        mensaje+="Deporte: "+actDeporte.getText().toString()+".";
                        Toast.makeText(segunda.this,mensaje, Toast.LENGTH_LONG).show();
                        mensaje="FAVORITOS: \n";
                    }
                });
            }
        }).start();
    }

    public void clickProcesar(View v){
        if(!actMateria.getText().toString().isEmpty() && !actComida.getText().toString().isEmpty() && !actDeporte.getText().toString().isEmpty()){
            mProgressStatus=0;
            setProgressValue();
        }else {
            Toast.makeText(this,"Llene todos los campos.", Toast.LENGTH_LONG).show();
        }
    }
}
