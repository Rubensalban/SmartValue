package cg.rbns.majitech.smatvalue.layouts.mecrecu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import cg.rbns.majitech.smatvalue.R;
import cg.rbns.majitech.smatvalue.databinding.ActivityDepotBloqueBinding;

public class DepotBloqueActivity extends AppCompatActivity {

    private ActivityDepotBloqueBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDepotBloqueBinding.inflate(getLayoutInflater());
        eventListner();
        setContentView(binding.getRoot());
    }

    private void eventListner() {
        binding.depotBlocBack.setOnClickListener(v -> onBackPressed());
        binding.depotBlocCancel.setOnClickListener(v-> binding.depotBlocAmount.setText(""));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}