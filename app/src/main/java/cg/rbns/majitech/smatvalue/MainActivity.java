package cg.rbns.majitech.smatvalue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.onesignal.Continue;
import com.onesignal.OneSignal;
import com.onesignal.debug.LogLevel;

import cg.rbns.majitech.smatvalue.fragments.HomeFragment;
import cg.rbns.majitech.smatvalue.fragments.ServiceClientFragment;
import cg.rbns.majitech.smatvalue.layouts.TermActivity;
import cg.rbns.majitech.smatvalue.utilities.PreferenceManager;

public class MainActivity extends AppCompatActivity {
    private ImageButton btn_airtel, btn_mtn, btn_send_to;
    private boolean singleBack = false;
    private EditText desti;
    private TelephonyManager telephonyManager;
    private String TAG;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private SwitchCompat switchNetwork;
    String my_operator;

    private static final String ONESIGNAL_APP_ID = "a8b1f782-1d9b-4d78-a46f-b2880edac66f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // OneSignal Initialization
        OneSignal.getDebug().setLogLevel(LogLevel.VERBOSE);
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID);
        OneSignal.getNotifications().requestPermission(true, Continue.with(r -> {
            if (r.isSuccess()) {
                if (r.getData()) {
                    // `requestPermission` completed successfully and the user has accepted permission
                }
                else {
                    // `requestPermission` completed successfully but the user has rejected permission
                }
            }
            else {
                // `requestPermission` completed unsuccessfully, check `r.getThrowable()` for more info on the failure reason
            }
        }));


        // Récupérer la valeur du Switch enregistrée dans les préférences
        PreferenceManager preferenceManager = new PreferenceManager(this);
        boolean savedSwitchValue = preferenceManager.getBoolean("switch_state", false);

        //Init
        desti = findViewById(R.id.tel_destinataire);
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        bottomNavigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.inflateMenu(R.menu.toolbar);
        SwitchCompat switchNetwork = toolbar.findViewById(R.id.switchNetwork);
        switchNetwork.setChecked(savedSwitchValue);
        switchNetwork.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferenceManager.putBoolean("switch_state", isChecked);
            }
        });

        //Operator Init
        my_operator = telephonyManager.getSimOperatorName();

        // Load Default Fragment
        loadFragment(new HomeFragment());

        // Toolbar
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.navigation_share_link){
                    playstore_share();
                    return true;
                }
                return true;
            }
        });

        // Bottom Navigation Menu
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.navigation_home) {
                    loadFragment(new HomeFragment());
                    return true;
                } else if (id == R.id.navigation_client){
                    loadFragment(new ServiceClientFragment());
                    return true;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.exit_verify))
                .setMessage(getString(R.string.exit_msg))
                .setPositiveButton(getString(R.string.exit_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton(getString(R.string.exit_no), null)
                .show();
    }

    private void playstore_share() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            String shareMessage= "Smart Value : Transferts Mobile inter-réseaux !\n";
            shareMessage = shareMessage + "\nhttps://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID;
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "Partagez via"));
        } catch(Exception e) {
            e.toString();
        }
    }

    private void sms_share() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText edt = (EditText) dialogView.findViewById(R.id.tel_destinataire);
        dialogBuilder.setTitle("Partagez à un amis");
        dialogBuilder.setPositiveButton(getString(R.string.valider), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String dest = edt.getText().toString();
                if (!dest.isEmpty()){
                    String firstFourChars = "";
                    if (dest.length() > 2) {
                        firstFourChars = dest.substring(0, 2);
                    } else {
                        firstFourChars = dest;
                    }
                    if (firstFourChars.equals("06")){
                        sendto(dest, my_operator);
                    }
                    if (firstFourChars.equals("05")){
                        sendto(dest, my_operator);
                    }
                    if (firstFourChars.equals("04")){
                        sendto(dest, my_operator);
                    }
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.svp_number), Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialogBuilder.setNegativeButton(getString(R.string.annuler), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }

    private void savePrefsData(boolean isCheck) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myNetwork",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isNetwork",isCheck);
        editor.commit();
    }

    private void sendto(String dest, String my_operator) {
        if (dest.length() < 9) {
            Toast.makeText(MainActivity.this, getString(R.string.msg_error), Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder builders = new AlertDialog.Builder(MainActivity.this);
            builders.setTitle(getString(R.string.choice_network));
            String[] data = { getString(R.string.net_airtel), getString(R.string.net_mtn)};
            builders.setItems(data, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    switch (i){
                        case 0:
                            String result =  "info*" + dest;
                            Uri uriSms =  Uri.parse("smsto:" + getString(R.string.srv_airtel));
                            Intent sms_intent = new Intent(Intent.ACTION_SENDTO, uriSms);
                            sms_intent.putExtra("sms_body", result);
                            startActivity(sms_intent);
                            break;
                        case 1:
                            String results =  "info*" + dest;
                            Uri uriSmss =  Uri.parse("smsto:" + getString(R.string.srv_mtn));
                            Intent sms_intents = new Intent(Intent.ACTION_SENDTO, uriSmss);
                            sms_intents.putExtra("sms_body", results);
                            startActivity(sms_intents);
                            break;
                    }
                }
            });
        }
    }
}

