package cg.rbns.majitech.smatvalue.layouts.directcash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cg.rbns.majitech.smatvalue.MainActivity;
import cg.rbns.majitech.smatvalue.R;
import cg.rbns.majitech.smatvalue.databinding.ActivityRechargeTwoBinding;

public class RechargeTwoActivity extends AppCompatActivity {

    private ActivityRechargeTwoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRechargeTwoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // View Binding replaces findViewById
        ImageView btnBack = binding.recharge2Back;
        EditText codeRecharge = binding.carteRecharge2;
        EditText telBeneficiaire = binding.telRecharge2;
        Button btnSend = binding.btnRecharge2Validate;
        Button btnCancel = binding.btnRecharge2Cancel;

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!codeRecharge.getText().toString().isEmpty() && !telBeneficiaire.getText().toString().isEmpty()) {
                    String phone = telBeneficiaire.getText().toString().substring(0, 2);
                    if (telBeneficiaire.getText().toString().length() > 7) {
                        if (phone.equals("05") || phone.equals("04")) {
                            if (codeRecharge.getText().toString().length() == 9) {
                                dialogConfirmation();
                            } else {
                                Toast.makeText(RechargeTwoActivity.this, getString(R.string.code_incomplet), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            telBeneficiaire.setFocusable(true);
                            Toast.makeText(RechargeTwoActivity.this, getString(R.string.invalide_airtel), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RechargeTwoActivity.this, getString(R.string.invalide_number), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RechargeTwoActivity.this, getString(R.string.all_need), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeRecharge.setText("");
                telBeneficiaire.setText("");
            }
        });

        btnBack.setOnClickListener(v -> onBackPressed());
    }

    private void sendSMS() {
        try {
            String message = "am*1*" + binding.carteRecharge2.getText().toString() + "*" + binding.telRecharge2.getText().toString();
            Uri telNumber;
            telNumber = Uri.parse("smsto:" + getString(R.string.srv_carte_airtel));
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, telNumber);
            smsIntent.putExtra("sms_body", message);
            startActivity(smsIntent);
            binding.carteRecharge2.setText("");
            binding.telRecharge2.setText("");
        } catch (Exception e) {
            Toast.makeText(RechargeTwoActivity.this, getString(R.string.failed_sms_send), Toast.LENGTH_SHORT).show();
        }
    }

    private void dialogConfirmation() {
        new AlertDialog.Builder(this)
                .setMessage("Code de recharge: " + binding.carteRecharge2.getText().toString() + "\nBénéficiaire: " + binding.telRecharge2.getText().toString() + "\n" + getString(R.string.validate))
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
        Intent i = new Intent(RechargeTwoActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}