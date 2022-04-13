package btssio.appliresto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import btssio.appliresto.R;
import btssio.appliresto.modele.User;
import btssio.appliresto.modele.UserDAO;
import btssio.appliresto.utils.IntentStorage;

public class PersonnalInfosMenu extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    private User loggedUser;
    private EditText editTextNickname, editTextEmail, editTextPass;
    private Button modifyButton;
    private UserDAO userManager;
    private ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_personal_infos);
        getSupportActionBar().hide();

        editTextNickname = findViewById(R.id.editTextNickname);
        editTextPass = findViewById(R.id.editTextPass);
        editTextEmail = findViewById(R.id.editTextEmail);
        modifyButton = findViewById(R.id.modifyButton);
        backIcon = findViewById(R.id.backIcon);

        loggedUser = IntentStorage.get(getIntent(), "loggedUser");
        editTextEmail.setText(loggedUser.getMailU());
        editTextNickname.setText(loggedUser.getPseudoU());

        userManager = new UserDAO(this);

        modifyButton.setOnClickListener(this);
        backIcon.setOnClickListener(this);
        editTextNickname.setOnKeyListener(this);
        editTextEmail.setOnKeyListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == modifyButton) {

            if (editTextPass.getText().toString().equals("")) {
                User newUser = new User(editTextEmail.getText().toString(),
                        loggedUser.getMdpU(),
                        editTextNickname.getText().toString()
                        );

                userManager.updateUserWithoutPass(newUser, loggedUser.getMailU());

                loggedUser = newUser;
            } else {
                User newUser = new User(editTextEmail.getText().toString(),
                        editTextPass.getText().toString(),
                        editTextNickname.getText().toString()
                );

                userManager.updateUser(newUser, loggedUser.getMailU());
                loggedUser = newUser;
            }

            Intent mainMenu = new Intent(this, MainMenu.class);
            IntentStorage.add(mainMenu, "LoggedUser", loggedUser);
            startActivity(mainMenu);
        }

        if (view == backIcon) {
            Intent mainMenu = new Intent(this, MainMenu.class);
            IntentStorage.add(mainMenu, "LoggedUser", loggedUser);
            startActivity(mainMenu);
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (editTextEmail.getText().toString().equals("")
                || editTextNickname.getText().toString().equals("")) {
            modifyButton.setEnabled(false);
        } else if (android.util.Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText().toString())
                .matches()) modifyButton.setEnabled(true);


        return false;
    }
}
