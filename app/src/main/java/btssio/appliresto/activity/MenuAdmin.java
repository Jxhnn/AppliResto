package btssio.appliresto.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import btssio.appliresto.R;
import btssio.appliresto.controleur.consulter_utilisateur;
import btssio.appliresto.modele.User;
import btssio.appliresto.utils.IntentStorage;

public class MenuAdmin extends Activity implements View.OnClickListener{
    private Button gestResto;
    private Button gestUtil;
    private Button backButton;
    private User loggedUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_admin);

        gestResto=(Button) findViewById(R.id.buttonResto);
        gestUtil=(Button) findViewById(R.id.button2);
        backButton=(Button) findViewById(R.id.button3);

        gestResto.setOnClickListener(this);
        gestUtil.setOnClickListener(this);
        backButton.setOnClickListener(this);

        loggedUser = IntentStorage.get(getIntent(), "LoggedUser");
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.buttonResto:
                Intent i= new Intent(MenuAdmin.this,GestionResto.class);
                IntentStorage.add(i, "LoggedUser", loggedUser);
                finish();
                startActivity(i);
                break;
            case R.id.button2:
                Intent i2=new Intent(MenuAdmin.this, consulter_utilisateur.class);
                IntentStorage.add(i2, "LoggedUser", loggedUser);
                finish();
                startActivity(i2);
                break;
            case R.id.button3:

                Intent i3=new Intent(MenuAdmin.this, MainMenu.class);
                IntentStorage.add(i3, "LoggedUser", loggedUser);
                finish();
                startActivity(i3);
                break;
        }

    }
}
