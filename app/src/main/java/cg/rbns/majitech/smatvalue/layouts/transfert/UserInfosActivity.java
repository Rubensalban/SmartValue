package cg.rbns.majitech.smatvalue.layouts.transfert;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import cg.rbns.majitech.smatvalue.R;
import cg.rbns.majitech.smatvalue.databinding.ActivityUserInfosBinding;

public class UserInfosActivity extends AppCompatActivity {

    private ActivityUserInfosBinding binding;
    private  String selectedDate = null;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserInfosBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setSupportActionBar(binding.toolbarUserInfo.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.infoBirthDateButton.setOnClickListener(v -> showDatePickerDialog());
        binding.btnInfoValidate.setOnClickListener(v -> nextscreen());

    }

    private void nextscreen() {
        String firstName = binding.infoFirstName.getText().toString();
        String lastName = binding.infoLastName.getText().toString();
        String phone1 = binding.infoPhone1.getText().toString();
        String phone2 = binding.infoPhone2.getText().toString();
        String address = binding.infoAddress.getText().toString();
        String city = binding.infoCity.getText().toString();

        if (firstName.isEmpty() || lastName.isEmpty() || phone1.isEmpty() || phone2.isEmpty() || address.isEmpty() || city.isEmpty() || selectedDate == null) {
            showDialog("Veuillez remplir tous les champs.");
        } else {
            startActivity(new Intent(this, UserInfos2Activity.class));
        }
    }

    private void showDialog(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                    binding.infoBirthDateButton.setText(selectedDate);
                }, year, month, day);

        datePickerDialog.show();
    }
}