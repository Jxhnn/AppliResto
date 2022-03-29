package btssio.appliresto.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import btssio.appliresto.R;
import btssio.appliresto.modele.User;
import btssio.appliresto.modele.UserDAO;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText passEdit, mailEdit;
    private Button loginButton;
    private TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        // https://www.youtube.com/watch?v=96QhUk0uid4&ab_channel=PraffulLachhwani

        mailEdit = findViewById(R.id.editTextId);
        passEdit = findViewById(R.id.editTextPass);


        loginButton = findViewById(R.id.buttonConnect);

        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == loginButton) {
            // Log.d("DEBUG:", "Email : " + passEdit.getText().toString());
            User thisUser = new User(mailEdit.getText().toString(), passEdit.getText().toString(), "");
            UserDAO userManager = new UserDAO(this);

            boolean out = userManager.verify(thisUser);

            if (out) Toast.makeText(this, "Authentifié !", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "Les identifiants sont invalides !", Toast.LENGTH_LONG).show();

            // Intent mainMenu = new Intent(this, )

        }
    }
}