package btssio.appliresto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import btssio.appliresto.R;

import androidx.appcompat.app.AppCompatActivity;

public class GestionResto extends AppCompatActivity implements View.OnClickListener{

    private Button ajouter,supp,modif,consulter,retour;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_resto);


        ajouter = (Button) findViewById(R.id.buttonAjouteResto);
        supp = (Button) findViewById(R.id.buttonSupResto);
        modif = (Button) findViewById(R.id.buttonModifResto);
        consulter = (Button) findViewById(R.id.buttonConsultResto);
        retour =(Button) findViewById(R.id.buttonRetour);

        ajouter.setOnClickListener(this);
        supp.setOnClickListener(this);
        modif.setOnClickListener(this);
        consulter.setOnClickListener(this);
        retour.setOnClickListener(this);


    }



    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAjouteResto:
                Intent ajouterR = new Intent(GestionResto.this, AjouterResto.class);
                startActivity(ajouterR);
                break;
            case R.id.buttonConsultResto:
                Intent cons = new Intent(GestionResto.this, ConsulterResto.class);
                startActivity(cons);
                break;
            case R.id.buttonModifResto:
                Intent modifR = new Intent(GestionResto.this, ModifierResto.class);
                startActivity(modifR);
                break;
            case R.id.buttonSupResto:
                Intent sup = new Intent(GestionResto.this, SupprimerResto.class);
                startActivity(sup);
                break;
            case R.id.buttonRetour:
                Intent retourR = new Intent(GestionResto.this, GestionResto.class);
                startActivity(retourR);
                break;
        }
    }
}
