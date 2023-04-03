package com.naufalazryan.alumnimipaulm.pendidikan;

import static com.naufalazryan.alumnimipaulm.Config.IMAGE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import com.naufalazryan.alumnimipaulm.R;
import com.naufalazryan.alumnimipaulm.SessionManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class UpdatePendidikanActivity extends AppCompatActivity {

    ImageView imgBack;
    CircleImageView profil;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pendidikan);
        profil = findViewById(R.id.bioGambar);

        sessionManager = new SessionManager(UpdatePendidikanActivity.this);
        imgBack = findViewById(R.id.imgBack);

        imgBack.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), PendidikanActivity.class)));

        Picasso.get().load(IMAGE_URL + sessionManager.getFoto()).into(profil);
    }
}