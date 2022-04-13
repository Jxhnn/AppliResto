package btssio.appliresto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import btssio.appliresto.R;
import btssio.appliresto.modele.Resto;
import btssio.appliresto.modele.RestoDAO;
import btssio.appliresto.modele.User;
import btssio.appliresto.utils.IntentStorage;

public class AjouterResto extends AppCompatActivity implements View.OnClickListener{
    private EditText nomResto, numAdr, voieR, villeR, cpR, latR, longR, descR, horaireR;
    private Button enregistreResto,retour;
    private RestoDAO restoDao=new RestoDAO(this);
    private User loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_resto);

        // instantiation des éléments de la vue
        nomResto = (EditText) findViewById(R.id.editTextNomResto);
        numAdr = (EditText) findViewById(R.id.editTextNumAdr);
        voieR = (EditText) findViewById(R.id.editTextVoieAdr);
        villeR = (EditText) findViewById(R.id.editTextVille);
        cpR = (EditText) findViewById(R.id.editTextCP);
        latR = (EditText) findViewById(R.id.editTextLat);
        longR = (EditText) findViewById(R.id.editTextLong);
        descR = (EditText) findViewById(R.id.editTextDesc);
        horaireR = (EditText) findViewById(R.id.editTextHorai);

        enregistreResto = (Button) findViewById(R.id.buttonAjouR);
        enregistreResto.setOnClickListener(this);

        retour = (Button) findViewById(R.id.btnRetour);
        retour.setOnClickListener(this);

        loggedUser = IntentStorage.get(getIntent(), "LoggedUser");

    }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonAjouR:
                    try {
                        // Dans le cas d'un click sur le bouton "buttonAjouR" créer un nouveau resto avec valeurs entrées dans les Edittext
                        Resto unResto= new Resto(nomResto.getText().toString(),
                                Integer.parseInt(numAdr.getText().toString()), voieR.getText().toString(),
                                Integer.parseInt(cpR.getText().toString()),
                                villeR.getText().toString(),Float.parseFloat(latR.getText().toString()),
                                Float.parseFloat(longR.getText().toString()),descR.getText().toString(),
                                horaireR.getText().toString(), "");


                        restoDao.addResto(unResto);
                        finish();
                        Intent i = new Intent(AjouterResto.this, GestionResto.class);
                        IntentStorage.add(i, "LoggedUser", loggedUser);
                        startActivity(i);

                    } catch (NumberFormatException exc) {
                        Toast.makeText(this, "Certains champs numériques ne sont pas valides !", Toast.LENGTH_SHORT).show();
                    }

                break;
                case R.id.btnRetour:
                    // Dans le cas d'un click sur le bouton "btnRetour" retourne sur la page précédente
                    Intent retour = new Intent(AjouterResto.this, GestionResto.class);
                    IntentStorage.add(retour, "LoggedUser", loggedUser);
                    startActivity(retour);
                break;
            }
        }

    @Override
    protected void onDestroy() {
        IntentStorage.remove(getIntent());
        super.onDestroy();
    }
}