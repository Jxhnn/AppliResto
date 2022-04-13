package btssio.appliresto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import btssio.appliresto.R;
import btssio.appliresto.modele.User;
import btssio.appliresto.utils.IntentStorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

public class GestionResto extends AppCompatActivity implements View.OnClickListener{

    private Button ajouter,supp,modif,consulter,retour;
    private User loggedUser;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_resto);


        ajouter = (Button) findViewById(R.id.buttonAjouteResto);
        supp = (Button) findViewById(R.id.buttonSupResto);
        modif = (Button) findViewById(R.id.buttonModifResto);
        consulter = (Button) findViewById(R.id.buttonConsultResto);
        retour =(Button) findViewById(R.id.btnRetour);

        ajouter.setOnClickListener(this);
        supp.setOnClickListener(this);
        modif.setOnClickListener(this);
        consulter.setOnClickListener(this);
        retour.setOnClickListener(this);

        loggedUser = IntentStorage.get(getIntent(), "LoggedUser");
    }



    public void onClick(View v) {
        switch (v.getId()) {
            // Dans le cas d'un click sur un bouton ammène sur la page associée
            case R.id.buttonAjouteResto:
                Intent ajouterR = new Intent(GestionResto.this, AjouterResto.class);
                IntentStorage.add(ajouterR, "LoggedUser", loggedUser);
                finish();
                startActivity(ajouterR);
                break;
            case R.id.buttonConsultResto:
                Intent cons = new Intent(GestionResto.this, ConsulterResto.class);
                IntentStorage.add(cons, "LoggedUser", loggedUser);
                finish();
                startActivity(cons);
                break;
            case R.id.buttonModifResto:
                Intent modifR = new Intent(GestionResto.this, ModifierResto.class);
                IntentStorage.add(modifR, "LoggedUser", loggedUser);
                finish();
                startActivity(modifR);
                break;
            case R.id.buttonSupResto:
                Intent sup = new Intent(GestionResto.this, SupprimerResto.class);
                IntentStorage.add(sup, "LoggedUser", loggedUser);
                finish();
                startActivity(sup);
                break;
            case R.id.btnRetour:
                Intent retourR = new Intent(GestionResto.this, MenuAdmin.class);
                IntentStorage.add(retourR, "LoggedUser", loggedUser);
                finish();
                startActivity(retourR);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        IntentStorage.remove(getIntent());
        super.onDestroy();
    }
}
