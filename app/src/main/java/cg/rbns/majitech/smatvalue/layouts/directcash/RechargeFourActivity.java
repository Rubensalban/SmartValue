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
import cg.rbns.majitech.smatvalue.databinding.ActivityRechargeFourBinding;

public class RechargeFourActivity extends AppCompatActivity {

    private ActivityRechargeFourBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRechargeFourBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // View Binding replaces findViewById
        ImageView btnBack = binding.recharge4Back;
        EditText codeRecharge = binding.carteRecharge4;
        EditText telBeneficiaire = binding.telDestinataire06;
        EditText telPrix = binding.telCode06;
        Button btnSend = binding.btnRecharge4Validate;
        Button btnCancel = binding.btnRecharge4Cancel;

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!codeRecharge.getText().toString().isEmpty() && !telBeneficiaire.getText().toString().isEmpty()) {
                    if (telBeneficiaire.getText().toString().length() > 7) {
                        String phone = telBeneficiaire.getText().toString().substring(0, 2);

                        if (phone.equals("06")) {
                            if (codeRecharge.getText().toString().length() == 9) {
                                dialogConfirmation();
                            } else {
                                Toast.makeText(RechargeFourActivity.this, getString(R.string.code_incomplet), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RechargeFourActivity.this, getString(R.string.mtn_need), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RechargeFourActivity.this, getString(R.string.invalide_number), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RechargeFourActivity.this, getString(R.string.all_need), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeRecharge.setText("");
                telBeneficiaire.setText("");
                telPrix.setText("");
            }
        });

        btnBack.setOnClickListener(v -> onBackPressed());
    }

    private void sendSMS() {
        try {
            String code = binding.carteRecharge4.getText().toString();
            String phone = binding.telDestinataire06.getText().toString();
            String prix = binding.telCode06.getText().toString();
            String message = "am*3*" + code + "*" + phone + "*" + prix;
            Uri telNumber;
            telNumber = Uri.parse("smsto:" + getString(R.string.srv_carte_airtel));
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, telNumber);
            smsIntent.putExtra("sms_body", message);
            startActivity(smsIntent);
            binding.carteRecharge4.setText("");
            binding.telDestinataire06.setText("");
        } catch (Exception e) {
            Toast.makeText(RechargeFourActivity.this, getString(R.string.failed_sms_send), Toast.LENGTH_SHORT).show();
        }
    }

    private void dialogConfirmation() {
        new AlertDialog.Builder(this)
                .setMessage("Code de recharge: " + binding.carteRecharge4.getText().toString() + "\nBénéficiaire: " + binding.telDestinataire06.getText().toString() + "\n" + getString(R.string.validate) + "\n\n" + getString(R.string.nb_4))
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
        Intent i = new Intent(RechargeFourActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}