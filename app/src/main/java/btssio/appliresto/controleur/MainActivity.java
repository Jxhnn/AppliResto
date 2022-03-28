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

public class MainActivity extends AppCompatActivity {

    EditText passEdit, mailEdit;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passEdit = findViewById(R.id.editTextId);
        mailEdit = findViewById(R.id.editTextMdp);
        loginButton = findViewById(R.id.buttonConnect);

        loginButton.setOnClickListener(loginEvent);
    }

    private View.OnClickListener loginEvent = new View.OnClickListener() {
        public void onClick(View v) {
            Log.d("DEBUG: ", "Login button clicked");
        }
    };
}