package com.example.ptmarketing04.pruebaservice;


import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ptmarketing04 on 19/04/2017.
 */

public class Cronometro extends Service {

    private Timer temporizador = new Timer();
    private static final long INTERVALO_ACTUALIZACION = 10; // En ms
    public static MainActivity UPDATE_LISTENER;
    private double cronometro = 0;
    private Handler handler;

    /**
     * Establece quien va ha recibir las actualizaciones del cronometro
     *
     * @param mainService
     */
    public static void setUpdateListener(MainActivity mainService) {
        UPDATE_LISTENER = mainService;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                UPDATE_LISTENER.actualizarCronometro(cronometro);
            }
        };

        iniciarCronometro();

    }

    @Override
    public void onDestroy() {
        pararCronometro();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void iniciarCronometro() {
        temporizador.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                cronometro += 0.01;
                handler.sendEmptyMessage(0);
            }
        }, 0, INTERVALO_ACTUALIZACION);
    }

    private void pararCronometro() {
        if (temporizador != null)
            temporizador.cancel();
    }
}
