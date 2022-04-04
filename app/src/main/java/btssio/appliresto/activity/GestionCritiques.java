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

import btssio.appliresto.R;
import btssio.appliresto.modele.Critique;
import btssio.appliresto.modele.CritiqueDAO;
import btssio.appliresto.modele.Resto;
import btssio.appliresto.modele.RestoDAO;
import btssio.appliresto.modele.User;
import btssio.appliresto.utils.IntentStorage;

public class GestionCritiques extends Activity implements View.OnClickListener{

    private Button supp,modif,retour;
    private Spinner nomR;
    private RadioGroup GroupNotes;
    private RadioButton radioButton1,radioButton2,radioButton3,radioButton4,radioButton5;
    private ArrayList<Resto> listeResto;
    private EditText EditCritiques;
    private CritiqueDAO uneCritiqueDAO = new CritiqueDAO(this);
    private ArrayList<Critique> lesCrit;


    private User loggedUser;
    private String mail,commentaire;
    private int idR,note;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_critiques);

        loggedUser = IntentStorage.get(getIntent(), "LoggedUser");

        mail=loggedUser.getMailU();

        lesCrit= uneCritiqueDAO.getCritiques(mail);

        supp=(Button) findViewById(R.id.btnSupOuValid);
        modif=(Button) findViewById(R.id.modifierCritiques);
        retour=(Button) findViewById(R.id.btnRetour);

        supp.setOnClickListener(this);
        modif.setOnClickListener(this);
        retour.setOnClickListener(this);

        nomR=(Spinner) findViewById(R.id.spinResto);

        EditCritiques = (EditText) findViewById(R.id.EditCritiques);

        GroupNotes= (RadioGroup) this.findViewById(R.id.radioGroup_notes);
        radioButton1 = (RadioButton) this.findViewById(R.id.radioButton_1);
        radioButton2  =  (RadioButton)this.findViewById(R.id.radioButton_2);
        radioButton3  =  (RadioButton)this.findViewById(R.id.radioButton_3);
        radioButton4  =  (RadioButton)this.findViewById(R.id.radioButton_4);
        radioButton5  =  (RadioButton)this.findViewById(R.id.radioButton_5);



        RestoDAO RestoAcces = new RestoDAO(this);
        listeResto = RestoAcces.getRestos();

        final ArrayAdapter<Resto> spinRestoAdapter = new ArrayAdapter<Resto>(this.getBaseContext(), android.R.layout.simple_spinner_item);

        for (int i = 0; i < listeResto.size(); i++) {
            spinRestoAdapter.add(listeResto.get(i));
        }
        nomR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("log", Integer.toString(position) + " " + listeResto.get(position).toString());
                idR = listeResto.get(position).getIdR();
                commentaire=lesCrit.get(position).getCommentaire();
                note=lesCrit.get(position).getNote();

                EditCritiques.setText(commentaire);
                switch (note){
                    case 1:
                        radioButton1.setChecked(true);
                    break;
                    case 2:
                        radioButton2.setChecked(true);
                    break;
                    case 3:
                        radioButton3.setChecked(true);
                    break;
                    case 4:
                        radioButton4.setChecked(true);
                    break;
                    case 5:
                        radioButton5.setChecked(true);
                    break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSupOuValid:
                uneCritiqueDAO.supprimerCrit(idR,mail);
                Intent oui = new Intent(GestionCritiques.this, GestionCritiques.class);
                startActivity(oui);

            break;
            case R.id.modifierCritiques:
                Critique uneCritique = new Critique(idR, mail,doSave() , EditCritiques.getText().toString());
                uneCritiqueDAO.modifierCrit(uneCritique);
                Intent non = new Intent(GestionCritiques.this, GestionCritiques.class);
                startActivity(non);
            break;
            case R.id.btnRetour:
                Intent retour = new Intent(GestionCritiques.this, GestionResto.class);
                startActivity(retour);
            break;
        }
    }

    private int doSave()  {
        int noteResto = this.GroupNotes.getCheckedRadioButtonId();
        return noteResto;
    }
}
