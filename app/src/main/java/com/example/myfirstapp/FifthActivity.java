package com.example.myfirstapp;

import android.animation.ObjectAnimator;
import android.widget.ImageView;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.LinkedList;
import java.util.Queue;
import android.view.View;
import android.widget.Button;

public class FifthActivity extends AppCompatActivity implements SensorEventListener{

    private long lastUpdateTime = 0;
    private final int SMOOTHING_WINDOW = 5; // Number of values to average
    private final Queue<Float> valueQueue = new LinkedList<>();
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private SensorGraphView sensorGraphView;
    private ImageView bulbImage;

    private Sensor accelerometer;
    // Add thresholds for variance
    final float LOW_THRESHOLD = 10f;
    final float HIGH_THRESHOLD = 12f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);


        sensorGraphView = findViewById(R.id.sensorGraph);
        Button backButton = findViewById(R.id.backButton);
        bulbImage = findViewById(R.id.bulbImage);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the previous activity
                onBackPressed();
            }
        });

        // Set up the sensor manager and accelerometer sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }


        // Register the sensor listener
        if (accelerometerSensor != null) {
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            throw new RuntimeException("Accelerometer not available on this device.");
        }

    }

    private void updateBulbAnimation(float motionVariance) {
        if (motionVariance < LOW_THRESHOLD) {
            // Low motion condition (dim bulb)
            bulbImage.setImageResource(R.drawable.bulb_dim);
        } else if (motionVariance < HIGH_THRESHOLD) {
            // Medium motion condition
            bulbImage.setImageResource(R.drawable.bulb_medium);
        } else {
            // High motion condition (bright bulb)
            bulbImage.setImageResource(R.drawable.bulb_bright);
        }

        // Optional: Add animation for smooth transition
        ObjectAnimator animator = ObjectAnimator.ofFloat(bulbImage, "alpha", 1f);
        animator.setDuration(100); // Animation duration
        animator.start();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        long currentTime = System.currentTimeMillis();


        if ((currentTime - lastUpdateTime) > 100) {
            float ax = event.values[0];
            float ay = event.values[1];
            float az = event.values[2];

            float magnitude = (float) Math.sqrt(ax * ax + ay * ay + az * az);


            // Add the new value to the queue
            if (valueQueue.size() >= SMOOTHING_WINDOW) {
                valueQueue.poll(); // Remove the oldest value
            }
            valueQueue.add(magnitude);

            // Calculate the average of the values in the queue
            float smoothedValue = 0;
            for (float val : valueQueue) {
                smoothedValue += val;
            }
            smoothedValue /= valueQueue.size();

            // Pass the smoothed value to the graph
            sensorGraphView.addSensorValue(smoothedValue);
            // Update bulb animation based on smoothed value
            updateBulbAnimation(smoothedValue);

            lastUpdateTime = currentTime;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No implementation needed for this example
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the sensor listener to save battery when the app is not in the foreground
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Re-register the sensor listener when the app resumes
        if (sensorManager != null && accelerometerSensor != null) {
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    public void goBack(View view) {
        finish(); // Close the current activity and go back to the previous one
    }
}

