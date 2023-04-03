package com.naufalazryan.alumnimipaulm.pekerjaan;

import static com.naufalazryan.alumnimipaulm.Config.IMAGE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.naufalazryan.alumnimipaulm.MainActivity;
import com.naufalazryan.alumnimipaulm.R;
import com.naufalazryan.alumnimipaulm.SessionManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class PekerjaanActivity extends AppCompatActivity {

    ImageView imgBack, imgUpdate;
    CircleImageView profil;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pekerjaan);

        sessionManager = new SessionManager(PekerjaanActivity.this);

        imgBack = findViewById(R.id.imgBack);
        imgUpdate = findViewById(R.id.imgUpdate);
        profil = findViewById(R.id.bioGambar);

        imgBack.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
        imgUpdate.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), UpdatePekerjaanActivity.class)));

        Picasso.get().load(IMAGE_URL + sessionManager.getFoto()).into(profil);

    }
}