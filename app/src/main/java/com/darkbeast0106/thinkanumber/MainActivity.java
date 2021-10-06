package com.darkbeast0106.thinkanumber;


import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btnPlusz, btnMinusz, btnTipp;
    private ImageView hp1, hp2, hp3, hp4;
    private ImageView[] eletek;
    private TextView felhasznaloTippText;
    private int felhasznaloTipp, gondoltSzam, maximum, elet;
    private Random random;
    private Toast customToast;
    private AlertDialog.Builder alertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//teszt
        init();
        ujJatek();
        btnPlusz.setOnClickListener((view) -> {
            if (felhasznaloTipp < maximum) {
                felhasznaloTipp++;
                felhasznaloTippText.setText(String.valueOf(felhasznaloTipp));
            } else {
                //Hibaüzenet tipp nem lehet maxnál nagyobb
                Toast.makeText(getApplicationContext(), "Nem lehet nagyobb 10-nél!", Toast.LENGTH_SHORT).show();
            }
        });
        btnMinusz.setOnClickListener(view -> {
            if (felhasznaloTipp > 1) {
                felhasznaloTipp--;
                felhasznaloTippText.setText(String.valueOf(felhasznaloTipp));
            } else {
                //Hibaüzenet tipp nem lehet kisebb mint 1
                /*Toast toast = new Toast(getApplicationContext());
                toast.setText("Nem lehet kisebb 1-nél");
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();*/
                Toast.makeText(getApplicationContext(), "Nem lehet kisebb 1-nél!", Toast.LENGTH_SHORT).show();
            }
        });
        btnTipp.setOnClickListener(view -> {
            if (felhasznaloTipp == gondoltSzam) {
                // Győzelem ALERT DIALOG
                //alertBuilder.setTitle("Győztél, játék vége!").create().show();
                alertBuilder.setTitle("Játék vége");
                alertBuilder.create();
                alertBuilder.show();
            } else if (felhasznaloTipp < gondoltSzam) {
                // Nagyobb számra gondoltam EGYEDI TOAST
                eletLevon();
                customToast.show();
                Toast.makeText(getApplicationContext(), "Nagyobb száma gondoltam!", Toast.LENGTH_SHORT).show();
            } else {
                // Kisebb számra gondoltam EGYEDI TOAST
                eletLevon();
                customToast.show();
                Toast.makeText(getApplicationContext(), "Kisebb száma gondoltam!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eletLevon() {
        if (elet > 0) {
            elet--;
        }
        eletek[elet].setImageResource(R.drawable.heart1);
        if (elet == 0) {
            alertBuilder.setTitle("Játék vége");
            alertBuilder.create();
            alertBuilder.show();
            //alertBuilder.setTitle("Vesztettél, játék vége!").create().show();
            // TODO: jatek vege
        }
    }

    private void ujJatek() {
        felhasznaloTipp = 1;
        felhasznaloTippText.setText("1");
        gondoltSzam = random.nextInt(maximum) + 1;
        Log.d("gondolt szam", String.valueOf(gondoltSzam));
        elet = 4;
        for (ImageView elet : eletek) {
            elet.setImageResource(R.drawable.heart2);
        }
    }

    private void init() {
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
        eletek = new ImageView[]{hp1, hp2, hp3, hp4};
        customToast = new Toast(getApplicationContext());
        CreateCustomToast();
        alertBuilder = new AlertDialog.Builder(getApplicationContext());
        CreateAlertDialog();
    }

    private void CreateAlertDialog() {
        alertBuilder.setMessage("Szeretnél e új játékot?");
        alertBuilder.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alertBuilder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ujJatek();
                closeContextMenu();
            }
        });
        //kötelező választani
        alertBuilder.setCancelable(false);
    }

    private void CreateCustomToast() {
        customToast.setDuration(Toast.LENGTH_SHORT);
        View view = getLayoutInflater().inflate(R.layout.custom_toast,
                findViewById(R.id.custom_toast));
        customToast.setView(view);
        customToast.setGravity(Gravity.CENTER, 0, 0);
    }
}