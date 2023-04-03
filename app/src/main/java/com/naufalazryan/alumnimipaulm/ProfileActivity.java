package com.naufalazryan.alumnimipaulm;

import static com.naufalazryan.alumnimipaulm.Config.IMAGE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.naufalazryan.alumnimipaulm.biodata.UpdateBiodataActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    SessionManager sessionManager;
    CircleImageView profil;
    TextView nama, nim, email, telepon, update;
    String aluNama, aluNim, aluEmail, aluTelp;
    ImageView btnBack;
    Button btnLogout;

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sessionManager = new SessionManager(ProfileActivity.this);

        profil = findViewById(R.id.profil);
        nama = findViewById(R.id.nama);
        nim = findViewById(R.id.nim);
        email = findViewById(R.id.email);
        telepon = findViewById(R.id.telepon);
        update = findViewById(R.id.update);
        btnBack = findViewById(R.id.btnBack);
        btnLogout = findViewById(R.id.btnLogout);

        aluNim = sessionManager.getUserDetail().get(SessionManager.ALU_NIM);
        aluNama = sessionManager.getUserDetail().get(SessionManager.ALU_NAMA);
        aluEmail = sessionManager.getUserDetail().get(SessionManager.ALU_EMAIL);
        aluTelp = sessionManager.getUserDetail().get(SessionManager.ALU_HP);

        nim.setText(aluNim);
        nama.setText(aluNama);
        email.setText(aluEmail);
        telepon.setText(aluTelp);


        update.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), UpdateBiodataActivity.class)));
        btnBack.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
        Picasso.get().load(IMAGE_URL + sessionManager.getFoto()).into(profil);

        
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logoutSession();
            }
        });
    }
}