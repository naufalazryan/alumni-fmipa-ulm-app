package com.naufalazryan.alumnimipaulm.biodata;


import static com.naufalazryan.alumnimipaulm.Config.IMAGE_URL;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.naufalazryan.alumnimipaulm.MainActivity;
import com.naufalazryan.alumnimipaulm.R;
import com.naufalazryan.alumnimipaulm.SessionManager;
import com.naufalazryan.alumnimipaulm.api.APIService;
import com.naufalazryan.alumnimipaulm.api.RetrofitClient;
import com.naufalazryan.alumnimipaulm.ModelResponse.AlumniModelResponse.AlumniResponse;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateBiodataActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int PICK_IMAGE = 1;
    SessionManager sessionManager;
    TextView tvMhsNim;
    EditText edMhsNama, edMhsAlamat, edMhsHp, edMhsEmail;
    Button btnUpdate;
    String upNim, upNama, upAlamat, upTelp, upEmail, upJK;
    RadioButton laki, perempuan, selected;
    RadioGroup rgJK;
    ProgressDialog pd;
    APIService apiService = RetrofitClient.getClient().create(APIService.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_biodata);
        view();

    }

    @Override
    protected void onResume() {
        super.onResume();
        view();
    }

    private void view() {

        findViewById(R.id.arrow_back).setOnClickListener(this);
        TextView activity = findViewById(R.id.activity);
        activity.setText("Update Biodata");


        sessionManager = new SessionManager(UpdateBiodataActivity.this);

        btnUpdate = findViewById(R.id.btnUpdate);
        tvMhsNim = findViewById(R.id.nim);
        edMhsNama = findViewById(R.id.nama);
        edMhsAlamat = findViewById(R.id.alamat);
        edMhsHp = findViewById(R.id.telepon);
        edMhsEmail = findViewById(R.id.email);
        laki = findViewById(R.id.rbLaki);
        perempuan = findViewById(R.id.rbPerempuan);
        rgJK = findViewById(R.id.rgJenisKelamin);


        upNim = sessionManager.getUserDetail().get(SessionManager.ALU_NIM);
        upNama = sessionManager.getUserDetail().get(SessionManager.ALU_NAMA);
        upAlamat = sessionManager.getUserDetail().get(SessionManager.ALU_ALAMAT);
        upTelp = sessionManager.getUserDetail().get(SessionManager.ALU_HP);
        upEmail = sessionManager.getUserDetail().get(SessionManager.ALU_EMAIL);
        upJK = sessionManager.getUserDetail().get(SessionManager.ALU_JK);

        tvMhsNim.setText(upNim);
        edMhsNama.setText(upNama);
        edMhsAlamat.setText(upAlamat);
        edMhsHp.setText(upTelp);
        edMhsEmail.setText(upEmail);

        tvMhsNim.setEnabled(false);

        //proses JK
        if (!(upJK == null)) {
            int index = (upJK.equalsIgnoreCase("P") ? 1 : 0);
            rgJK.check(rgJK.getChildAt(index).getId());
        }


        pd = new ProgressDialog(this);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("Update...");
                pd.setCancelable(false);
                pd.show();

                String selectedRbText = "L";

                int selectedRadioButtonId = rgJK.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    selected = findViewById(selectedRadioButtonId);
                    selectedRbText = selected.getText().toString();
                    selectedRbText = (selectedRbText.equalsIgnoreCase("Perempuan") ? "P" : "L");

                }
                Log.d("Retro", selectedRbText);
                Call<AlumniResponse> update = apiService.updateBiodata(
                        upNim.toString(),
                        edMhsNama.getText().toString(),
                        edMhsAlamat.getText().toString(),
                        selectedRbText,
                        edMhsHp.getText().toString(),
                        edMhsEmail.getText().toString());

                update.enqueue(new Callback<AlumniResponse>() {
                    @Override
                    public void onResponse(Call<AlumniResponse> call, Response<AlumniResponse> response) {
//                         Log.d("Retro", "Response "+response.body().getData().getAluJK());
                        sessionManager.setAluNama(response.body().getData().getAluNama());
                        sessionManager.setAluJk(response.body().getData().getAluJK());
                        sessionManager.setAluAlamat(response.body().getData().getAluAlamat());
                        sessionManager.setAluHp(response.body().getData().getAluHp());
                        sessionManager.setAluEmail(response.body().getData().getAluEmail());
                        Toast.makeText(UpdateBiodataActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateBiodataActivity.this, BiodataActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<AlumniResponse> call, Throwable t) {
                        pd.hide();
                        Log.d("Retro", "On Failure");
                    }
                });
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arrow_back:
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateBiodataActivity.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setIcon(R.drawable.app);
                builder.setMessage("Yakin ingin kembali");
                builder.setPositiveButton("Ya", ((dialogInterface, i) -> {
                    startActivity(new Intent(this, BiodataActivity.class));
                    overridePendingTransition(R.anim.slide_from_bottom, android.R.anim.accelerate_decelerate_interpolator);
                }
                ));
                builder.setNegativeButton("Tidak", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });
                builder.show();
        }
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateBiodataActivity.this);
        builder.setTitle(getString(R.string.app_name));
        builder.setIcon(R.drawable.app);
        builder.setMessage("Yakin ingin kembali");
        builder.setPositiveButton("Ya", ((dialogInterface, i) -> {
            startActivity(new Intent(this, BiodataActivity.class));
            overridePendingTransition(R.anim.slide_from_bottom, android.R.anim.accelerate_decelerate_interpolator);
        }
        ));
        builder.setNegativeButton("Tidak", (dialogInterface, i) -> {
            dialogInterface.dismiss();
        });
        builder.show();
    }
}
