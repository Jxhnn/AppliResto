package btssio.appliresto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import btssio.appliresto.R;
import btssio.appliresto.modele.Resto;
import btssio.appliresto.modele.RestoDAO;

public class AjouterResto extends AppCompatActivity {
    private EditText nomResto, numAdr, voieR, villeR, cpR, latR, longR, descR, horaireR;
    private Button enregistreResto;
    private RestoDAO restoDao=new RestoDAO(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_resto);


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


        enregistreResto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Resto unResto= new Resto(String.valueOf(nomResto),Integer.parseInt(String.valueOf(numAdr)),String.valueOf(voieR),Integer.parseInt(String.valueOf(cpR)),String.valueOf(villeR),Integer.parseInt(String.valueOf(latR)),Integer.parseInt(String.valueOf(longR)),String.valueOf(descR),String.valueOf(horaireR));
                restoDao.addResto(unResto);
            };
        });
    }

    public void onClick(View v){
        Intent retour = new Intent(AjouterResto.this,GestionResto.class);
        startActivity(retour);
    }
}