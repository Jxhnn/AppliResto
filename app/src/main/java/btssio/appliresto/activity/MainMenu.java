package btssio.appliresto.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import java.util.ArrayList;

import btssio.appliresto.R;
import btssio.appliresto.adapter.RestoAdapter;
import btssio.appliresto.holder.RestaurantViewHolder;
import btssio.appliresto.modele.Resto;
import btssio.appliresto.modele.RestoDAO;
import btssio.appliresto.modele.User;
import btssio.appliresto.utils.IntentStorage;
import btssio.appliresto.utils.WebUtils;

public class MainMenu extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener, AdapterView.OnItemClickListener {

    private ImageView accountIcon;
    private User loggedUser;
    private TextView numRestaurants;
    private int[] idRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        getSupportActionBar().hide();

        loggedUser = IntentStorage.get(getIntent(), "LoggedUser");

        accountIcon = findViewById(R.id.accountIcon);
        numRestaurants = findViewById(R.id.numRestaurants);


        accountIcon.setOnClickListener(this);

        RestoDAO restoManager = new RestoDAO(this);
        ArrayList<Resto> restorants = new ArrayList<Resto>();
        restorants = restoManager.getRestos();

        numRestaurants.setText((restorants.size() + 1) + " restaurants");

        String[] nomRestaurants = new String[restorants.size()];
        String[] descRestaurants = new String[restorants.size()];
        String[] imageRestaurants = new String[restorants.size()];
        idRestaurants = new int[restorants.size()];

        WebUtils webUtils = new WebUtils();

        for (int i = 0; i <= restorants.size() - 1; i++) {
            nomRestaurants[i] = restorants.get(i).getNomR();
            descRestaurants[i] = restorants.get(i).getHorairesR();
            imageRestaurants[i] = restorants.get(i).getPhotoPrincipal();
            idRestaurants[i] = restorants.get(i).getIdR();
        }


        ListView listView = (ListView)findViewById(R.id.restaurantsList);
        RestaurantViewHolder.loggedUser = loggedUser;
        RestoAdapter restoAdapter = new RestoAdapter(this, nomRestaurants, descRestaurants, imageRestaurants, idRestaurants, listView);
        listView.setAdapter(restoAdapter);
        listView.setOnItemClickListener(this);

        // https://stackoverflow.com/a/8353790
        // https://java2blog.com/android-custom-listview-with-images-text-example/

    }

    // TODO: Move onto Utils


    @Override
    public void onClick(View view) {

        if (view == accountIcon) {

            btssio.appliresto.utils.PopupMenu.showPopup(view, this, loggedUser.isAdmin());
            Log.d("ADMIN", "" + loggedUser.isAdmin());
        }
    }

    @Override
    protected void onDestroy() {
        IntentStorage.remove(getIntent());
        super.onDestroy();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        if (item.getTitle().equals("Mon compte")) {
            Intent personnalInfosMenu = new Intent(this, PersonnalInfosMenu.class);
            IntentStorage.add(personnalInfosMenu, "loggedUser", loggedUser);
            startActivity(personnalInfosMenu);

        } else if (item.getTitle().equals("Déconnexion")) {
            loggedUser = null;
            Intent mainActivity = new Intent(this, MainActivity.class);
            startActivity(mainActivity);
        } else if (item.getTitle().equals("Options")) {
            Intent userMenu = new Intent(this, MenuAdmin.class);
            IntentStorage.add(userMenu, "LoggedUser", loggedUser);
            startActivity(userMenu);
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
        Intent showRestaurant = new Intent(this, showRestaurant.class);
        RestoDAO restoManager = new RestoDAO(this);
        IntentStorage.add(showRestaurant, "loggedUser", loggedUser);
        IntentStorage.add(showRestaurant, "thisResto", restoManager.getUnResto(idRestaurants[pos]));
        startActivity(showRestaurant);
    }
}