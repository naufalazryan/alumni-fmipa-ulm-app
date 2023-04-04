package com.naufalazryan.alumnimipaulm.pekerjaan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.naufalazryan.alumnimipaulm.AboutActivity;
import com.naufalazryan.alumnimipaulm.MainActivity;
import com.naufalazryan.alumnimipaulm.ModelResponse.PekerjaanModelResponse.PekerjaanDataModel;
import com.naufalazryan.alumnimipaulm.ModelResponse.PekerjaanModelResponse.PekerjaanResponse;
import com.naufalazryan.alumnimipaulm.R;
import com.naufalazryan.alumnimipaulm.SessionManager;
import com.naufalazryan.alumnimipaulm.api.APIService;
import com.naufalazryan.alumnimipaulm.api.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PekerjaanActivity extends AppCompatActivity implements View.OnClickListener {

    APIService apiService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Animation rotateOpenAnimation, rotateCloseAnimation, fromBottomAnimation, toBottomAnimation;
    SessionManager sessionManager;
    String nim;
    public static PekerjaanActivity pekerjaanActivity;
    FloatingActionButton mainBtn, add, update;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pekerjaan);
        view();
    }

    private void view() {
        sessionManager = new SessionManager(this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        apiService = RetrofitClient.getClient().create(APIService.class);
        nim = sessionManager.getUserDetail().get(SessionManager.ALU_NIM);
        pekerjaanActivity = this;
        refresh();

        findViewById(R.id.arrow_back).setOnClickListener(this);
        findViewById(R.id.about).setOnClickListener(this);
        add = findViewById(R.id.add);
        update = findViewById(R.id.update);
        mainBtn = findViewById(R.id.floatingActionButton);
        rotateOpenAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_open_animation);
        rotateCloseAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_close_animation);
        fromBottomAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_bottom_animation);
        toBottomAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_bottom_animation);


        TextView activity = findViewById(R.id.activity);

        mainBtn.setOnClickListener(view -> {

            if(isOpen){
                add.startAnimation(fromBottomAnimation);
                add.setClickable(false);
                update.startAnimation(fromBottomAnimation);
                update.setClickable(false);
                isOpen = false;
            } else {
                add.startAnimation(toBottomAnimation);
                add.setClickable(true);
                update.startAnimation(toBottomAnimation);
                update.setClickable(true);
                isOpen = true;
            }

        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PekerjaanActivity.this, AddPekerjaanActivity.class));
                overridePendingTransition(R.anim.slide_from_top, android.R.anim.accelerate_decelerate_interpolator);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PekerjaanActivity.this, UpdatePekerjaanActivity.class));
                overridePendingTransition(R.anim.slide_from_top, android.R.anim.accelerate_decelerate_interpolator);
            }
        });




        activity.setText("Pekerjaan");
    }

    private void refresh() {

        Call<PekerjaanResponse> pekerjaan = apiService.readPekerjaan(nim);
        Log.d("response",nim);
        pekerjaan.enqueue(new Callback<PekerjaanResponse>() {
            @Override
            public void onResponse(Call<PekerjaanResponse> call, Response<PekerjaanResponse> response) {
                List<PekerjaanDataModel> pekerjaanDataModel = response.body().getData();
                Log.d("response",pekerjaanDataModel.toString());
                adapter = new PekerjaanAdapter(pekerjaanDataModel);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PekerjaanResponse> call, Throwable t) {
                Log.d("Retrofit Get", t.toString());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arrow_back:
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(R.anim.slide_from_top, android.R.anim.accelerate_decelerate_interpolator);
                break;
            case R.id.about:
                startActivity(new Intent(this, AboutActivity.class));
                overridePendingTransition(R.anim.slide_from_top, android.R.anim.accelerate_decelerate_interpolator);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.slide_from_top, android.R.anim.accelerate_decelerate_interpolator);
    }
}