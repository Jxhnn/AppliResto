package btssio.appliresto.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import btssio.appliresto.R;

public class MenuUtilisateur extends Activity implements View.OnClickListener{

    private Button consAimer,crit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_utilisateur);

        crit = (Button) findViewById(R.id.buttonCrit);
        crit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonCrit:
                Intent crit = new Intent(MenuUtilisateur.this, CritiquesResto.class);
                startActivity(crit);
            break;
            case R.id.buttonGestionCritiques:
                Intent gestioncrit = new Intent(MenuUtilisateur.this, GestionCritiques.class);
                startActivity(gestioncrit);
            break;

        }

    }
}
