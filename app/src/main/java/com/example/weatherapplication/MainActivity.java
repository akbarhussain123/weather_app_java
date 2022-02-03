package com.example.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapplication.retopit.ApiClint;
import com.example.weatherapplication.retopit.ApiInterface;
import com.example.weatherapplication.retopit.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etCity;
    ImageView ivSearch;
    TextView tvTemp, tvDesc, tvPressure, tvHumidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCity = findViewById(R.id.etCity);
        ivSearch = findViewById(R.id.iv);
        tvTemp = findViewById(R.id.tvTem);
        tvDesc = findViewById(R.id.tvDescription);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvPressure = findViewById(R.id.tvPressure);

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etCity.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    etCity.setError("Enter city");
                    return;
                }
                getWeatherData(name);

            }
        });

    }

    private void getWeatherData(String name) {
        ApiInterface apiInterface = ApiClint.getClint().create(ApiInterface.class);
        Call<Example> call = apiInterface.getWeatherData(name);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()) {
                    tvTemp.setText(response.body().getMain().getTemp() + " C");
                    tvDesc.setText(response.body().getMain().getFeels_like());
                    tvHumidity.setText(response.body().getMain().getHumidity());
                    tvPressure.setText(response.body().getMain().getPressure());
                } else {
                    Toast.makeText(MainActivity.this, "Enter Correct City Name", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Network Problem", Toast.LENGTH_SHORT).show();

            }
        });


    }


}