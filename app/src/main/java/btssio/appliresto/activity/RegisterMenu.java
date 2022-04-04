package btssio.appliresto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import btssio.appliresto.R;
import btssio.appliresto.modele.User;
import btssio.appliresto.modele.UserDAO;

public class RegisterMenu extends AppCompatActivity implements View.OnClickListener {

    private EditText mailEdit, passEdit, nickEdit;
    private Button registerButton;
    private TextView loginAccountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_menu);
        getSupportActionBar().hide();

        mailEdit = findViewById(R.id.editTextEmail);
        passEdit = findViewById(R.id.editTextPass);
        nickEdit = findViewById(R.id.editTextNickname);
        registerButton = findViewById(R.id.buttonRegister);
        loginAccountText = findViewById(R.id.loginAccountText);

        registerButton.setOnClickListener(this);
        loginAccountText.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //Crée un nouveau User avec les parametres mailEdit, passEdit et nickEdit
        if (view == registerButton) {
            User newUser = new User(mailEdit.getText().toString(),
                    passEdit.getText().toString(),
                    nickEdit.getText().toString());

            UserDAO userManager = new UserDAO(this);

            long out = userManager.createUser(newUser);
            Log.d("DEBUG: ", "" +out);


            if (out == 0) Toast.makeText(this, "Erreur dans la création du compte.", Toast.LENGTH_SHORT).show();
            else {
                Intent loginMenu = new Intent(this, MainActivity.class);
                startActivity(loginMenu);
            }
        }

        if (view == loginAccountText) {
            Intent loginMenu = new Intent(this, MainActivity.class);
            startActivity(loginMenu);
        }
    }
}