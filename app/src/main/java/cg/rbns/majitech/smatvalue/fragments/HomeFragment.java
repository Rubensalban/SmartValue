package cg.rbns.majitech.smatvalue.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import cg.rbns.majitech.smatvalue.R;
import cg.rbns.majitech.smatvalue.databinding.FragmentHomeBinding;
import cg.rbns.majitech.smatvalue.layouts.reabonnement.ChangeCanalActivity;
import cg.rbns.majitech.smatvalue.layouts.reabonnement.ChangeStartimeActivity;
import cg.rbns.majitech.smatvalue.layouts.mecrecu.DepotBloqueActivity;
import cg.rbns.majitech.smatvalue.layouts.mecrecu.DepotCourantActivity;
import cg.rbns.majitech.smatvalue.layouts.mecrecu.DepotEpargneActivity;
import cg.rbns.majitech.smatvalue.layouts.mecrecu.DepotTontineActivity;
import cg.rbns.majitech.smatvalue.layouts.directcash.MoneyShareActivity;
import cg.rbns.majitech.smatvalue.layouts.directcash.MoneyShareActivity2;
import cg.rbns.majitech.smatvalue.layouts.reabonnement.PayCanalActivity;
import cg.rbns.majitech.smatvalue.layouts.reabonnement.PayStartimeActivity;
import cg.rbns.majitech.smatvalue.layouts.directcash.RechargeFourActivity;
import cg.rbns.majitech.smatvalue.layouts.directcash.RechargeOneActivity;
import cg.rbns.majitech.smatvalue.layouts.directcash.RechargeThreeActivity;
import cg.rbns.majitech.smatvalue.layouts.directcash.RechargeTwoActivity;
import cg.rbns.majitech.smatvalue.layouts.mecrecu.RetraitCourantActivity;
import cg.rbns.majitech.smatvalue.layouts.mecrecu.RetraitEpargneActivity;
import cg.rbns.majitech.smatvalue.layouts.mecrecu.RetraitTontineActivity;
import cg.rbns.majitech.smatvalue.layouts.directcash.SoldeActivity;
import cg.rbns.majitech.smatvalue.layouts.transfert.TransfertActivity;
import cg.rbns.majitech.smatvalue.layouts.transfert.UserInfosActivity;
import cg.rbns.majitech.smatvalue.utilities.PreferenceManager;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private int checkedItem;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        eventListener();
        return binding.getRoot();
    }
    private void eventListener() {
        binding.homeDirectcash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContextThemeWrapper ctw = new ContextThemeWrapper(getActivity(), R.style.CustomAlertDialog);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctw);
                String[] items = {
                        getString(R.string.mtn_to_airtel),
                        getString(R.string.airtel_to_mtn),
                        getString(R.string.solde),
                        getString(R.string.recharge1),getString(R.string.recharge2),
                        getString(R.string.recharge3), getString(R.string.recharge4)
                };
                alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent isend1 = new Intent(getActivity(), MoneyShareActivity.class);
                                startActivity(isend1);
                                dialog.dismiss();
                                break;
                            case 1:
                                Intent isend2 = new Intent(getActivity(), MoneyShareActivity2.class);
                                startActivity(isend2);
                                dialog.dismiss();
                                break;
                            case 2:
                                startActivity(new Intent(getActivity(), SoldeActivity.class));
                                dialog.dismiss();
                                break;
                            case 3:
                                startActivity(new Intent(getActivity(), RechargeOneActivity.class));
                                dialog.dismiss();
                                break;
                            case 4:
                                startActivity(new Intent(getActivity(), RechargeTwoActivity.class));
                                dialog.dismiss();
                                break;
                            case 5:
                                startActivity(new Intent(getActivity(), RechargeThreeActivity.class));
                                dialog.dismiss();
                                break;
                            case 6:
                                startActivity(new Intent(getActivity(), RechargeFourActivity.class));
                                dialog.dismiss();
                                break;
                        }
                    }
                });
                AlertDialog alert = alertDialog.create();
                alert.setCanceledOnTouchOutside(false);
                alert.show();
            }
        });
        binding.homeTransfertInternational.setOnClickListener(v -> startTransfert());
        binding.homeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContextThemeWrapper ctw = new ContextThemeWrapper(getActivity(), R.style.CustomAlertDialog);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctw);
                String[] items = {
                        getString(R.string.pay_canal),
                        getString(R.string.change_canal),
                        getString(R.string.pay_startime),
                        getString(R.string.change_startime),
                };
                alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent isend1 = new Intent(getActivity(), PayCanalActivity.class);
                                startActivity(isend1);
                                dialog.dismiss();
                                break;
                            case 1:
                                Intent isend2 = new Intent(getActivity(), ChangeCanalActivity.class);
                                startActivity(isend2);
                                dialog.dismiss();
                                break;
                            case 2:
                                startActivity(new Intent(getActivity(), PayStartimeActivity.class));
                                dialog.dismiss();
                                break;
                            case 3:
                                startActivity(new Intent(getActivity(), ChangeStartimeActivity.class));
                                dialog.dismiss();
                                break;
                        }
                    }
                });
                AlertDialog alert = alertDialog.create();
                alert.setCanceledOnTouchOutside(false);
                alert.show();
            }
        });
        binding.homeMecrecu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContextThemeWrapper ctw = new ContextThemeWrapper(getActivity(), R.style.CustomAlertDialog);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctw);
                String[] items = {
                        getString(R.string.depo_courant),
                        getString(R.string.depo_epargne),
                        getString(R.string.depo_bloque),
                        getString(R.string.depo_tontine),
                        getString(R.string.retrait_courant),
                        getString(R.string.retrait_epargne),
                        getString(R.string.retrait_tontine),
                };
                alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                startActivity(new Intent(getActivity(), DepotCourantActivity.class));
                                dialog.dismiss();
                                break;
                            case 1:
                                startActivity(new Intent(getActivity(), DepotEpargneActivity.class));
                                dialog.dismiss();
                                break;
                            case 2:
                                startActivity(new Intent(getActivity(), DepotBloqueActivity.class));
                                dialog.dismiss();
                                break;
                            case 3:
                                startActivity(new Intent(getActivity(), DepotTontineActivity.class));
                                dialog.dismiss();
                                break;
                            case 4:
                                startActivity(new Intent(getActivity(), RetraitCourantActivity.class));
                                dialog.dismiss();
                                break;
                            case 5:
                                startActivity(new Intent(getActivity(), RetraitEpargneActivity.class));
                                dialog.dismiss();
                                break;
                            case 6:
                                startActivity(new Intent(getActivity(), RetraitTontineActivity.class));
                                dialog.dismiss();
                                break;
                        }
                    }
                });
                AlertDialog alert = alertDialog.create();
                alert.setCanceledOnTouchOutside(false);
                alert.show();
            }
        });
    }

    private void startTransfert() {
        if (!restorePrefData()){
            Intent intent = new Intent(getActivity(), UserInfosActivity.class);
            startActivity(intent);
        } else {
            startActivity(new Intent(getContext(), TransfertActivity.class));
        }
    }


    private boolean restorePrefData() {
        SharedPreferences pref = getActivity().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isUserInfo",false);
        return  isIntroActivityOpnendBefore;
    }


}