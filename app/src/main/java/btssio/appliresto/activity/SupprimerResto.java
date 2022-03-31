package btssio.appliresto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import btssio.appliresto.R;
import btssio.appliresto.modele.Resto;
import btssio.appliresto.modele.RestoDAO;

public class SupprimerResto extends AppCompatActivity {

    private Spinner nomResto;
    private ArrayList<Resto> lesRestos;
    private int idResto;
    private Button sup, retour;
    RestoDAO restoDao = new RestoDAO(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supprimer_resto);


        retour = (Button) findViewById(R.id.buttonRetour);
        sup = (Button) findViewById(R.id.buttonSup);
        retour.setOnClickListener((View.OnClickListener) this);
        sup.setOnClickListener((View.OnClickListener)this);


        nomResto = (Spinner) findViewById(R.id.spinnerNomResto);
        RestoDAO restoDao = new RestoDAO(this);
        lesRestos = restoDao.getRestos();
        ArrayAdapter<Resto> spinResto = new ArrayAdapter<Resto>(this.getBaseContext(), android.R.layout.simple_spinner_item);

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

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonRetour:
                    Intent retour = new Intent(SupprimerResto.this, GestionResto.class);
                    startActivity(retour);
                    break;
                case R.id.buttonSup:
                    Resto unResto = restoDao.getUnResto(idResto);
                    restoDao.supprimerResto(unResto);
                    break;
            }
        }
    };
}

