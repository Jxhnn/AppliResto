package btssio.appliresto.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import btssio.appliresto.R;
import btssio.appliresto.modele.User;
import btssio.appliresto.modele.UserDAO;

public class ModifProfil extends Activity implements View.OnClickListener{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif_profil);
    }
    private TextView lblmailU;
    private EditText lblmdpU;
    private EditText new_pseudoU;
    private EditText new_mdpU;
    private EditText confirm_mdpU;
    private Button btnModifier;
    private UserDAO unUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier_profil);

        lblmailU = (TextView) findViewById(R.id.lblmailU);
        lblmdpU = (EditText) findViewById(R.id.textviewmdp);
        new_pseudoU=(EditText) findViewById(R.id.new_pseudoU);
        new_mdpU=(EditText) findViewById(R.id.new_mdpU);
        confirm_mdpU=(EditText) findViewById(R.id.confirm_mdpU);




        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (confirm_mdpU=new_mdpU && lblmdpU /* getmdpU de l'utilisateur connecté*/){
                    User unUser = new User( /*mail utilisateur connecté*/, new_pseudoU.getText().toString(), new_mdpU.getText().toString());
                    UserDAO unUserDAO;
                    unUserDAO = new UserDAO(Context);
                    unUserDAO.updateUser(unUser,/*mail de l'utilisateur connecté*/);
                }
            }
        });
    };
}
