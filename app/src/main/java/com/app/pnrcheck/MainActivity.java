package com.app.pnrcheck;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
  EditText pnrNum;
  Button pnrBtn;
TextView trainName;
    TextView jDate;
    TextView booking;
    TextView current;
    TextView clas;
    TextView chart;
    TextView tvError;
    ProgressBar progressBar;
    RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pnrNum=(EditText) findViewById(R.id.pnrNum);
        pnrBtn=(Button) findViewById(R.id.pnrBtn);
        trainName=(TextView)findViewById(R.id.trainName);
        jDate=(TextView)findViewById(R.id.jDate);
        booking=(TextView)findViewById(R.id.bookingStatus);
       current=(TextView)findViewById(R.id.currentStatus);
        clas=(TextView)findViewById(R.id.clas);
        chart=(TextView)findViewById(R.id.chartStatus);
        tvError=(TextView)findViewById(R.id.tvError);
         progressBar=(ProgressBar)findViewById(R.id.pBar);
         relativeLayout=(RelativeLayout)findViewById(R.id.relLayout);

        pnrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pnrnumber=pnrNum.getText().toString();
                Log.e("TAGG",pnrnumber);
                check(pnrnumber);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(pnrNum.getWindowToken(), 0);

            }
        });
    }

    private void check(String pnr) {
        if(TextUtils.isEmpty(pnr)) {
            pnrNum.setError("Please Enter PNR");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("https://pnr-status-indian-railway.p.rapidapi.com/rail/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);
        Call<PnrModel> call= api.search(pnr);
        call.enqueue(new Callback<PnrModel>() {
            @Override
            public void onResponse(Call<PnrModel> call, Response<PnrModel> response) {

                PnrModel body = response.body();


                StringBuilder stringBuilder;
                stringBuilder = new StringBuilder();
                stringBuilder.append("===>>>>");
                if(body!=null) {
                    Log.e("jsonOutput", body.getPassenger().get(0).getBooking_status());

                    trainName.setText(body.getTrain_name().toUpperCase());
                    jDate.setText(body.getDeparture_data().getDeparture_date().replaceAll("\n","  Time : "));
                    booking.setText("Booking Status : " + body.getPassenger().get(0).getBooking_status());
                    current.setText("Current Status : " + body.getPassenger().get(0).getCurrent_status());
                    clas.setText("Class : " + body.getClas());
                    chart.setText(body.getChart_status());
                    progressBar.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    pnrNum.setText("");
                }


            }

            @Override
            public void onFailure(Call<PnrModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                tvError.setVisibility(View.VISIBLE);
            }
        });

    }

}