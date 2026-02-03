package com.example.annexe2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //etape1
    Ecouteur ec;
    Button btnValider;
    Button btnEnvoyer;
    EditText champNomCompte;
    EditText champCouriel;
    EditText champTransfert;
    TextView champSolde;
    ArrayList<String> choix;
    int solde;
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

        btnValider = findViewById(R.id.valider);
        btnEnvoyer = findViewById(R.id.envoyer);
        champNomCompte = findViewById(R.id.editTextDe);
        champSolde = findViewById(R.id.champSolde);
        champCouriel = findViewById(R.id.champCouriel);
        champTransfert = findViewById(R.id.champTransfert);

        choix = new ArrayList<>();
        choix.add("CHEQUE");
        choix.add("EPARGNE");
        choix.add("EPARGNEPLUS");


        //etape1
        ec = new Ecouteur();
        //etape2
        btnValider.setOnClickListener(ec);
        btnEnvoyer.setOnClickListener(ec);

    }


    private class Ecouteur implements View.OnClickListener {


        @Override
        public void onClick(View source) {
            // quand on click on est ici
            if (source == btnValider) {
                String nomCompte = champNomCompte.getText().toString();
                nomCompte = nomCompte.trim().toUpperCase();

                if (choix.contains(nomCompte)) {
                    solde = 500;
                    champSolde.setText(String.valueOf(solde));
                } else {
                    champSolde.setText("Pas un bon nom de compte");
                    champNomCompte.setText("");
                }
            } else if (source == btnEnvoyer) {
                if (!champCouriel.getText().toString().isEmpty()){
                    int temp = solde - Integer.parseInt(champTransfert.getText().toString());
                    if (temp < 0){
                        solde = temp;
                        champSolde.setText(String.valueOf(solde));
                    }
                    else {
                        champTransfert.setHint("Solde indisponible ");
                    }


                }
                else {
                    champCouriel.setHint("Indiquer un destinataire");
                }
            }

        }
    }
}