package com.health.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        EditText weight = findViewById(R.id.weight);
        EditText heightFeet = findViewById(R.id.heightFeet);
        EditText heightInch = findViewById(R.id.heightInch);
        Button btnBMI = findViewById(R.id.btnBMI);
        TextView result = findViewById(R.id.result);

        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userWeight = Integer.parseInt(weight.getText().toString());
                int userHeightFeet = Integer.parseInt(heightFeet.getText().toString());
                int userHeightInch = Integer.parseInt(heightInch.getText().toString());

                // BMI = weight(kg)/height*height(meter)
                int totalInch = userHeightFeet*12 + userHeightInch;

                double totalCm = totalInch*2.54;

                double totalM = totalCm/100;

                double bmi = userWeight/(totalM*totalM);

                if(bmi>25){
                    result.setText(R.string.overweight);
                    result.setTextColor(getResources().getColor(R.color.red));
                }else if(bmi<18){
                    result.setText(R.string.underweight);
                    result.setTextColor(getResources().getColor(R.color.yellow));
                }else{
                    result.setText(R.string.healthy);
                    result.setTextColor(getResources().getColor(R.color.green));
                }
            }
        });
    }
}