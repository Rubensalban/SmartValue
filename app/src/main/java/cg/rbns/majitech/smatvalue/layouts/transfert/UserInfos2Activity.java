package cg.rbns.majitech.smatvalue.layouts.transfert;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import cg.rbns.majitech.smatvalue.databinding.ActivityUserInfos2Binding;

public class UserInfos2Activity extends AppCompatActivity {
    private ActivityUserInfos2Binding binding;
    private RadioButton selectedRadioButton;
    File imageFile;
    private static final int REQUEST_PERMISSION_CAMERA = 1;
    String selectedPiece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserInfos2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setSupportActionBar(binding.toolbarUserInfo2.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.radioGroupPiece.setOnCheckedChangeListener((group, checkedId) -> {
            selectedRadioButton = findViewById(checkedId);
            if (selectedRadioButton != null) {
                selectedPiece = selectedRadioButton.getText().toString();
            }
        });
        binding.btnInfo2Scan.setOnClickListener(v -> scanner());
        binding.infoCni.setChecked(true);
        binding.btnInfo2Validate.setOnClickListener(v -> sendToServer());
    }

    private void sendToServer() {
        if (binding.infoNumPiece.getText().toString().isEmpty()) {
            showDialog("Veuillez saisir le numéro de la pièce d'identité");
        } else {
            startActivity(new Intent(this, TransfertActivity.class));
            savePrefsData();
        }
    }

    private void showDialog(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isUserInfo",true);
        editor.commit();
    }

    private void scanner() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CAMERA);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            activityResultLauncher.launch(intent);
        }
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    imageFile = bitmapToFile(bitmap);
                }
            }
    );

    private File bitmapToFile(Bitmap bitmap) {
        File filesDir = getApplicationContext().getFilesDir();
        File imageFile = new File(filesDir, "tempImage.png");
        try {
            OutputStream os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            os.flush();
            os.close();
            return imageFile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}