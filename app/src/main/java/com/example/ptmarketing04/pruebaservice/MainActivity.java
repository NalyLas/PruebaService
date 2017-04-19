package com.example.ptmarketing04.pruebaservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTime = (TextView)findViewById(R.id.tvTime);

        Button startButton = (Button) findViewById(R.id.btIniciar);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                iniciarCronometro();
            }
        });

        Button stopButton = (Button) findViewById(R.id.btParar);
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pararCronometro();
            }
        });

        Cronometro.setUpdateListener(this);
    }


    //En nuestra aplicación no nos interesa utilizar este metodo puesto que
    //nuestro servicio no interactua con ningua interfaz
    //y queremos que aunque sedestruya la aplicación siga realizando el proceso de comprobacion
    @Override
    protected void onDestroy() {
        // Antes de cerrar la aplicacion se para el servicio (el cronometro)
        pararCronometro();
        super.onDestroy();
    }

    /**
     * Inicia el servicio
     */
    private void iniciarCronometro() {
        Intent service = new Intent(this, Cronometro.class);
        startService(service);
    }

    /**
     * Finaliza el servicio
     */
    private void pararCronometro() {
        Intent service = new Intent(this, Cronometro.class);
        stopService(service);
    }

    /**
     * Actualiza en la interfaz de usuario el tiempo cronometrado
     *
     * @param tiempo
     */
    public void actualizarCronometro(double tiempo) {
        tvTime.setText(String.format("%.2f", tiempo) + "s");
    }

}
