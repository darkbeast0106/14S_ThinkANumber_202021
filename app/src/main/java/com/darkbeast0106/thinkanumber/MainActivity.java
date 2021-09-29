package com.darkbeast0106.thinkanumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btnPlusz, btnMinusz, btnTipp;
    private ImageView hp1, hp2, hp3, hp4;
    private ImageView[] eletek;
    private TextView felhasznaloTippText;
    private int felhasznaloTipp, gondoltSzam, maximum, elet;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        ujJatek();
        btnPlusz.setOnClickListener((view) -> {
            if (felhasznaloTipp < maximum) {
                felhasznaloTipp++;
                felhasznaloTippText.setText(String.valueOf(felhasznaloTipp));
            } else {
                // TODO: Hibaüzenet tipp nem lehet maxnál nagyobb
            }
        });
        btnMinusz.setOnClickListener(view -> {
            if (felhasznaloTipp > 1) {
                felhasznaloTipp--;
                felhasznaloTippText.setText(String.valueOf(felhasznaloTipp));
            } else {
                // TODO: Hibaüzenet tipp nem lehet kisebb mint 1
            }
        });
        btnTipp.setOnClickListener(view -> {
            if (felhasznaloTipp == gondoltSzam){
                // TODO: Győzelem
            } else if (felhasznaloTipp < gondoltSzam) {
                // TODO: Nagyobb számra gondoltam
                eletLevon();
            } else {
                // TODO: Kisebb számra gondoltam
                eletLevon();
            }
        });
    }

    private void eletLevon() {
        if (elet > 0){
            elet--;
        }
        eletek[elet].setImageResource(R.drawable.heart1);
        if (elet == 0){
            // TODO: jatek vege
        }
    }

    private void ujJatek() {
        felhasznaloTipp = 1;
        felhasznaloTippText.setText("1");
        gondoltSzam = random.nextInt(maximum)+1;
        Log.d("gondolt szam", String.valueOf(gondoltSzam));
        elet = 4;
        for (ImageView elet: eletek) {
            elet.setImageResource(R.drawable.heart2);
        }
    }

    private void init(){
        btnPlusz = findViewById(R.id.btn_plusz);
        btnMinusz = findViewById(R.id.btn_minusz);
        btnTipp = findViewById(R.id.btn_tipp);
        hp1 = findViewById(R.id.hp1);
        hp2 = findViewById(R.id.hp2);
        hp3 = findViewById(R.id.hp3);
        hp4 = findViewById(R.id.hp4);
        felhasznaloTippText = findViewById(R.id.felhasznalo_valasztas);
        maximum = 10;
        random = new Random();
        eletek = new ImageView[]{hp1,hp2,hp3,hp4};
    }
}