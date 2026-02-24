package com.example.annexe2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    //etape1
    Ecouteur ec;
    Button btnEnvoyer;
    Spinner spinnerNom;
    EditText champCouriel;
    EditText champTransfert;
    TextView champSolde;
    int solde;
    ArrayList<String> choix;
    DecimalFormat df  = new DecimalFormat("#,##0.00$");
     HashMap<String, Compte> hashMap = new HashMap<>();
     Compte compteChoisi;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnEnvoyer = findViewById(R.id.envoyer);
        spinnerNom = findViewById(R.id.spinnerNom);
        champSolde = findViewById(R.id.champSolde);
        champCouriel = findViewById(R.id.champCouriel);
        champTransfert = findViewById(R.id.champTransfert);

        choix = new ArrayList<>();
        hashMap.put("Cheque", new Compte("Cheque", 1000));
        hashMap.put("Epargne",new Compte("Epargne", 10000));
        hashMap.put("Epargne Plus", new Compte("Epargne Plus", 20000));
        choix.addAll(hashMap.keySet()); //place toute les clées dans le spinner


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, choix);
        spinnerNom.setAdapter(adapter);



        //etape1
        ec = new Ecouteur();
        //etape2
        btnEnvoyer.setOnClickListener(ec);
        spinnerNom.setOnItemSelectedListener(ec);

    }


    private class Ecouteur implements View.OnClickListener, AdapterView.OnItemSelectedListener {


        @Override
        public void onClick(View source) {
            // quand on click on est ici

            if (!champCouriel.getText().toString().trim().isEmpty() && champCouriel.getText().toString().matches("[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+")){
                if(compteChoisi.transfert(Double.parseDouble(champTransfert.getText().toString()))) {
                    champSolde.setText(df.format(compteChoisi.getSolde()));
                    champTransfert.setText("");
                }
                else{
                    champTransfert.setText("");
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Attention")
                            .setMessage("Il manque de fonds")
                            .show();

                }

            }
            else {
                champCouriel.setText("Vous devez indiquez un courriel");
                champTransfert.setText(0);
            }


        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // méthode Jacob
            String txt = parent.getAdapter().getItem(position).toString();
            //méthode ludo
            String txt1 = (String) parent.getSelectedItem();
            //méthode emile
            String txt2 = choix.get(position);
            //méthode eric
            TextView temp = (TextView) view;
            String txt3 = temp.getText().toString();

            Toast.makeText(MainActivity.this,txt,Toast.LENGTH_SHORT).show();

            compteChoisi =  hashMap.get(txt);
            champSolde.setText(df.format(compteChoisi.getSolde()));

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    }
