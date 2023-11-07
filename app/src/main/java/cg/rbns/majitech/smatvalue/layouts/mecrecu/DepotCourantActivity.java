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
    ActivityDepotCourantBinding binding;
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
    }

    private void eventListner() {

    }

}