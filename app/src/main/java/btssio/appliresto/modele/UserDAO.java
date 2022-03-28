package btssio.appliresto.modele;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by mick.souloumiac1 on 17/03/2022.
 */

public class UserDAO {

    private static String base = "BDresto";
    private static int version = 1;
    BD_SQLiteOpenHelper accesBD;


    public UserDAO(Context ct){ accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);

}

    public User getUser(String mail, String pseudoU){
        User unUser=null;
        Cursor curseur;
        curseur=accesBD.getReadableDatabase().rawQuery("select * from utilisateur where mailU ="+mail+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            unUser=new User(mail,pseudoU);
        }

        return unUser;
}

    public ArrayList<User> getUsers(){
        Cursor curseur;
        String req="select * from utilisateur;";
        curseur=accesBD.getReadableDatabase().rawQuery(req,null);
        return  cursorToUserArrayList(curseur);
    }

    public ArrayList<User> cursorToUserArrayList(Cursor curseur){
        ArrayList<User> listeUser=new ArrayList<User>();
        String mail;
        String pseudo;
        curseur.moveToFirst();
        while(!curseur.isAfterLast()) {


            mail = curseur.getString(0);
            pseudo = curseur.getString(2);
            listeUser.add(new User(mail, pseudo));
            curseur.moveToNext();
        }
        return  listeUser;
    }


}
