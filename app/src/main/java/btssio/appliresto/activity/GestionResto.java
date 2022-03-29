package btssio.appliresto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import btssio.appliresto.R;

import androidx.appcompat.app.AppCompatActivity;

public class GestionResto extends AppCompatActivity {

    private Button ajouter,supp,modif,consulter,retour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_resto);


        ajouter = (Button) findViewById(R.id.buttonAjouteResto);
        supp = (Button) findViewById(R.id.buttonSupResto);
        modif = (Button) findViewById(R.id.buttonModifResto);
        consulter = (Button) findViewById(R.id.buttonConsultResto);
        retour =(Button) findViewById(R.id.buttonRetour);

        ajouter.setOnClickListener((View.OnClickListener) this);
        supp.setOnClickListener((View.OnClickListener) this);
        modif.setOnClickListener((View.OnClickListener) this);
        consulter.setOnClickListener((View.OnClickListener) this);
        retour.setOnClickListener((View.OnClickListener) this);


    }


    public final View.OnClickListener onClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonAjouteResto:
                    Intent ajouter = new Intent(GestionResto.this, AjouterResto.class);
                    startActivity(ajouter);
                break;
                case R.id.buttonConsultResto:
                    Intent cons = new Intent(GestionResto.this, ConsulterResto.class);
                    startActivity(cons);
                break;
                case R.id.buttonModifResto:
                    Intent modif = new Intent(GestionResto.this, ModifierResto.class);
                    startActivity(modif);
                break;
                case R.id.buttonSupResto:
                    Intent sup = new Intent(GestionResto.this, SupprimerResto.class);
                    startActivity(sup);
                break;
                case R.id.buttonRetour:
                    Intent retour = new Intent(GestionResto.this, GestionResto.class);
                    startActivity(retour);
                break;
            }
        }
    };
}
