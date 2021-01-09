package com.example.simple_password_generator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


public class FragmentFirst extends Fragment {


    public View v;

    private Switch upperSwitch;
    private Switch lowerSwitch;
    private Switch numberSwitch;
    private Switch charSwitch;

    private boolean upperSit;
    private boolean lowerSit;
    private boolean numberSit;
    private boolean charSit;

    private SeekBar seekBar;

    private Button button;


    private TextView lengthShowing;
    private TextView passwordDisplayed;

   public int length = 4;

    AlertDialog.Builder alertDialog;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_first, container, false);
        // Inflate the layout for this fragment
        upperSwitch = (Switch) v.findViewById(R.id.switch1);
        lowerSwitch = (Switch) v.findViewById(R.id.switch2);
        numberSwitch = (Switch) v.findViewById(R.id.switch3);
        charSwitch = (Switch) v.findViewById(R.id.switch4);

        seekBar = (SeekBar) v.findViewById(R.id.seekBar2);

        button = (Button) v.findViewById(R.id.button);

        lengthShowing =  (TextView) v.findViewById(R.id.textView);

        passwordDisplayed = (TextView) v.findViewById(R.id.passwordDisplayed);

        CardView cardView = (CardView) v.findViewById(R.id.cardView2);

        upperSit = true;
        lowerSit = true;
        numberSit = true;
        charSit = true;

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lengthShowing.setText("Length :"+" "+progress);
                length = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        upperSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    upperSit = true;
                } else {
                    if(lowerSit == false && numberSit == false && charSit == false) {
                        buttonView.setChecked(true);
                        upperSit = true;
                    } else {
                        upperSit = false;
                    }
                }
            }
        });

        lowerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    lowerSit = true;
                } else {
                    if(upperSit == false && numberSit == false && charSit == false) {
                        upperSwitch.setChecked(true);
                        lowerSit = false;
                    } else {
                        lowerSit = false;
                    }
                }
            }
        });

        numberSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    numberSit = true;
                } else {
                    if(lowerSit == false && upperSit == false && charSit == false) {
                        upperSwitch.setChecked(true);
                        numberSit = false;
                    } else {
                        numberSit = false;
                    }
                }
            }
        });

        charSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    charSit = true;
                } else {
                    if(lowerSit == false && numberSit == false && upperSit == false) {
                        upperSwitch.setChecked(true);
                        charSit = false;
                    } else {
                        charSit = false;

                    }
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Here is to generate.
                Generation generation = new Generation(upperSit,lowerSit,numberSit,charSit,length);
                generation.setChecks();
                String pass = generation.generate();
                passwordDisplayed.setText(pass);

                //
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("kopyalanan", passwordDisplayed.getText().toString());
                cm.setPrimaryClip(clipData);
                Toast.makeText(getContext(),"Password is copied", Toast.LENGTH_SHORT).show();
            }
        });



        return v;
    }


}