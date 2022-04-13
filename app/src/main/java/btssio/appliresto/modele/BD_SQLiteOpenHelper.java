package btssio.appliresto.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mick.souloumiac1 on 17/03/2022.
 */

public class BD_SQLiteOpenHelper extends SQLiteOpenHelper{

    private String creaTableResto="create table resto ( "
            + " 	idR integer, "
            + " 	nomR text, "
            + " 	numAdrR integer, "
            + " 	voieAdrR text, "
            + " 	cpR integer, "
            + " 	villeR text, "
            + " 	latitudeDegR float, "
            + " 	longitudeDegR float, "
            + " 	descR text, "
            + " 	horairesR text, "
            + "     photoPrincipal text, "
            + " 	primary key (idR) "
            + " ); ";


    private String creaTableUtilisateur = " create table utilisateur ( "
            + " 	mailU text, "
            + " 	mdpU text, "
            + " 	pseudoU text, "
            + "     roleU text,"
            + " 	primary key (mailU) "
            + " ); ";


    private String creaTableAimer = " create table aimer ( "
            + " 	idR integer, "
            + " 	mailU text, "
            + " 	aime boolean, "
            + " 	primary key (idR,mailU), "
            + " 	foreign key(idR) references resto(idR), "
            + " 	foreign key(mailU) references utilisateur(mailU) "
            + " ); ";


    private String creaTableCritiquer = " create table critique ( "
            + " 	idR integer, "
            + " 	mailU text, "
            + " 	note integer, "
            + " 	commentaire text, "
            + " 	primary key (idR,mailU), "
            + " 	foreign key(idR) references resto(idR), "
            + " 	foreign key(mailU) references utilisateur(mailU) "
            + " );";


    private String creaTablePhoto = " create table photo ( "
            + " 	idP integer, "
            + " 	cheminP text, "
            + " 	idR integer, "
            + " 	primary key (idP), "
            + " 	foreign key(idR) references resto(idR) "
            + " ); ";


    public BD_SQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(creaTableUtilisateur);
        db.execSQL(creaTableResto);
        db.execSQL(creaTableCritiquer);
        db.execSQL(creaTableAimer);
        db.execSQL(creaTablePhoto);


        Log.d("log","base de test cree");


        db.execSQL("insert into utilisateur (mailU,mdpU, pseudoU, roleU) values('mickael@mickael.com','secretpass64','Mickael09', 'user');");
        db.execSQL("insert into utilisateur (mailU,mdpU, pseudoU, roleU) values('john@john.com','toto','Toto64000', 'admin');");


        db.execSQL("insert into resto (idR, nomR, numAdrR, voieAdrR, cpR, villeR, latitudeDegR, longitudeDegR, descR, horairesR, photoPrincipal) values(1, 'lentrepote', '2', 'rue Maurice Ravel', 33000, 'Bordeaux', 44.7948, -0.58754, 'description','11h45||14h30 18h||22h', 'https://d1ralsognjng37.cloudfront.net/fd881b26-99d1-45d3-9517-d1177e9e0028.jpeg');");
        db.execSQL("insert into resto (idR, nomR, numAdrR, voieAdrR, cpR, villeR, latitudeDegR, longitudeDegR, descR, horairesR, photoPrincipal) values(2, 'le bar du charcutier', '30', 'rue Parlement Sainte-Catherine', 33000, 'Bordeaux', NULL, NULL, 'description', '11h45||14h30 18h||22h', 'https://d1ralsognjng37.cloudfront.net/fd881b26-99d1-45d3-9517-d1177e9e0028.jpeg');");
        db.execSQL("insert into resto (idR, nomR, numAdrR, voieAdrR, cpR, villeR, latitudeDegR, longitudeDegR, descR, horairesR, photoPrincipal) values(3, 'Sapporo', '33', 'rue Saint Rémi', 33000, 'Bordeaux', NULL, NULL, 'Le Sapporo propose à ses clients de délicieux plats typiques japonais.','11h45||14h30 18h||22h', 'https://d1ralsognjng37.cloudfront.net/fd881b26-99d1-45d3-9517-d1177e9e0028.jpeg');");
        db.execSQL("insert into resto (idR, nomR, numAdrR, voieAdrR, cpR, villeR, latitudeDegR, longitudeDegR, descR, horairesR, photoPrincipal) values(4, 'Cidrerie du fronton', NULL, 'Place du Fronton', 64210, 'Arbonne', NULL, NULL, 'description','11h45||14h30 18h||22h', 'https://d1ralsognjng37.cloudfront.net/fd881b26-99d1-45d3-9517-d1177e9e0028.jpeg');");
        db.execSQL("insert into resto (idR, nomR, numAdrR, voieAdrR, cpR, villeR, latitudeDegR, longitudeDegR, descR, horairesR, photoPrincipal) values(5, 'Agadir', '3', 'Rue Sainte-Catherine', 64100, 'Bayonne', NULL, NULL, 'description','11h45||14h30 18h||22h', 'https://d1ralsognjng37.cloudfront.net/fd881b26-99d1-45d3-9517-d1177e9e0028.jpeg');");
        db.execSQL("insert into resto (idR, nomR, numAdrR, voieAdrR, cpR, villeR, latitudeDegR, longitudeDegR, descR, horairesR, photoPrincipal) values(6, 'Le Bistrot Sainte Cluque', '9', 'Rue Hugues', 64100, 'Bayonne', NULL, NULL, 'description','11h45||14h30 18h||22h', 'https://d1ralsognjng37.cloudfront.net/fd881b26-99d1-45d3-9517-d1177e9e0028.jpeg');");
        db.execSQL("insert into resto (idR, nomR, numAdrR, voieAdrR, cpR, villeR, latitudeDegR, longitudeDegR, descR, horairesR, photoPrincipal) values(7, 'Le Bistrot Sainte Cluque', '9', 'Rue Hugues', 64100, 'Bayonne', NULL, NULL, 'description','11h45||14h30 18h||22h', 'https://d1ralsognjng37.cloudfront.net/fd881b26-99d1-45d3-9517-d1177e9e0028.jpeg');");
        db.execSQL("insert into resto (idR, nomR, numAdrR, voieAdrR, cpR, villeR, latitudeDegR, longitudeDegR, descR, horairesR, photoPrincipal) values(8, 'Le Bistrot Sainte Cluque', '9', 'Rue Hugues', 64100, 'Bayonne', NULL, NULL, 'description','11h45||14h30 18h||22h', 'https://d1ralsognjng37.cloudfront.net/fd881b26-99d1-45d3-9517-d1177e9e0028.jpeg');");

/*
        db.execSQL("insert into aimer (idR,mailU,aime) values(1,'mathieu.capliez@gmail.com',1);");
        db.execSQL("insert into aimer (idR,mailU,aime) values(2,'nicolas.harispe@gmail.com',1);");
        db.execSQL("insert into aimer (idR,mailU,aime) values(4,'alex.garat@gmail.com',1);");
        db.execSQL("insert into aimer (idR,mailU,aime) values(6,'jj.soueix@gmail.com',1);");
*/

        db.execSQL("insert into critique(idR,mailU,note,commentaire) values(1,'alex.garat@gmail.com',3,'moyen');");
        db.execSQL("insert into critique(idR,mailU,note,commentaire) values(3,'nicolas.harispe@gmail.com',4,'Très bonne entrecote, les frites sont maisons et delicieuses.');");
        db.execSQL("insert into critique(idR,mailU,note,commentaire) values(5,'mathieu.capliez@gmail.com',4,'Très bon accueil.');");
        db.execSQL("insert into critique(idR,mailU,note,commentaire) values(6,'jj.soueix@gmail.com',1,'À éviter...');");
        db.execSQL("insert into critique(idR,mailU,note,commentaire) values(4,'alex.garat@gmail.com',2,'bof.');");
        db.execSQL("insert into critique(idR,mailU,note,commentaire) values(4,'john@john.com',2,'pas ouf hein.');");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
