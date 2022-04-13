package btssio.appliresto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import btssio.appliresto.R;
import btssio.appliresto.modele.Resto;
import btssio.appliresto.modele.RestoDAO;
import btssio.appliresto.modele.User;
import btssio.appliresto.utils.IntentStorage;

public class SupprimerResto extends AppCompatActivity implements View.OnClickListener{

    private Spinner nomResto;
    private ArrayList<Resto> lesRestos;
    private int idResto;
    private Button sup, retour;
    RestoDAO restoDao = new RestoDAO(this);
    private User loggedUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supprimer_resto);


        retour = (Button) findViewById(R.id.btnRetour);
        sup = (Button) findViewById(R.id.buttonSup);
        retour.setOnClickListener(this);
        sup.setOnClickListener(this);

        loggedUser = IntentStorage.get(getIntent(), "LoggedUser");

        //Création d'une barre déroulante pour sélectionner un nom de resto
        nomResto = (Spinner) findViewById(R.id.spinnerNomResto);
        RestoDAO restoDao = new RestoDAO(this);
        lesRestos = restoDao.getRestos();
        ArrayAdapter<Resto> spinResto = new ArrayAdapter<Resto>(this.getBaseContext(), android.R.layout.simple_spinner_item);
        // Boucle pour récupérer les noms resto de la base de donnée
        for (int i = 0; i < lesRestos.size(); i++) {
            spinResto.add(lesRestos.get(i));
        }


        nomResto.setAdapter(spinResto);
        nomResto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("log", "" + lesRestos.get(i).getNomR());
                idResto = lesRestos.get(i).getIdR();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRetour:
                Intent retour = new Intent(SupprimerResto.this, GestionResto.class);
                IntentStorage.add(retour, "LoggedUser", loggedUser);
                finish();
                startActivity(retour);
                break;
            case R.id.buttonSup:
                //Dans le cas d'un click sur le bouton "buttonsup" fait appel a la méthode supprimerResto pour supprimer le resto sélectionné
                Resto unResto = restoDao.getUnResto(idResto);
                restoDao.supprimerResto(unResto.getIdR());
                Toast.makeText(this, "Le restaurant a bien été supprimé !", Toast.LENGTH_SHORT).show();

                Intent oui = new Intent(SupprimerResto.this, GestionResto.class);
                IntentStorage.add(oui, "LoggedUser", loggedUser);
                finish();
                startActivity(oui);
                break;
        }
    }
}

