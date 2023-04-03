package com.naufalazryan.alumnimipaulm.pendidikan;

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

public class PendidikanActivity extends AppCompatActivity {

    ImageView imgBack, imgUpdate;
    CircleImageView profil;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendidikan);

        sessionManager = new SessionManager(PendidikanActivity.this);

        imgUpdate = findViewById(R.id.imgUpdate);
        imgBack = findViewById(R.id.imgBack);
        profil = findViewById(R.id.bioGambar);

        imgUpdate.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), UpdatePendidikanActivity.class)));
        imgBack.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));

        Picasso.get().load(IMAGE_URL + sessionManager.getFoto()).into(profil);


    }
}