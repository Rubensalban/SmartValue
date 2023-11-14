package cg.rbns.majitech.smatvalue.layouts.reabonnement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import cg.rbns.majitech.smatvalue.R;
import cg.rbns.majitech.smatvalue.databinding.ActivityPayCanalBinding;

public class PayCanalActivity extends AppCompatActivity {

    private ActivityPayCanalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPayCanalBinding.inflate(getLayoutInflater());
        eventListener();
        setContentView(binding.getRoot());
    }

    private void eventListener() {
        binding.recharge1Back.setOnClickListener(v -> onBackPressed());
    }
}