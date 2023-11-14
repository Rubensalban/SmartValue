package cg.rbns.majitech.smatvalue.layouts.reabonnement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cg.rbns.majitech.smatvalue.R;
import cg.rbns.majitech.smatvalue.databinding.ActivityPayStartimeBinding;

public class PayStartimeActivity extends AppCompatActivity {

    private ActivityPayStartimeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPayStartimeBinding.inflate(getLayoutInflater());
        eventListener();
        setContentView(binding.getRoot());
    }

    private void eventListener() {
        binding.payStartimeBack.setOnClickListener(v -> onBackPressed());
    }
}