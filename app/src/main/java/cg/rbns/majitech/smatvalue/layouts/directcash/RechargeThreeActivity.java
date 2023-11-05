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
import cg.rbns.majitech.smatvalue.databinding.ActivityRechargeThreeBinding;

public class RechargeThreeActivity extends AppCompatActivity {

    private ActivityRechargeThreeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRechargeThreeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // View Binding replaces findViewById
        ImageView btnBack = binding.recharge3Back;
        EditText codeRecharge = binding.codeRecharge3;
        EditText telBeneficiaire1 = binding.telDestinataire1;
        EditText numBeneficiaire1 = binding.numDestinataire1;
        EditText telBeneficiaire2 = binding.telDestinataire2;
        EditText numBeneficiaire2 = binding.numDestinataire2;
        Button btnSend = binding.btnRecharge3Validate;
        Button btnCancel = binding.btnRecharge3Cancel;

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!codeRecharge.getText().toString().isEmpty() && !telBeneficiaire1.getText().toString().isEmpty()
                        && !telBeneficiaire2.getText().toString().isEmpty() && !numBeneficiaire1.getText().toString().isEmpty()
                        && !numBeneficiaire2.getText().toString().isEmpty()) {
                    String phone1 = numBeneficiaire1.getText().toString().substring(0, 2);
                    String phone2 = numBeneficiaire2.getText().toString().substring(0, 2);
                    if (numBeneficiaire1.getText().toString().length() > 7 && numBeneficiaire2.getText().toString().length() > 7) {
                        if (phone1.equals("05") || phone1.equals("04")) {
                            if (phone2.equals("04") || phone2.equals("05")) {
                                if (codeRecharge.getText().toString().length() == 9) {
                                    dialogConfirmation();
                                } else {
                                    Toast.makeText(RechargeThreeActivity.this, getString(R.string.code_incomplet), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(RechargeThreeActivity.this, getString(R.string.invalide_airtel), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RechargeThreeActivity.this, getString(R.string.invalide_airtel), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RechargeThreeActivity.this, getString(R.string.invalide_number), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RechargeThreeActivity.this, getString(R.string.all_need), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telBeneficiaire1.setText("");
                numBeneficiaire1.setText("");
                telBeneficiaire2.setText("");
                numBeneficiaire2.setText("");
                codeRecharge.setText("");
            }
        });

        btnBack.setOnClickListener(v -> onBackPressed());
    }

    private void sendSMS() {
        try {
            String code = binding.codeRecharge3.getText().toString();
            String phone1 = binding.telDestinataire1.getText().toString();
            String phone2 = binding.telDestinataire2.getText().toString();
            String benf1 = binding.numDestinataire1.getText().toString();
            String benf2 = binding.numDestinataire2.getText().toString();
            String message = "am*2*" + code + "*" + benf1 + "*" + phone1 + "*" + benf2 + "*" + phone2;

            Uri telNumber;
            telNumber = Uri.parse("smsto:" + getString(R.string.srv_carte_airtel));
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, telNumber);
            smsIntent.putExtra("sms_body", message);
            startActivity(smsIntent);

            binding.codeRecharge3.setText("");
            binding.telDestinataire1.setText("");
            binding.telDestinataire2.setText("");
        } catch (Exception e) {
            Toast.makeText(RechargeThreeActivity.this, getString(R.string.failed_sms_send), Toast.LENGTH_SHORT).show();
        }
    }

    private void dialogConfirmation() {
        new AlertDialog.Builder(this)
                .setMessage("Code de recharge: " + binding.codeRecharge3.getText().toString() + "\nPremier bénéficiaire: " + binding.numDestinataire1.getText().toString() + "\nDeuxième bénéficiaire: " + binding.numDestinataire2.getText().toString() + "\n" + getString(R.string.validate) + "\n\n" + getString(R.string.nb_3))
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
        Intent i = new Intent(RechargeThreeActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}