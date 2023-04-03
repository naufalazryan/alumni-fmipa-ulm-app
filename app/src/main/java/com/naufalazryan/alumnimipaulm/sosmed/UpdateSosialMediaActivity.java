package com.naufalazryan.alumnimipaulm.sosmed;

import static com.naufalazryan.alumnimipaulm.Config.IMAGE_URL;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.naufalazryan.alumnimipaulm.R;
import com.naufalazryan.alumnimipaulm.SessionManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateSosialMediaActivity extends AppCompatActivity {

    SessionManager sessionManager;
    ImageView btnBack;
    CircleImageView profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sosial_media);

        sessionManager = new SessionManager(UpdateSosialMediaActivity.this);

        btnBack = findViewById(R.id.btnBack);
        profil = findViewById(R.id.bioGambar);

        btnBack.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), SosialMediaActivity.class)));

        Picasso.get().load(IMAGE_URL + sessionManager.getFoto()).into(profil);
    }

}