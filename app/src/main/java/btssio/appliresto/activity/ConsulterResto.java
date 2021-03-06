package btssio.appliresto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

import btssio.appliresto.R;
import btssio.appliresto.modele.Resto;
import btssio.appliresto.modele.RestoDAO;
import btssio.appliresto.modele.User;
import btssio.appliresto.utils.IntentStorage;

public class ConsulterResto extends AppCompatActivity implements View.OnClickListener{
    private TextView adrResto,coordResto,descResto,horairesResto;
    private String adrR,coordR,descR,horairesR;
    private Spinner nomResto;
    private ArrayList<Resto> lesRestos;
    private Button retour;
    private User loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulter_resto);
        // instantiation des éléments de la vue
        nomResto = (Spinner) findViewById(R.id.spinnerNomResto);
        adrResto = (TextView) findViewById(R.id.textViewAdresseRes);
        coordResto = (TextView) findViewById(R.id.textViewCoord);
        descResto = (TextView) findViewById(R.id.textViewDescResto);
        horairesResto = (TextView) findViewById(R.id.textViewHorairesResto);

        loggedUser = IntentStorage.get(getIntent(), "LoggedUser");

        retour = (Button) findViewById(R.id.btnRetour);
        retour.setOnClickListener(this);
        // création d'une liste déroulante pour récupérer le nom des resto
        RestoDAO restoDao = new RestoDAO(this);
        lesRestos = restoDao.getRestos();
        ArrayAdapter<Resto> spinResto = new ArrayAdapter<Resto>(this.getBaseContext(),android.R.layout.simple_spinner_item);
        //création d'une boucle pour récupérer les valeurs du tableau "Resto"
        for(int i=0;i<lesRestos.size();i++){
            spinResto.add(lesRestos.get(i));
        }

        nomResto.setAdapter(spinResto);

        //Affichage des champs du resto sélectionné
        nomResto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("log",""+lesRestos.get(i).getNomR());
                adrR=lesRestos.get(i).getNumAdrR()+" "+lesRestos.get(i).getVoieAdrR()+" "+lesRestos.get(i).getVilleR()+" "+lesRestos.get(i).getCpR();
                coordR=lesRestos.get(i).getLatitudeDegR()+" "+lesRestos.get(i).getLongitudeDegR();
                descR=lesRestos.get(i).getDescR();
                horairesR=lesRestos.get(i).getHorairesR();
                horairesR=lesRestos.get(i).getHorairesR();

                adrResto.setText(adrR);
                coordResto.setText(coordR);
                descResto.setText(descR);
                horairesResto.setText(horairesR);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Calendar cal = Calendar.getInstance();
    }

    public void onClick(View v) {
        Intent retour = new Intent(ConsulterResto.this, GestionResto.class);
        IntentStorage.add(retour, "LoggedUser", loggedUser);
        finish();
        startActivity(retour);
    }
}
