package btssio.appliresto.controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import btssio.appliresto.R;
import btssio.appliresto.modele.UserDAO;

/**
 * Created by mick.souloumiac1 on 22/03/2022.
 */

public class Supprimer_utilisateur extends Activity implements View.OnClickListener{
    private int id;
    private String mail;
    private String pseudo;
    private EditText editMail;
    private EditText editPseudo;
    private Button supp,retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supprimer_utilisateur);

        id=getIntent().getIntExtra("id",0);
        mail=getIntent().getStringExtra("mail");
        pseudo=getIntent().getStringExtra("pseudo");

        editMail=(EditText) findViewById(R.id.editMail);
        editPseudo=(EditText) findViewById(R.id.editPseudo);

        editMail.setText(mail);
        editPseudo.setText(pseudo);

        supp=(Button) findViewById(R.id.buttSup);
        retour=(Button) findViewById(R.id.buttRet);

        retour.setOnClickListener(this);
        supp.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttSup:
                UserDAO userDao=new UserDAO(this);
                userDao.suppUser(mail);
                Intent i=new Intent(Supprimer_utilisateur.this,consulter_utilisateur.class);
                startActivity(i);
                break;

            case R.id.buttRet:
                Intent back=new Intent(Supprimer_utilisateur.this,consulter_utilisateur.class);
                startActivity(back);
                break;
        }

    }
}
