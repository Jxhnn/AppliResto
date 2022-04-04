package btssio.appliresto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import btssio.appliresto.R;
import btssio.appliresto.modele.Resto;
import btssio.appliresto.modele.RestoDAO;

public class ModifierResto extends AppCompatActivity implements View.OnClickListener{

    private Spinner nomResto;
    private ArrayList<Resto> lesRestos;
    private EditText nomR,numAdrR,voieAdrR,cpR,villeR,latR,longR,descR,horR;
    private String nom,num,voie,cp,ville,lat,longRes,desc,hor;

    private int idResto;
    private Button modif, retour;
    RestoDAO restoDao = new RestoDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier_resto);

        retour=(Button) findViewById(R.id.btnRetour);
        modif=(Button) findViewById(R.id.btnModifier);

        retour.setOnClickListener(this);
        modif.setOnClickListener(this);

        nomR= findViewById(R.id.editTextNomResto);
        numAdrR= findViewById(R.id.editTextNumAdr);
        voieAdrR= findViewById(R.id.editTextVoieAdr);
        cpR= findViewById(R.id.editTextCp);
        villeR= findViewById(R.id.editTextVille);
        latR= findViewById(R.id.editTextLat);
        longR= findViewById(R.id.editTextLong);
        descR= findViewById(R.id.editTextDesc);
        horR= findViewById(R.id.editTextHr);

        //Création d'une barre déroulante pour sélectionner un nom de resto
        nomResto = (Spinner) findViewById(R.id.spinnerNomResto);
        RestoDAO restoDao = new RestoDAO(this);
        lesRestos = restoDao.getRestos();
        ArrayAdapter<Resto> spinResto = new ArrayAdapter<Resto>(this.getBaseContext(), android.R.layout.simple_spinner_item);
        // boucle pour récupérer les noms resto de la base de donnée
        for (int i = 0; i < lesRestos.size(); i++) {
            spinResto.add(lesRestos.get(i));
        }


        nomResto.setAdapter(spinResto);
        //modifie les champs du resto sélectionné
        nomResto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("log", "" + lesRestos.get(i).getNomR());
                idResto = lesRestos.get(i).getIdR();

                nom=lesRestos.get(i).getNomR();
                num=lesRestos.get(i).getNumAdrR()+"";
                voie=lesRestos.get(i).getVoieAdrR();
                cp=lesRestos.get(i).getCpR()+"";
                ville=lesRestos.get(i).getVilleR();
                lat=lesRestos.get(i).getLatitudeDegR()+"";
                longRes=lesRestos.get(i).getLongitudeDegR()+"";
                desc=lesRestos.get(i).getDescR();
                hor=lesRestos.get(i).getHorairesR();
                //incrémente les valeurs rentrées en Edit text
                nomR.setText(nom);
                numAdrR.setText(num);
                voieAdrR.setText(voie);
                cpR.setText(cp);
                villeR.setText(ville);
                latR.setText(lat);
                longR.setText(longRes);
                descR.setText(desc);
                horR.setText(hor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRetour:
                Intent retour = new Intent(ModifierResto.this, GestionResto.class);
                startActivity(retour);
                break;
            //Dans le cas d'un click sur bouton modifier
            case R.id.btnModifier:
                Resto unResto = restoDao.getUnResto(idResto);
                restoDao.modifierResto(unResto);
                Intent oui = new Intent(ModifierResto.this, ModifierResto.class);
                startActivity(oui);
                break;
        }
    }
}
