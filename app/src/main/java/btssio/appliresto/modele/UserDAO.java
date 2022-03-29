package btssio.appliresto.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    public String getUsernameFromEmail(String email) {
        User unUser=null;
        Cursor curseur;
        curseur=accesBD.getReadableDatabase().rawQuery("select pseudoU from utilisateur where mailU = '" + email + "';",null);
        if (curseur.getCount() == 1) {
            curseur.moveToFirst();
            return curseur.getString(0);
        }
        return "Unknown";
    }

    public boolean verify(User user) {
        Cursor out;
        out = accesBD.getReadableDatabase().rawQuery("SELECT * FROM utilisateur WHERE mailU = '" + user.getMailU() + "' AND mdpU = '" + user.getMdpU() + "';", null);

        // Log.d("DEBUG QUERY:", "SELECT * FROM utilisateur WHERE mailU = '" + user.getMailU() + "' AND mdpU = '" + user.getMdpU() + "';");

        if (out.getCount() > 0) return true;
        else return false;
    }

    public long createUser(User user) {
        SQLiteDatabase db = accesBD.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("mailU", user.getMailU());
        values.put("pseudoU", user.getPseudoU());
        values.put("mdpU", user.getMdpU());

        long ret = db.insert("Utilisateur", null, values);
        return ret;
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
