package com.example.annexe2;

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
    EditText champNomCompte;
    TextView champSolde;
    ArrayList<String> choix;
    int solde;
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
        champNomCompte = findViewById(R.id.editTextDe);
        champSolde = findViewById(R.id.champSolde);

        choix = new ArrayList<>();
        choix.add("CHEQUE");
        choix.add("EPARGNE");
        choix.add("EPARGNEPLUS");


        //etape1
        ec = new Ecouteur();
        //etape2
        btnValider.setOnClickListener(ec);

    }


    private class Ecouteur implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            // quand on click on est ici
            String nomCompte = champNomCompte.getText().toString();
            nomCompte = nomCompte.trim().toUpperCase();

            if (choix.contains(nomCompte)){
                solde=500;
                champSolde.setText(String.valueOf(solde));
            }


        }
    }
}