package btssio.appliresto.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

import btssio.appliresto.modele.Critique;
import btssio.appliresto.modele.CritiqueDAO;
import btssio.appliresto.modele.Resto;
import btssio.appliresto.modele.RestoDAO;

import btssio.appliresto.R;
import btssio.appliresto.modele.User;
import btssio.appliresto.utils.IntentStorage;

public class CritiquesResto extends Activity implements View.OnClickListener{

    private Spinner spinResto;
    private RadioGroup GroupNotes;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private RadioButton radioButton5;

    private String mail;
    private int idR;

    private EditText EditCritiques;
    private Button btnValider;
    private Button btnRetour;
    private ArrayList<Resto> listeResto;

    private User loggedUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        loggedUser = IntentStorage.get(getIntent(), "LoggedUser");

        mail=loggedUser.getMailU();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.critiques);
        btnValider = (Button) findViewById(R.id.btnSupOuValid);
        btnRetour = (Button) findViewById(R.id.btnRetour);

        btnValider.setOnClickListener(this);
        btnRetour.setOnClickListener(this);


        GroupNotes= (RadioGroup) this.findViewById(R.id.radioGroup_notes);
        radioButton1 = (RadioButton) this.findViewById(R.id.radioButton_1);
        radioButton2  =  (RadioButton)this.findViewById(R.id.radioButton_2);
        radioButton3  =  (RadioButton)this.findViewById(R.id.radioButton_3);
        radioButton4  =  (RadioButton)this.findViewById(R.id.radioButton_4);
        radioButton5  =  (RadioButton)this.findViewById(R.id.radioButton_5);

        EditCritiques = (EditText) findViewById(R.id.EditCritiques);
        spinResto = (Spinner) findViewById(R.id.spinResto);
        // Edit text critique pour ajouter une critique écrite par l'utilisateur au resto selectionné
        EditCritiques.setText(String.valueOf(EditCritiques));

        RestoDAO RestoAcces = new RestoDAO(this);
        // Liste déroulante des restos pour leurs mettre une note
        listeResto = RestoAcces.getRestos();
        final ArrayAdapter<Resto> spinRestoAdapter = new ArrayAdapter<Resto>(this.getBaseContext(), android.R.layout.simple_spinner_item);

        for (int i = 0; i < listeResto.size(); i++) {
            spinRestoAdapter.add(listeResto.get(i));
        }
        spinResto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("log", Integer.toString(position) + " " + listeResto.get(position).toString());
                idR=listeResto.get(position).getIdR();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        // When button "Save" clicked.

    }

    @Override
    public void onClick(View v) {
        //doSave();
        switch (v.getId()){
            case R.id.btnSupOuValid:
                Critique uneCritique = new Critique(idR, mail,doSave() , EditCritiques.getText().toString());
                CritiqueDAO uneCritiqueDAO = new CritiqueDAO(this);
                uneCritiqueDAO.addCritique(uneCritique);
            break;
            case R.id.btnRetour:
                Intent retour = new Intent(CritiquesResto.this, GestionResto.class);
                startActivity(retour);
                break;
        }


    }
        // Radio group 1-5 pour mettre une note au resto selectionné
        private int doSave()  {
            int noteResto = this.GroupNotes.getCheckedRadioButtonId();
            return noteResto;
        }
    }



