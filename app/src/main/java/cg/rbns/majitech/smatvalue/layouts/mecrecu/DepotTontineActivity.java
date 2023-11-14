package cg.rbns.majitech.smatvalue.layouts.mecrecu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cg.rbns.majitech.smatvalue.R;
import cg.rbns.majitech.smatvalue.databinding.ActivityDepotTontineBinding;

public class DepotTontineActivity extends AppCompatActivity {

    private ActivityDepotTontineBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDepotTontineBinding.inflate(getLayoutInflater());
        eventListner();
        setContentView(R.layout.activity_depot_tontine);
    }

    private void eventListner() {
        binding.depotTontineBack.setOnClickListener(v -> onBackPressed());
        binding.depotTontineCancel.setOnClickListener(v-> binding.depotTontineAmount.setText(""));
    }
}