package btssio.appliresto.controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import btssio.appliresto.R;

import java.util.ArrayList;

import btssio.appliresto.activity.MenuAdmin;
import btssio.appliresto.modele.User;
import btssio.appliresto.modele.UserDAO;

/**
 * Created by mick.souloumiac1 on 22/03/2022.
 */

public class consulter_utilisateur extends Activity implements View.OnClickListener{
    private ListView listeU;
    private ArrayList<User> lesUsers=new ArrayList<User>();
    private UserDAO userDao = new UserDAO(this);
    private Button ret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulter_utilisateur);

        ret=(Button) findViewById(R.id.btnRetour);
        ret.setOnClickListener(this);


        listeU=(ListView) findViewById(R.id.listUtilisateur);
        lesUsers = userDao.getUsers();

        ArrayAdapter monadapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lesUsers);

        listeU.setAdapter(monadapter);


        listeU.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User userItem=(User) listeU.getAdapter().getItem(position);
                Intent i = new Intent(consulter_utilisateur.this,Supprimer_utilisateur.class);
                i.putExtra("mail",userItem.getMailU());
                i.putExtra("pseudo", userItem.getPseudoU());
                Log.d("fnqsdlnd", "klqsjd"+userItem.getPseudoU());
                startActivity(i);
            }
        });


    }


    @Override
    public void onClick(View view) {
        Intent i = new Intent(consulter_utilisateur.this, MenuAdmin.class);
        startActivity(i);
    }
}
