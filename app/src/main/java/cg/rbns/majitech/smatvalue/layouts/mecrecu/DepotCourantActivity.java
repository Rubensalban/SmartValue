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
import cg.rbns.majitech.smatvalue.databinding.ActivityDepotCourantBinding;

public class DepotCourantActivity extends AppCompatActivity {
    private ActivityDepotCourantBinding binding;
    String selectedOption = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDepotCourantBinding.inflate(getLayoutInflater());
        eventListner();
        spinnerInit();
        setContentView(binding.getRoot());
    }
    private void spinnerInit() {
        List<String> option = new ArrayList<>();
        option.add("Selectionner le compte");
        option.add("Compte personnel");
        option.add("Autre compte");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, option);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerMecrecuAccount.setAdapter(adapter);
    }

    private void eventListner() {
        binding.spinnerMecrecuAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedOption = (String) adapterView.getItemAtPosition(i);
                if (selectedOption == "Autre compte"){
                    binding.mecrecuView.setVisibility(View.VISIBLE);
                }else  {
                    binding.mecrecuView.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        binding.mecrecuBack.setOnClickListener(v -> onBackPressed());
        binding.btnMecrecuCancel.setOnClickListener(v -> clearContent());
    }

    private void clearContent() {
        binding.mecrecuAmount.setText("");
        binding.mecrecuAmountSend.setText("");
        binding.mecrecuNumOtherAccount.setText("");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}