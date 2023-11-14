package cg.rbns.majitech.smatvalue.layouts.mecrecu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import cg.rbns.majitech.smatvalue.R;
import cg.rbns.majitech.smatvalue.databinding.ActivityRetraitCourantBinding;

public class RetraitCourantActivity extends AppCompatActivity {

    private ActivityRetraitCourantBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRetraitCourantBinding.inflate(getLayoutInflater());
        initListner();
        eventListner();
        setContentView(binding.getRoot());
    }

    private void initListner() {
        List<String> option = new ArrayList<>();
        option.add("Selectionner le réseau");
        option.add("MTN");
        option.add("AIRTEL");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, option);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.retraitCourantSpinner.setAdapter(adapter);
    }

    private void eventListner() {
        binding.retraitCourantBack.setOnClickListener(v -> onBackPressed());
        binding.retraitCourantCancel.setOnClickListener(v -> binding.retraitCourantAmount.setText(""));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}