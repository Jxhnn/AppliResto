package btssio.appliresto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import btssio.appliresto.R;
import btssio.appliresto.modele.Resto;
import btssio.appliresto.modele.RestoDAO;

public class ConsulterResto extends AppCompatActivity {
    private TextView adrResto,coordResto,descResto,horairesResto;
    private Spinner nomResto;
    private ArrayList<Resto> lesRestos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        nomResto = (Spinner) findViewById(R.id.spinnerNomResto);
        adrResto = (TextView) findViewById(R.id.textViewAdresseRes);
        coordResto = (TextView) findViewById(R.id.textViewCoord);
        descResto = (TextView) findViewById(R.id.textViewDescResto);
        horairesResto = (TextView) findViewById(R.id.textViewHorairesResto);
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
                Log.d("log",Integer.toString(i)+" "+lesRestos.get(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulter_resto);
    }

    public void onClick(View v){
        Intent retour = new Intent(ConsulterResto.this,GestionResto.class);
    }
}
