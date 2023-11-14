package cg.rbns.majitech.smatvalue.layouts.mecrecu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cg.rbns.majitech.smatvalue.R;
import cg.rbns.majitech.smatvalue.databinding.ActivityDepotEpargneBinding;

public class DepotEpargneActivity extends AppCompatActivity {

    private ActivityDepotEpargneBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDepotEpargneBinding.inflate(getLayoutInflater());
        eventListner();
        setContentView(binding.getRoot());
    }

    private void eventListner() {
        binding.depotEpargneBack.setOnClickListener(v -> onBackPressed());
        binding.depotEpargneCancel.setOnClickListener(v -> binding.depotEpargneAmount.setText(""));
    }
}