package com.example.aplicacionsensores;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager = null;
    private Sensor sensorTemperatura = null;
    private TextView tvTemperatura;
    private ListView lvTemperaturas;
    ArrayList<String> temp = new ArrayList();
    private ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorTemperatura = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        tvTemperatura = findViewById(R.id.tvTemperatura);
        lvTemperaturas = findViewById(R.id.lvTemperaturas);

        if (sensorTemperatura == null){
            Toast.makeText(getApplicationContext(), "No hay sensor de Temperatura", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Hay sensor de Temperatura", Toast.LENGTH_SHORT).show();
            sensorManager.registerListener(this, sensorTemperatura, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {


        float[] prueba = sensorEvent.values;
        temp.add("Temperaura: " + String.valueOf(prueba[0]));
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,temp);
        lvTemperaturas.setAdapter(adaptador);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}