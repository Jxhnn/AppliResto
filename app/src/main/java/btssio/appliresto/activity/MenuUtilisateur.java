package btssio.appliresto.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import btssio.appliresto.R;

public class MenuUtilisateur extends Activity implements View.OnClickListener{

    private Button consAimer,crit,modifProfil,deco;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_utilisateur);

        consAimer = (Button) findViewById(R.id.buttonConsAime);
        crit = (Button) findViewById(R.id.buttonCrit);
        modifProfil=(Button) findViewById(R.id.buttonModifProfil);
        deco=(Button) findViewById(R.id.buttonDeco);

        consAimer.setOnClickListener(this);
        crit.setOnClickListener(this);
        modifProfil.setOnClickListener(this);
        deco.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonConsAime:
                Intent consAimer = new Intent(MenuUtilisateur.this, AimerResto.class);
                startActivity(consAimer);
            break;
            case R.id.buttonCrit:
                Intent crit = new Intent(MenuUtilisateur.this, CritiquesResto.class);
                startActivity(crit);
            break;
            case R.id.buttonModifProfil:
                Intent modif = new Intent(MenuUtilisateur.this, ModifProfil.class);
                startActivity(modif);
            case R.id.buttonGestionCritiques:
                Intent gestioncrit = new Intent(MenuUtilisateur.this, GestionCritiques.class);
                startActivity(gestioncrit);
            break;
            case R.id.buttonDeco:
                Intent deco = new Intent(MenuUtilisateur.this, MainActivity.class);
                startActivity(deco);

        }

    }
}
