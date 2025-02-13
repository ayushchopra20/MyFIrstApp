package com.example.myfirstapp;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FourthActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fourth);


        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Get the required sensors
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        TextView accelerometerSnsrDta = findViewById(R.id.accelerometerSnsrDta);
        TextView proximitySnsrDta = findViewById(R.id.proximitySnsrDta);



        // Get the list of available sensors
//        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensorInfo = new StringBuilder();
//        display only two sensors
        String accelerometerData = String.valueOf(displaySensorDetails(sensorInfo, "Accelerometer", accelerometer));
        String proximityData = String.valueOf(displaySensorDetails(sensorInfo, "Proximity", proximity));
        accelerometerSnsrDta.setText(accelerometerData);
        proximitySnsrDta.setText(proximityData);

        // Get the TextView and display sensor details

//        for (Sensor sensor : sensorList) {
//            sensorInfo.append("Name: ").append(sensor.getName()).append("\n");
//            sensorInfo.append("Type: ").append(sensor.getStringType()).append("\n");
//            sensorInfo.append("Vendor: ").append(sensor.getVendor()).append("\n");
//            sensorInfo.append("Version: ").append(sensor.getVersion()).append("\n");
//            sensorInfo.append("Maximum Range: ").append(sensor.getMaximumRange()).append("\n");
//            sensorInfo.append("Resolution: ").append(sensor.getResolution()).append("\n");
//            sensorInfo.append("Power: ").append(sensor.getPower()).append(" mA\n");
//            sensorInfo.append("---------------------------------\n");
//        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ll_4th), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Method to display sensor details
    private StringBuilder displaySensorDetails(StringBuilder sensorInfo, String sensorName, Sensor sensor) {
        if (sensor != null) {
            sensorInfo.setLength(0); // Clear previous data in StringBuilder
            sensorInfo.append(sensorName).append(" Sensor:\n");
            sensorInfo.append("Name: ").append(sensor.getName()).append("\n");
            sensorInfo.append("Power: ").append(sensor.getPower()).append(" mA\n");
            sensorInfo.append("Maximum Range: ").append(sensor.getMaximumRange()).append("\n");
            sensorInfo.append("Resolution: ").append(sensor.getResolution()).append("\n");
            sensorInfo.append("Min Delay: ").append(sensor.getMinDelay()).append("\n");
            sensorInfo.append("power consumption (mA): ").append(sensor.getPower()).append("\n");
        } else {
            sensorInfo.append(sensorName).append(" sensor not available.\n");
        }
        return sensorInfo;
    }
}