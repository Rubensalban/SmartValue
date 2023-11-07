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
import java.util.HashMap;
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

        // Création d'une liste de operateur telephonique par pays
        List<String> selectList = new ArrayList<>();
        selectList.add("Selectionner un opératteur");

        List<String> burkinaList = new ArrayList<>();
        burkinaList.add("ORANGE");

        List<String> beninList = new ArrayList<>();
        beninList.add("MOOV");

        List<String> civList = new ArrayList<>();
        civList.add("ORANGE");
        civList.add("MOOV");
        civList.add("MTN");

        List<String> senegalList = new ArrayList<>();
        senegalList.add("FREE");
        senegalList.add("ORANGE");

        List<String> ghanalList = new ArrayList<>();
        ghanalList.add("AIRTEL");
        ghanalList.add("TIGO");
        ghanalList.add("MTN");
        ghanalList.add("VODAFONE");

        List<String> guinnelList = new ArrayList<>();
        guinnelList.add("AIRTEL");
        guinnelList.add("TIGO");
        guinnelList.add("MTN");
        guinnelList.add("VODAFONE");

        List<String> maliList = new ArrayList<>();
        maliList.add("ORANGE");

        List<String> togoList = new ArrayList<>();
        togoList.add("MOOV");
        togoList.add("TOGOCELL");

        List<String> nigerList = new ArrayList<>();
        nigerList.add("MOOV");
        nigerList.add("TOGOCELL");

        // Vos listes de opérateurs téléphoniques par pays
        HashMap<String, List<String>> operatorsByCountry = new HashMap<>();
        operatorsByCountry.put("Sélectionner le pays de destination", selectList);
        operatorsByCountry.put("BURKINA FASO", burkinaList);
        operatorsByCountry.put("BENIN", beninList);
        operatorsByCountry.put("CODE D'IVOIRE", civList);
        operatorsByCountry.put("SENEGAL", senegalList);
        operatorsByCountry.put("GHANA", ghanalList);
        operatorsByCountry.put("GUINEE", guinnelList);
        operatorsByCountry.put("MALI", maliList);
        operatorsByCountry.put("TOGO", togoList);
        operatorsByCountry.put("NIGER", nigerList);

// Récupération de la liste des pays depuis la classe Locale
        List<String> countryList = new ArrayList<>();
        countryList.add("Veuillez sélectionner le pays");
        countryList.add("BURKINA FASO");
        countryList.add("BENIN");
        countryList.add("CODE D'IVOIRE");
        countryList.add("SENEGAL");
        countryList.add("GHANA");
        countryList.add("GUINEE");
        countryList.add("MALI");
        countryList.add("TOGO");
        countryList.add("NIGER");

// Création d'un ArrayAdapter pour le Spinner des pays
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryList);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Application de l'adapter au Spinner des pays
        countrySpinner.setAdapter(countryAdapter);

// Gestion de la sélection du Spinner des pays (événement)
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry = (String) parent.getItemAtPosition(position);

                // Vérifier si le pays sélectionné a une liste d'opérateurs associée
                if (operatorsByCountry.containsKey(selectedCountry)) {
                    List<String> operators = operatorsByCountry.get(selectedCountry);

                    // Création d'un ArrayAdapter pour le Spinner des opérateurs
                    ArrayAdapter<String> operatorsAdapter = new ArrayAdapter<>(TransfertActivity.this, android.R.layout.simple_spinner_item, operators);
                    operatorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Application de l'adapter au Spinner des opérateurs
                    paymentSpinner.setAdapter(operatorsAdapter);
                } else {
                    // Si le pays sélectionné n'a pas d'opérateurs, réinitialisez le Spinner de paiement
                    List<String> emptyList = new ArrayList<>();
                    ArrayAdapter<String> emptyAdapter = new ArrayAdapter<>(TransfertActivity.this, android.R.layout.simple_spinner_item, emptyList);
                    emptyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    paymentSpinner.setAdapter(emptyAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Gestion si rien n'est sélectionné
            }
        });

    }
}