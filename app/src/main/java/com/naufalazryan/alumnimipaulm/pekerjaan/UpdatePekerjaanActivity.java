package com.naufalazryan.alumnimipaulm.pekerjaan;

import static com.naufalazryan.alumnimipaulm.Config.IMAGE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.naufalazryan.alumnimipaulm.R;
import com.naufalazryan.alumnimipaulm.SessionManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdatePekerjaanActivity extends AppCompatActivity {

    ImageView imgBack;
    CircleImageView bioGambar;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pekerjaan);

        sessionManager = new SessionManager(UpdatePekerjaanActivity.this);

        bioGambar = findViewById(R.id.bioGambar);

        Picasso.get().load(IMAGE_URL + sessionManager.getFoto()).into(bioGambar);

        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), PekerjaanActivity.class)));

    }
}