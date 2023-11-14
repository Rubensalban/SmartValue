package cg.rbns.majitech.smatvalue.layouts.mecrecu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import cg.rbns.majitech.smatvalue.R;
import cg.rbns.majitech.smatvalue.databinding.ActivityRetraitTontineBinding;

public class RetraitTontineActivity extends AppCompatActivity {

    private ActivityRetraitTontineBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRetraitTontineBinding.inflate(getLayoutInflater());
        initListener();
        eventListener();
        setContentView(binding.getRoot());
    }

    private void initListener() {
        List<String> option = new ArrayList<>();
        option.add("Selectionner le réseau");
        option.add("MTN");
        option.add("AIRTEL");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, option);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.retraitTontineSpinner.setAdapter(adapter);
    }

    private void eventListener() {
        binding.retraitTontineBack.setOnClickListener(v -> onBackPressed());
        binding.retraitTontineCancel.setOnClickListener(v -> binding.retraitTontineAmount.setText(""));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}