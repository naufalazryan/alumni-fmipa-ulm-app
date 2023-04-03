package com.naufalazryan.alumnimipaulm.sosmed;

import static com.naufalazryan.alumnimipaulm.Config.IMAGE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.naufalazryan.alumnimipaulm.LoginActivity;
import com.naufalazryan.alumnimipaulm.MainActivity;
import com.naufalazryan.alumnimipaulm.R;
import com.naufalazryan.alumnimipaulm.SessionManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class SosialMediaActivity extends AppCompatActivity {

    ImageView btnBack, btnUpdateSosmed;
    CircleImageView profil;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosial_media);

        sessionManager = new SessionManager(SosialMediaActivity.this);

        btnUpdateSosmed = findViewById(R.id.imgUpdateSosialmedia);
        btnBack = findViewById(R.id.btnBackSosmed);

        profil = findViewById(R.id.bioGambar);


        btnUpdateSosmed.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), UpdateSosialMediaActivity.class)));
        btnBack.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));

        Picasso.get().load(IMAGE_URL + sessionManager.getFoto()).into(profil);

    }

    private void moveToLogin() {
        Intent intent = new Intent(SosialMediaActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}