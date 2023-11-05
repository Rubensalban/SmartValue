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
import cg.rbns.majitech.smatvalue.databinding.ActivityChangeStartimeBinding;

public class ChangeStartimeActivity extends AppCompatActivity {

    ActivityChangeStartimeBinding binding;
    private String selectedOption = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangeStartimeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initSnipper();
        eventListener();
    }

    private void initSnipper() {
        List<String> option = new ArrayList<>();
        option.add("Veuillez selectionner une offre d'abonnement");
        option.add("DTH MAX 1 JOUR");
        option.add("DTH MAX 30 JOUR");
        option.add("DTH SUPER 1 JOUR");
        option.add("DTH SUPER 7 JOUR");
        option.add("DTH SUPER 30 JOUR");
        option.add("DTH SMART 1 JOUR");
        option.add("DTH SMART 7 JOUR");
        option.add("DTH SMART 30 JOUR");
        option.add("DTH CHINESE 30 JOUR");
        option.add("DTH ENGLISH 30 JOUR");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, option);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerStarTimeOption.setAdapter(adapter);
    }

    private void eventListener() {
        binding.startTimeChangeBack.setOnClickListener(v -> onBackPressed());
        binding.btnChangeStartimeValidate.setOnClickListener(v -> sendToserver());
        binding.btnChangeStartimeCancel.setOnClickListener(v -> binding.numCarteStartime.setText(""));
        binding.spinnerStarTimeOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        if (binding.numCarteStartime.getText().toString() == null || selectedOption == null) {
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