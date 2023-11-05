package cg.rbns.majitech.smatvalue.layouts.reabonnement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import cg.rbns.majitech.smatvalue.R;
import cg.rbns.majitech.smatvalue.databinding.ActivityChangeCanalBinding;
import cg.rbns.majitech.smatvalue.databinding.ActivityUserInfos2Binding;

public class ChangeCanalActivity extends AppCompatActivity {

    ActivityChangeCanalBinding binding;
    private String selectedOption = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangeCanalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initSnipper();
        eventListener();

    }

    private void initSnipper() {
        List<String> option = new ArrayList<>();
        option.add("Veuillez selectionner une offre d'abonnement");
        option.add("ACCESS");
        option.add("EVASION");
        option.add("ACCESS +");
        option.add("EVASION +");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, option);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerCanalOption.setAdapter(adapter);
    }

    private void eventListener() {
        binding.btnChangeCanalBack.setOnClickListener(v -> onBackPressed());
        binding.btnRechargeValidate.setOnClickListener(v -> sendToserver());
        binding.btnChangeCanalCancel.setOnClickListener(v -> binding.numCarteCanalplus.setText(""));
        binding.spinnerCanalOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedOption = (String) adapterView.getItemAtPosition(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void sendToserver() {
        if (binding.numCarteCanalplus.getText().toString() == null || selectedOption == null) {
            showDialog(getString(R.string.set_all_info));
        } else {
            //
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void showDialog(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}