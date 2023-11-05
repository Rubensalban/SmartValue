package cg.rbns.majitech.smatvalue.layouts.directcash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cg.rbns.majitech.smatvalue.MainActivity;
import cg.rbns.majitech.smatvalue.R;
import cg.rbns.majitech.smatvalue.databinding.ActivityRechargeOneBinding;

public class RechargeOneActivity extends AppCompatActivity {

    private ActivityRechargeOneBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRechargeOneBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // View Binding replaces findViewById
        ImageView btnBack = binding.recharge1Back;
        EditText codeRecharge = binding.codeRecharge1;
        Button btnSend = binding.btnRecharge1Validate;
        Button btnCancel = binding.btnRecharge1Cancel;

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeRecharge.setText("");
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!codeRecharge.getText().toString().isEmpty()) {
                    if (codeRecharge.getText().toString().length() == 9) {
                        dialogConfirmation();
                    } else {
                        Toast.makeText(RechargeOneActivity.this, getString(R.string.code_incomplet), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RechargeOneActivity.this, getString(R.string.code_need), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(v -> onBackPressed());
    }

    // Le reste du code reste inchangé
    private void sendSMS() {
        try {
            String message = "am*1*" + binding.codeRecharge1.getText().toString();
            Uri telNumber;
            telNumber = Uri.parse("smsto:" + getString(R.string.srv_carte_airtel));
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, telNumber);
            smsIntent.putExtra("sms_body", message);
            startActivity(smsIntent);
        } catch (Exception ex) {
            Log.d("Send SMS", "sendSMS: " + ex);
            Toast.makeText(RechargeOneActivity.this, getString(R.string.failed_sms_send) + ": " + ex, Toast.LENGTH_SHORT).show();
        }
    }

    private void dialogConfirmation() {
        new AlertDialog.Builder(this)
                .setMessage("Code de recharge: " + binding.codeRecharge1.getText().toString() + "\n" + getString(R.string.validate_code))
                .setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        sendSMS();
                    }
                })
                .setNegativeButton("Modifier", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backToPreview();
        this.finish();
    }

    private void backToPreview() {
        Intent i = new Intent(RechargeOneActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}