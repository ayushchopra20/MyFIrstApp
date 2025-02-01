package com.example.myfirstapp;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private TextView nTv_today_date;
    private Button mBtn_left_btn, mBtn_right_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nTv_today_date = (TextView) findViewById(R.id.tv_date_now);
        Date d = new Date();
        CharSequence s = DateFormat.format(  "MMMM dd, yyyy ", d.getTime());

        nTv_today_date.setText(s);
        print_logs("this is my first app");

        mBtn_left_btn = (Button) findViewById(R.id.btn_lft_btn);
        mBtn_right_btn = (Button) findViewById(R.id.btn_rgt_btn);

        mBtn_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                print_logs("Left button is clicked");
            }
        });

        mBtn_right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                print_logs("Right button is clicked");
            }
        });



    }

    private int print_logs(String print_message){
        Log.v("Main Activity", print_message);
        return 1;
    }
}