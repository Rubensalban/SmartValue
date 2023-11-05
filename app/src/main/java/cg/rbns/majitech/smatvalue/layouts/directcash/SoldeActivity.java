package cg.rbns.majitech.smatvalue.layouts.directcash;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import cg.rbns.majitech.smatvalue.MainActivity;
import cg.rbns.majitech.smatvalue.databinding.ActivitySoldeBinding;

public class SoldeActivity extends AppCompatActivity {
    private ActivitySoldeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySoldeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Back to preview
        binding.soldeBack.setOnClickListener(v -> onBackPressed());

        // Airtel
        binding.soldeAirtel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSoldeAirtel();
            }
        });

        // Mtn
        binding.soldeMtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSoldeMtn();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backToPreview();
        this.finish();
    }

    private void backToPreview() {
        Intent i = new Intent(SoldeActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    private void getSoldeAirtel() {
        if (ActivityCompat.checkSelfPermission(SoldeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SoldeActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            String ussdCode = "*128*7*2#";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(ussdToCallUri(ussdCode));
            try {
                startActivity(intent);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    private void getSoldeMtn() {
        if (ActivityCompat.checkSelfPermission(SoldeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SoldeActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            String ussdCode = "*105#";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(ussdToCallUri(ussdCode));
            try {
                startActivity(intent);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    private Uri ussdToCallUri(String ussdCode) {
        StringBuilder uriString = new StringBuilder();
        if (!ussdCode.startsWith("tel:"))
            uriString.append("tel:");
        for (char c : ussdCode.toCharArray()) {
            if (c == '#')
                uriString.append(Uri.encode("#"));
            else
                uriString.append(c);
        }
        return Uri.parse(uriString.toString());
    }
}