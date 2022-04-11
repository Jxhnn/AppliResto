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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import btssio.appliresto.modele.Critique;
import btssio.appliresto.modele.CritiqueDAO;
import btssio.appliresto.modele.Resto;
import btssio.appliresto.modele.RestoDAO;

import btssio.appliresto.R;
import btssio.appliresto.modele.User;
import btssio.appliresto.utils.IntentStorage;

public class CritiquesResto extends Activity implements View.OnClickListener{

    private TextView restaurantName;
    private EditText editComment, editNote;
    private Button btnValider;
    private ImageView backButton;
    private Resto thisResto;

    private User loggedUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        loggedUser = IntentStorage.get(getIntent(), "LoggedUser");
        thisResto = IntentStorage.get(getIntent(), "thisResto");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.critiques);


        btnValider = (Button) findViewById(R.id.addComment);
        backButton = findViewById(R.id.backButton);
        editComment = (EditText) findViewById(R.id.descComment);
        restaurantName = findViewById(R.id.restaurantNameComm);
        editNote = findViewById(R.id.editNote);

        btnValider.setOnClickListener(this);
        backButton.setOnClickListener(this);

        restaurantName.setText("Sur le restaurant : " + thisResto.getNomR());

    }

    @Override
    public void onClick(View v) {
        if (v == btnValider) {

            Critique uneCritique = new Critique(thisResto.getIdR(), loggedUser.getMailU(), Integer.parseInt(editNote.getText().toString()) ,editComment.getText().toString());
            CritiqueDAO uneCritiqueDAO = new CritiqueDAO(this);
            uneCritiqueDAO.addCritique(uneCritique);
            Toast.makeText(this, "Commentaire ajouté !", Toast.LENGTH_SHORT).show();

            Intent showRestaurantActivity = new Intent(CritiquesResto.this, showRestaurant.class);
            IntentStorage.add(showRestaurantActivity, "loggedUser", loggedUser);
            IntentStorage.add(showRestaurantActivity, "thisResto", thisResto);
            startActivity(showRestaurantActivity);

        } else if (v == backButton) {
            Intent showRestaurantActivity = new Intent(CritiquesResto.this, showRestaurant.class);
            IntentStorage.add(showRestaurantActivity, "loggedUser", loggedUser);
            IntentStorage.add(showRestaurantActivity, "thisResto", thisResto);
            startActivity(showRestaurantActivity);
        }
    }
}



