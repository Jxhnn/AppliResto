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

public class ConsulterResto extends AppCompatActivity {
    private TextView adrResto,coordResto,descResto,horairesResto;
    private String adrR,coordR,descR,horairesR;
    private Spinner nomResto;
    private ArrayList<Resto> lesRestos;
    private Button retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        nomResto = (Spinner) findViewById(R.id.spinnerNomResto);
        adrResto = (TextView) findViewById(R.id.textViewAdresseRes);
        coordResto = (TextView) findViewById(R.id.textViewCoord);
        descResto = (TextView) findViewById(R.id.textViewDescResto);
        horairesResto = (TextView) findViewById(R.id.textViewHorairesResto);

        retour = (Button) findViewById(R.id.buttonRetour);
        retour.setOnClickListener((View.OnClickListener) this);

        RestoDAO restoDao = new RestoDAO(this);
        lesRestos = restoDao.getRestos();
        ArrayAdapter<Resto> spinResto = new ArrayAdapter<Resto>(this.getBaseContext(),android.R.layout.simple_spinner_item);

        for(int i=0;i<lesRestos.size();i++){
            spinResto.add(lesRestos.get(i));
        }

        nomResto.setAdapter(spinResto);

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



        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulter_resto);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent retour = new Intent(ConsulterResto.this, GestionResto.class);
            startActivity(retour);
        }
    };
}
