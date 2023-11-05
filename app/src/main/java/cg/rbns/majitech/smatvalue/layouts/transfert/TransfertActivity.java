package cg.rbns.majitech.smatvalue.layouts.transfert;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import cg.rbns.majitech.smatvalue.R;

public class TransfertActivity extends AppCompatActivity {

    String selectedPaymentMethod;
    String selectedCountry;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfert);

        // Référence au Spinner dans le layout XML
        Spinner countrySpinner = findViewById(R.id.countrySpinner);
        Spinner paymentSpinner = findViewById(R.id.paymentSpinner);
        ImageView btnback = findViewById(R.id.back_transfer);

        btnback.setOnClickListener(v -> onBackPressed());

        // Création d'une liste de moyens de paiement
        List<String> paymentMethods = new ArrayList<>();
        paymentMethods.add("Veuillez selectionner le mode de payment");
        paymentMethods.add("Mobile money");
        paymentMethods.add("Cash");
        paymentMethods.add("CB");

        // Récupération de la liste des pays depuis la classe Locale
        String[] locales = Locale.getISOCountries();
        List<String> countryList = new ArrayList<>();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            countryList.add(obj.getDisplayCountry());
        }

        // Création d'un ArrayAdapter pour le Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentMethods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Application de l'adapter au Spinner
        paymentSpinner.setAdapter(adapter);

        // Gestion de la sélection du Spinner (événement)
        paymentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPaymentMethod = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Gestion si rien n'est sélectionné
            }
        });

        // Tri des pays par ordre alphabétique
        countryList.sort(String::compareTo);

        // Ajout de l'indication "Veuillez sélectionner le mode de paiement" au début de la liste
        countryList.add(0, "Veuillez sélectionner le pays");

        // Création d'un ArrayAdapter pour le Spinner
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Application de l'adapter au Spinner
        countrySpinner.setAdapter(adapter2);

        // Gestion de la sélection du Spinner (événement)
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Gestion si rien n'est sélectionné
            }
        });

    }
}