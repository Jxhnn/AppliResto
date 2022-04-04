package btssio.appliresto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import btssio.appliresto.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

public class GestionResto extends AppCompatActivity implements View.OnClickListener{

    private Button ajouter,supp,modif,consulter,retour;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_resto);


        ajouter = (Button) findViewById(R.id.buttonAjouteResto);
        supp = (Button) findViewById(R.id.buttonSupResto);
        modif = (Button) findViewById(R.id.buttonModifResto);
        consulter = (Button) findViewById(R.id.buttonConsultResto);
        retour =(Button) findViewById(R.id.btnRetour);

        ajouter.setOnClickListener(this);
        supp.setOnClickListener(this);
        modif.setOnClickListener(this);
        consulter.setOnClickListener(this);
        retour.setOnClickListener(this);


    }



    public void onClick(View v) {
        switch (v.getId()) {
            // Dans le cas d'un click sur un bouton ammène sur la page associée
            case R.id.buttonAjouteResto:
                Intent ajouterR = new Intent(GestionResto.this, AjouterResto.class);
                startActivity(ajouterR);
                break;
            case R.id.buttonConsultResto:
                Intent cons = new Intent(GestionResto.this, ConsulterResto.class);
                startActivity(cons);
                break;
            case R.id.buttonModifResto:
                Intent modifR = new Intent(GestionResto.this, ModifierResto.class);
                startActivity(modifR);
                break;
            case R.id.buttonSupResto:
                Intent sup = new Intent(GestionResto.this, SupprimerResto.class);
                startActivity(sup);
                break;
            case R.id.btnRetour:
                Intent retourR = new Intent(GestionResto.this, GestionResto.class);
                startActivity(retourR);
                break;
        }
    }

    public static class MainMenu extends AppCompatActivity implements View.OnClickListener {

        private ImageView accountIcon;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main_menu);
            getSupportActionBar().hide();

            accountIcon = findViewById(R.id.accountIcon);

            accountIcon.setOnClickListener(this);
        }

        private void showPopup(View v) {
            PopupMenu popup = new PopupMenu(this, v);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.main_option_menu, popup.getMenu());
            popup.show();

        }

        @Override
        public void onClick(View view) {

            if (view == accountIcon) {
                showPopup(view);
            }
        }
    }
}
