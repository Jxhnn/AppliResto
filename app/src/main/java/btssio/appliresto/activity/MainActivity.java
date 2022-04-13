package btssio.appliresto.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import btssio.appliresto.R;
import btssio.appliresto.modele.User;
import btssio.appliresto.modele.UserDAO;
import btssio.appliresto.utils.IntentStorage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    private EditText passEdit, mailEdit;
    private Button loginButton;
    private TextView titleText, createAccountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        // https://www.youtube.com/watch?v=96QhUk0uid4&ab_channel=PraffulLachhwani

        mailEdit = findViewById(R.id.editTextId);
        passEdit = findViewById(R.id.editTextPass);
        createAccountText = findViewById(R.id.createAccountText);
        loginButton = findViewById(R.id.buttonConnect);

        loginButton.setEnabled(false);
        loginButton.setOnClickListener(this);

        mailEdit.setOnKeyListener(this);
        passEdit.setOnKeyListener(this);

        createAccountText.setOnClickListener(this);
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

            if (out) {

                String nickName = userManager.getUsernameFromEmail(thisUser.getMailU());
                thisUser.setPseudoU(nickName);

                String isAdmin = userManager.getRankFromEmail(thisUser.getMailU());

                if (isAdmin.equals("admin")) thisUser.setAdmin(true);
                else thisUser.setAdmin(false);

                Intent mainMenu = new Intent(this, MainMenu.class);
                IntentStorage.add(mainMenu, "LoggedUser", thisUser);
                startActivity(mainMenu);
            }

        }

        if (view == createAccountText) {
            Intent registerMenu = new Intent(this, RegisterMenu.class);
            startActivity(registerMenu);
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (mailEdit.getText().toString().equals("")
                || passEdit.getText().toString().equals("")) {
            loginButton.setEnabled(false);
        } else if (android.util.Patterns.EMAIL_ADDRESS.matcher(mailEdit.getText().toString())
                .matches()) loginButton.setEnabled(true);


        return false;
    }

    @Override
    protected void onDestroy() {
        IntentStorage.remove(getIntent());
        super.onDestroy();
    }

}