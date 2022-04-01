package btssio.appliresto.activity;

import android.app.ListActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
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
import btssio.appliresto.modele.Resto;
import btssio.appliresto.modele.RestoDAO;
import btssio.appliresto.modele.User;
import btssio.appliresto.utils.IntentStorage;
import btssio.appliresto.utils.WebUtils;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    private ImageView accountIcon;
    private User loggedUser;
    private TextView numRestaurants;

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
        int[] likeButtonsRestaurants = new int[restorants.size()];

        WebUtils webUtils = new WebUtils();

        for (int i = 0; i <= restorants.size() - 1; i++) {
            nomRestaurants[i] = restorants.get(i).getNomR();
            descRestaurants[i] = restorants.get(i).getHorairesR();
            imageRestaurants[i] = restorants.get(i).getPhotoPrincipal();
            likeButtonsRestaurants[i] = i;
        }

        ListView listView = (ListView)findViewById(R.id.restaurantsList);
        RestoAdapter restoAdapter = new RestoAdapter(this, nomRestaurants, descRestaurants, imageRestaurants);
        listView.setAdapter(restoAdapter);

        // https://stackoverflow.com/a/8353790
        // https://java2blog.com/android-custom-listview-with-images-text-example/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(),"You Selected "+nomRestaurants[position]+ " as Restaurant",Toast.LENGTH_SHORT).show();        }
        });

    }

    // TODO: Move onto Utils
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

    @Override
    protected void onDestroy() {
        IntentStorage.remove(getIntent());
        super.onDestroy();
    }
}