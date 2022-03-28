package btssio.appliresto.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import btssio.appliresto.R;
import btssio.appliresto.modele.User;
import btssio.appliresto.modele.UserDAO;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText passEdit, mailEdit;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mailEdit = findViewById(R.id.editTextId);
        passEdit = findViewById(R.id.editTextMdp);

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
            Log.d("LOGIN DEBUG: ", "" + out);
        }
    }
}