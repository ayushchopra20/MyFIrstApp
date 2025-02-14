package com.example.myfirstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FifthActivity extends AppCompatActivity {

    private TextView mtv_show_sens_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fifth);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ll_5th), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        mtv_show_sens_data = (TextView) findViewById(R.id.tv_new_sens_data);
        String new_data = String.valueOf(getIntent().getStringExtra("new_sensor_data"));
        mtv_show_sens_data.setText(new_data);


    }

    public void goBack(View view) {
        finish(); // Close the current activity and go back to the previous one
    }
}